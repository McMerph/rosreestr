package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.District;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class DistrictHandler extends AttributesHandler {

    public DistrictHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:District"};
    }

    @Override
    protected String getTagDescription() {
        return "Район";
    }

    @Override
    protected String[] getRequiredAttributesNames() {
        return new String[]{"Type", "Name"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        List<String> attributesValues = parseAttributes(attributes);
        getFlat().getAddress().setDistrict(new District(attributesValues.get(0), attributesValues.get(1)));
    }

}
