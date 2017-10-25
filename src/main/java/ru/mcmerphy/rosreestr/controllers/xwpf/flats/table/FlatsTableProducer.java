package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to produce flats table in document.
 */
public class FlatsTableProducer {

    public void produce(XWPFDocument document, List<Flat> flats) {
        List<TableRowFormatter> tableRowFormatters = getTableRowFormatters(flats);
        int rowsAmount = tableRowFormatters.size();
        XWPFTable table = document.createTable(rowsAmount, getColumnsAmount(tableRowFormatters));
        setFullWidth(table);

        for (int i = 0; i < rowsAmount; i++) {
            tableRowFormatters.get(i).format(table.getRow(i));
        }
    }

    private List<TableRowFormatter> getTableRowFormatters(List<Flat> flats) {
        List<TableRowFormatter> tableRowFormatters = new ArrayList<>();
        tableRowFormatters.add(new FlatsTableHeaderRowFormatter());
        for (Flat flat : flats) {
            if (flat.getRights().isEmpty()) {
                tableRowFormatters.add(new FlatWithoutRightsRowFormatter(flat));
            } else {
                tableRowFormatters.addAll(generateRightRowFormatters(flat));
            }
        }
        return tableRowFormatters;
    }

    private List<FlatRightRowFormatter> generateRightRowFormatters(Flat flat) {
        return flat.getRights().stream()
                .map(right -> new FlatRightRowFormatter(flat, right))
                .collect(Collectors.toList());
    }

    private int getColumnsAmount(List<TableRowFormatter> tableRowFormatters) {
        return tableRowFormatters.stream()
                .mapToInt(TableRowFormatter::getCellsAmount)
                .max()
                .getAsInt();
    }

    private void setFullWidth(XWPFTable table) {
        CTTblPr tableProperties = table.getCTTbl().getTblPr();
        CTTblWidth tableWidth = tableProperties.getTblW();
        tableWidth.setType(STTblWidth.PCT);
        tableWidth.setW(BigInteger.valueOf(100 * 50));
    }

}
