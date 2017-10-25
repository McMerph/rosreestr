package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class OrganizationContentHandler extends OwnerHandler {

    public OrganizationContentHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right", "Owner", "Organization", "Content"};
    }

}
