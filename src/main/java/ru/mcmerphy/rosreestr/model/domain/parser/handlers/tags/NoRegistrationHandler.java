package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Right;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class NoRegistrationHandler extends TagHandler {

    public NoRegistrationHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right", "NoRegistration"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        List<Right> rights = getFlat().getRights();
        Right right = rights.get(rights.size() - 1);
        right.setNoRegistration();
    }

}
