package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

/**
 * Represents header of flats table.
 */
public class FlatsTableHeaderRowFormatter extends TableRowFormatter {

    public FlatsTableHeaderRowFormatter() {
        cellFormatters.add(new CellFormatter("Помещение"));
        cellFormatters.add(new CellFormatter("Правообладатель"));
        cellFormatters.add(new CellFormatter("Право"));
        cellFormatters.add(new CellFormatter("Площадь, м²"));

        cellFormatters.forEach(cellFormatter -> cellFormatter.setBold().setCentered());
    }

}
