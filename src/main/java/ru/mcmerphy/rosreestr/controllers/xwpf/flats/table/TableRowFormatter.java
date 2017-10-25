package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;

import java.util.ArrayList;
import java.util.List;

/**
 * Used to format row in table.
 */
public abstract class TableRowFormatter {

    protected final List<CellFormatter> cellFormatters = new ArrayList<>();

    private boolean mergingStart = false;
    private boolean mergingContinue = false;

    public void format(XWPFTableRow row) {
        row.setCantSplitRow(true);
        List<XWPFTableCell> cells = row.getTableCells();
        for (int i = 0; i < cells.size(); i++) {
            CellFormatter cellFormatter = cellFormatters.get(i);
            cellFormatter.format(cells.get(i));
        }

        if (mergingStart) {
            setMerged(row, STMerge.RESTART);
        } else if (mergingContinue) {
            setMerged(row, STMerge.CONTINUE);
        }
    }

    /**
     * @return cells amount in this row.
     */
    public int getCellsAmount() {
        return cellFormatters.size();
    }

    public void setMergingStart() {
        this.mergingStart = true;
    }

    public void setMergingContinue() {
        this.mergingContinue = true;
    }

    protected List<Integer> getColumnsToMerge() {
        return new ArrayList<>();
    }

    private void setMerged(XWPFTableRow row, STMerge.Enum merge) {
        getColumnsToMerge().stream()
                .map(row::getCell)
                .forEach(cell -> cell.getCTTc().addNewTcPr().addNewVMerge().setVal(merge));
    }

}
