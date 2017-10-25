package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class GovernanceContentHandler extends OwnerHandler {

    public GovernanceContentHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right", "Owner", "Governance", "Content"};
    }

}
