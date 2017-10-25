package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Apartment;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.Objects;

public class ApartmentHandler extends AttributesHandler {

    public ApartmentHandler(FlatParser parser) {
        super(parser);
    }

    @Override
    protected String[] getTagNames() {
        return new String[]{"Flat", "Address", "adrs:Apartment"};
    }

    @Override
    protected String getTagDescription() {
        return "Апартаменты";
    }

    @Override
    protected String[] getRequiredAttributesNames() {
        return new String[]{"Type", "Value"};
    }

    @Override
    protected void handleStartElement(String tagName, Attributes attributes) {
        Apartment apartment = new Apartment();

        String type = attributes.getValue(getRequiredAttributesNames()[0]);
        if (Objects.nonNull(type)) {
            apartment.setType(type);
        }

        String value = attributes.getValue(getRequiredAttributesNames()[1]);
        if (Objects.nonNull(value)) {
            apartment.setValue(value);
        }
        getFlat().getAddress().setApartment(apartment);
    }

}
