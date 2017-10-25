package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;
import ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.TagHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serves to handle attributes of specified tag.
 */
public abstract class AttributesHandler extends TagHandler {

    public AttributesHandler(FlatParser parser) {
        super(parser);
    }

    protected abstract String getTagDescription();

    protected abstract String[] getRequiredAttributesNames();

    protected List<String> parseAttributes(Attributes attributes) {
        if (isConsistent(attributes)) {
            return Arrays.stream(getRequiredAttributesNames())
                    .map(attributes::getValue)
                    .collect(Collectors.toList());
        } else {
            addError(getTagDescription() + ": отсутствует как минимум один из требуемых атрибутов");
            return new ArrayList<>();
        }
    }

    private boolean isConsistent(Attributes attributes) {
        return getAttributesQNames(attributes).containsAll(Arrays.asList(getRequiredAttributesNames()));
    }

    private List<String> getAttributesQNames(Attributes attributes) {
        List<String> attributesQNames = new ArrayList<>();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            attributesQNames.add(attributes.getQName(i));
        }

        return attributesQNames;
    }

}
