package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Locality;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class CityHandler extends AttributesHandler {

    public CityHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:City"};
    }

    @Override
    protected String getTagDescription() {
        return "Местность";
    }

    @Override
    protected String[] getRequiredAttributesNames() {
        return new String[]{"Type", "Name"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        List<String> attributesValues = parseAttributes(attributes);
        getFlat().getAddress().setLocality(new Locality(attributesValues.get(0), attributesValues.get(1)));
    }

}
