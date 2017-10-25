package ru.mcmerphy.rosreestr.controllers.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.mcmerphy.rosreestr.controllers.util.Comparators.FLAT_COMPARATOR_BY_APARTMENT;
import static ru.mcmerphy.rosreestr.controllers.util.Formatter.format;

/**
 * Represents marshaller from flats to documents.
 */
public class FlatsMarshaller {

    private int fileCreationFailCounter = 0;
    private final File directory;

    public FlatsMarshaller(File directory) {
        this.directory = directory;
    }

    public FlatsMarshallerResult marshall(Collection<Flat> flats) {
        Map<String, List<Flat>> flatsByBuildings = getFlatsByBuildings(flats);

        FlatsMarshallerResult flatsMarshallerResult = new FlatsMarshallerResult(directory);
        for (String building : flatsByBuildings.keySet()) {
            try {
                XWPFDocument document = new FlatsDocumentProducer().produce(building, flatsByBuildings.get(building));
                write(building, document);
                flatsMarshallerResult.getSuccesses().add(building);
            } catch (FlatsDocumentWriteFailException e) {
                flatsMarshallerResult.getFails().add(building);
            }
        }

        return flatsMarshallerResult;
    }

    private Map<String, List<Flat>> getFlatsByBuildings(Collection<Flat> flats) {
        Map<String, List<Flat>> flatsByBuildings = flats.stream()
                .collect(Collectors.groupingBy(flat ->
                        format(" ",
                                flat.getAddress().getStreet().getRepresentation(),
                                flat.getAddress().getBuilding().getRepresentation()
                        )));
        for (String building : flatsByBuildings.keySet()) {
            flatsByBuildings.get(building).sort(FLAT_COMPARATOR_BY_APARTMENT);
        }
        return flatsByBuildings;
    }

    private void write(String building, XWPFDocument document) throws FlatsDocumentWriteFailException {
        try {
            File file = getFile(building);
            try (OutputStream os = new FileOutputStream(file)) {
                document.write(os);
            }
        } catch (IOException e) {
            throw new FlatsDocumentWriteFailException();
        }
    }

    private File getFile(String building) throws IOException {
        Path filePath = directory.toPath().resolve(building + ".docx");
        if (filePath.toFile().exists()) {
            Files.delete(filePath);
        }
        if (!filePath.toFile().createNewFile()) {
            String fileName = "flats" + (++fileCreationFailCounter);
            filePath = directory.toPath().resolve(fileName + ".docx");
        }

        return filePath.toFile();
    }

}
