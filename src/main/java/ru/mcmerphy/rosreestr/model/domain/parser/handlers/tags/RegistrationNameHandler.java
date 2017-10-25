package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.Right;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class RegistrationNameHandler extends TagHandler {

    public RegistrationNameHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"ObjectRight", "Right", "Registration", "Name"};
    }

    @Override
    protected void handleEndElement(String tagName) {
        List<Right> rights = getFlat().getRights();
        Right right = rights.get(rights.size() - 1);
        right.setRegistration(getCleanParsedCharacters());
    }

}
