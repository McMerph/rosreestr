package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class PersonContentHandler extends OwnerHandler {

    public PersonContentHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right", "Owner", "Person", "Content"};
    }

}
