package ru.mcmerphy.rosreestr.controllers.xwpf;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.mcmerphy.rosreestr.controllers.xwpf.flats.table.FlatsTableProducer;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Flats document producer.
 */
public class FlatsDocumentProducer {

    public XWPFDocument produce(String building, List<Flat> flats) {
        XWPFDocument document = new XWPFDocument();
        insertHeader(document, building);
        new FlatsTableProducer().produce(document, flats);

        return document;
    }

    private void insertHeader(XWPFDocument document, String building) {
        String headerText = String.join(" ",
                "Список собственников помещений по", building,
                "на", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        XWPFParagraph header = document.createParagraph();
        XWPFRun run = header.createRun();
        run.setText(headerText);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        header.setAlignment(ParagraphAlignment.CENTER);
        run.setBold(true);
    }

}
