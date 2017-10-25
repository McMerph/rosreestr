package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Right;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

public class RightHandler extends TagHandler {

    public RightHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        getFlat().getRights().add(new Right());
    }

}
