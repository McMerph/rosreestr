package ru.mcmerphy.rosreestr.controllers.xwpf;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import ru.mcmerphy.rosreestr.controllers.xwpf.flats.table.FlatsTableProducer;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.format;

/**
 * Flats document producer.
 */
public class FlatsDocumentProducer {

    public XWPFDocument produce(String building, List<Flat> flats) {
        XWPFDocument document = new XWPFDocument();
        insertHeader(document, building);
        new FlatsTableProducer().produce(document, flats);
        insertFooter(document, flats);

        return document;
    }

    private void insertHeader(XWPFDocument document, String building) {
        String text = String.join(" ",
                "Список собственников помещений по", building,
                "на", LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        XWPFParagraph header = document.createParagraph();
        header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = getRun(text, header);
        run.setBold(true);
    }

    private void insertFooter(XWPFDocument document, List<Flat> flats) {
        BigDecimal summaryTotalArea = flats.stream()
                .map(Flat::getTotalArea)
                .reduce(BigDecimal::add)
                .orElse(null);
        String text = String.join(" ", "Общая площадь помещений:", format(summaryTotalArea), "м²");

        XWPFParagraph footer = document.createParagraph();
        footer.setAlignment(ParagraphAlignment.RIGHT);
        footer.setSpacingBefore(150);
        XWPFRun run = getRun(text, footer);
        run.setItalic(true);
    }

    private XWPFRun getRun(String text, XWPFParagraph header) {
        XWPFRun run = header.createRun();
        run.setText(text);
        run.setFontSize(12);
        run.setFontFamily("Times New Roman");
        return run;
    }

}
