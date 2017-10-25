package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class KpoksHandler extends TagHandler {

    public KpoksHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"KPOKS"};
    }

    @Override
    protected void handleEndElement(String tagName) {
        parser.setFlatParsed();
    }

}
