package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Building;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.List;

public class BuildingHandler extends AttributesHandler {

    public BuildingHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:Level1"};
    }

    @Override
    protected String getTagDescription() {
        return "Здание";
    }

    @Override
    protected String[] getRequiredAttributesNames() {
        return new String[]{"Type", "Value"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        List<String> attributesValues = parseAttributes(attributes);
        getFlat().getAddress().setBuilding(new Building(attributesValues.get(0), attributesValues.get(1)));
    }

}
