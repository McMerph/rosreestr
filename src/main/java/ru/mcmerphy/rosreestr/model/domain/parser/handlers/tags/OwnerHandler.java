package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.Right;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public abstract class OwnerHandler extends TagHandler {

    public OwnerHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected void handleEndElement(String tagName) {
        List<Right> rights = getFlat().getRights();
        Right right = rights.get(rights.size() - 1);
        right.setOwner(getCleanParsedCharacters());
    }

}
