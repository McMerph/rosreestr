package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Street;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class StreetHandler extends AttributesHandler {

    public StreetHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:Street"};
    }

    @Override
    protected String getTagDescription() {
        return "Улица";
    }

    @Override
    protected String[] getRequiredAttributesNames() {
        return new String[]{"Type", "Name"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        List<String> attributesValues = parseAttributes(attributes);
        getFlat().getAddress().setStreet(new Street(attributesValues.get(0), attributesValues.get(1)));
    }

}
