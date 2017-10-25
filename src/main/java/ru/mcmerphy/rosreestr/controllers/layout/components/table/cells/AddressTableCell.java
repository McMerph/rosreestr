package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import ru.mcmerphy.rosreestr.model.domain.Address;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;

public class AddressTableCell extends CenteredTextTableCell<FlatExcerpt, Address> {

    @Override
    protected String getTextFill(Address address) {
        return address.getRepresentation();
    }

    @Override
    protected boolean isDangerStyle(Address address) {
        return !address.isConsistent();
    }

}
