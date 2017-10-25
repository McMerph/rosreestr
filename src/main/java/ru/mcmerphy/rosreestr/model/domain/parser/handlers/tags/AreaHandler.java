package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.math.BigDecimal;

public class AreaHandler extends TagHandler {

    public AreaHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Area"};
    }

    @Override
    protected void handleEndElement(String tagName) {
        try {
            getFlat().setTotalArea(new BigDecimal(getCleanParsedCharacters()));
        } catch (NumberFormatException e) {
            addError("Общая площадь не является валидным числом");
        }
    }

}
