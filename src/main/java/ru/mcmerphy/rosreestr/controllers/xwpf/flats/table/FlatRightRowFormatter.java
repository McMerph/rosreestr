package ru.mcmerphy.rosreestr.controllers.xwpf.flats.table;

import ru.mcmerphy.rosreestr.controllers.util.Formatter;
import ru.mcmerphy.rosreestr.model.domain.Apartment;
import ru.mcmerphy.rosreestr.model.domain.Flat;
import ru.mcmerphy.rosreestr.model.domain.Right;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents flat right row in flats table.
 */
public class FlatRightRowFormatter extends TableRowFormatter {

    public FlatRightRowFormatter(Flat flat, Right right) {
        Apartment apartment = flat.getAddress().getApartment();
        cellFormatters.add(new CellFormatter(apartment.getValue()).setCentered());
        cellFormatters.add(new CellFormatter(right.getOwnerRepresentation()));
        cellFormatters.add(new CellFormatter(right.getRegistrationRepresentation()));
        cellFormatters.add(new CellFormatter(Formatter.format(flat.getTotalArea())).setCentered());

        handleMerging(flat, right);
    }

    @Override
    protected List<Integer> getColumnsToMerge() {
        List<Integer> columnsToMerge = new ArrayList<>();
        columnsToMerge.add(0);
        columnsToMerge.add(3);

        return columnsToMerge;
    }

    private void handleMerging(Flat flat, Right right) {
        List<Right> rights = flat.getRights();
        if (rights.size() > 1) {
            if (Objects.equals(rights.get(0), right)) {
                setMergingStart();
            } else {
                setMergingContinue();
            }
        }
    }

}
