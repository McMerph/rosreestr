package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.Objects;

/**
 * Used to format cell in table.
 */
public class CellFormatter {

    private final String[] paragraphsText;

    private boolean bold = false;
    private boolean centered = false;
    private String color;

    public CellFormatter(String... paragraphsText) {
        this.paragraphsText = paragraphsText;
    }

    public void format(XWPFTableCell cell) {
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
        if (Objects.nonNull(color)) {
            cell.setColor(color);
        }

        handleText(cell);
    }

    public CellFormatter setBold() {
        this.bold = true;
        return this;
    }

    public CellFormatter setCentered() {
        this.centered = true;
        return this;
    }

    public CellFormatter setColor(String color) {
        this.color = color;
        return this;
    }

    private void handleText(XWPFTableCell cell) {
        for (int i = 0; i < paragraphsText.length; i++) {
            String paragraphText = paragraphsText[i];
            XWPFParagraph paragraph;
            if (i == 0) {
                paragraph = cell.getParagraphs().get(i);
            } else {
                paragraph = cell.addParagraph();
            }
            formatParagraph(paragraph, i);
        }
    }

    private void formatParagraph(XWPFParagraph paragraph, int paragraphCount) {
        paragraph.setIndentationLeft(50);
        paragraph.setIndentationRight(50);
        if (centered) {
            paragraph.setAlignment(ParagraphAlignment.CENTER);
        }
        paragraph.setSpacingAfter(0);

        XWPFRun run = paragraph.createRun();
        run.setText(paragraphsText[paragraphCount]);
        run.setBold(bold);
        run.setFontFamily("Times New Roman");
    }

}
