package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

import ru.mcmerphy.rosreestr.controllers.util.Formatter;
import ru.mcmerphy.rosreestr.model.domain.Apartment;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.NO_CONTENT;

/**
 * Represents flat with no rights row in table.
 */
public class FlatWithoutRightsRowFormatter extends TableRowFormatter {

    public FlatWithoutRightsRowFormatter(Flat flat) {
        Apartment apartment = flat.getAddress().getApartment();

        cellFormatters.add(new CellFormatter(apartment.getValue()).setCentered());
        cellFormatters.add(new CellFormatter(NO_CONTENT));
        cellFormatters.add(new CellFormatter(NO_CONTENT));
        cellFormatters.add(new CellFormatter(Formatter.format(flat.getTotalArea())).setCentered());
    }

}
