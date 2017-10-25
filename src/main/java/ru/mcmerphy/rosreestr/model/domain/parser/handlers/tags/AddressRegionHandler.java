package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class AddressRegionHandler extends TagHandler {

    public AddressRegionHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:Region"};
    }

    @Override
    protected void handleEndElement(String tagName) {
        getFlat().getAddress().setRegion(getCleanParsedCharacters());
    }

}
