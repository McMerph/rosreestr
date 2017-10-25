package ru.mcmerphy.rosreestr.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.mcmerphy.rosreestr.model.domain.Flat;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Application model.
 */
public class ApplicationModel {

    private final ApplicationFileSystemHandler fileSystemHandler = new ApplicationFileSystemHandler();
    private ApplicationConfig associatedConfig = new ApplicationConfig();

    private final ObservableList<FlatExcerpt> flatExcerpts = FXCollections.observableArrayList();

    /**
     * Replace flat excerpts from associated config file.
     * If no file associated - remove all flat excerpts.
     *
     * @throws LoadFileFailException if can't load associated config file
     */
    public void loadAssociatedConfig()
            throws LoadFileFailException, FilesAlreadyContainsException, FlatParserFailException {
        clearFlatExcerpts();
        if (fileSystemHandler.isAssociatedConfigFileExists()) {
            associateConfig(fileSystemHandler.getAssociatedConfigFile());
            parseFiles(associatedConfig.getFilesList());
        }
    }

    /**
     * Remove all flat excerpts.
     */
    public void clearFlatExcerpts() {
        flatExcerpts.clear();
    }

    /**
     * Remove flat excerpt.
     *
     * @param flatExcerpt flat excerpt to remove
     */
    public void removeFlatParser(FlatExcerpt flatExcerpt) {
        flatExcerpts.remove(flatExcerpt);
    }

    /**
     * Add flats from specified files if file is not already parsed.
     *
     * @param files files to parse
     * @throws FilesAlreadyContainsException if at least one file is already parsed
     */
    public void parseFiles(Collection<File> files) throws FilesAlreadyContainsException, FlatParserFailException {
        List<File> alreadyContainsFiles = new ArrayList<>();
        List<FlatParser> failedParsers = new ArrayList<>();
        files.forEach(file -> {
            try {
                parseFile(file);
            } catch (FilesAlreadyContainsException e) {
                alreadyContainsFiles.addAll(e.getFiles());
            } catch (FlatParserFailException e) {
                failedParsers.addAll(e.getFlatParsers());
            }
        });
        if (!alreadyContainsFiles.isEmpty()) {
            throw new FilesAlreadyContainsException(alreadyContainsFiles);
        }
        if (!failedParsers.isEmpty()) {
            throw new FlatParserFailException(failedParsers);
        }
    }

    /**
     * Replace flatParsers from specified config file.
     *
     * @param file config file to load from
     * @throws LoadFileFailException if can't load specified config file
     */
    public void loadConfig(File file)
            throws LoadFileFailException, FilesAlreadyContainsException, FlatParserFailException {
        associateConfig(file);
        loadAssociatedConfig();
    }

    /**
     * Save associated config file if exists.
     */
    public void saveAssociatedConfig() throws SaveFileFailException {
        if (fileSystemHandler.isAssociatedConfigFileExists()) {
            saveConfig(fileSystemHandler.getAssociatedConfigFile());
        }
    }

    /**
     * Save application config to file
     *
     * @param file file to save to
     */
    public void saveConfig(File file) throws SaveFileFailException {
        associatedConfig = getCurrentConfig();
        try {
            JAXBContext context = JAXBContext.newInstance(ApplicationConfig.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(associatedConfig, file);
            fileSystemHandler.saveAssociatedConfigFile(file);
        } catch (Exception e) {
            throw new SaveFileFailException(file);
        }
    }

    /**
     * @return all flats.
     */
    public Collection<Flat> getFlats() {
        return flatExcerpts.stream().map(FlatExcerpt::getFlat).collect(Collectors.toSet());
    }

    public ApplicationFileSystemHandler getFileSystemHandler() {
        return fileSystemHandler;
    }

    /**
     * Flat excerpts getter.
     * DO NOT use it for modifying because it can lead to inconsistent state.
     * Instead use other methods.
     */
    public ObservableList<FlatExcerpt> getFlatExcerpts() {
        return flatExcerpts;
    }

    /**
     * Add flat from specified file if this file is not already parsed.
     *
     * @param file file to parse
     * @throws FilesAlreadyContainsException if file is already parsed
     */
    private void parseFile(File file) throws FilesAlreadyContainsException, FlatParserFailException {
        boolean alreadyContains = flatExcerpts.stream()
                .anyMatch(flatExcerpt -> flatExcerpt.isSameFile(file));
        if (!alreadyContains) {
            FlatParser flatParser = new FlatParser(file);
            flatParser.parse();
            flatExcerpts.add(new FlatExcerpt(flatParser));
        } else {
            throw new FilesAlreadyContainsException(file);
        }
    }

    private void associateConfig(File file) throws LoadFileFailException {
        try {
            JAXBContext context = JAXBContext.newInstance(ApplicationConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            associatedConfig = (ApplicationConfig) unmarshaller.unmarshal(file);
            fileSystemHandler.saveAssociatedConfigFile(file);
        } catch (Exception e) {
            fileSystemHandler.removeAssociatedConfigFile();
            throw new LoadFileFailException(file);
        }
    }

    private ApplicationConfig getCurrentConfig() {
        return new ApplicationConfig(flatExcerpts.stream()
                .map(FlatExcerpt::getFile)
                .collect(Collectors.toList()));
    }

}
