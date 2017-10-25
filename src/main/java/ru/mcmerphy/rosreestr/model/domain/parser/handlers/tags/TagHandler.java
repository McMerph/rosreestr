package ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags;

import org.xml.sax.Attributes;
import ru.mcmerphy.rosreestr.model.domain.Flat;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serves to handle specified xml-tag.
 */
public abstract class TagHandler {

    protected final FlatParser parser;

    private final List<String> currentTagHierarchy = new ArrayList<>();
    private final List<String> searchedTagHierarchy;
    private String parsedCharacters;

    protected TagHandler(FlatParser parser) {
        this.parser = parser;
        this.searchedTagHierarchy = Arrays.asList(getTagNames());
    }

    /**
     * @return tag names hierarchy last element of which is a tag to handle.
     */
    protected abstract String[] getTagNames();

    public void startElement(String tagName, Attributes attributes) {
        currentTagHierarchy.add(tagName);
        if (isInto()) {
            handleStartElement(tagName, attributes);
        }
    }

    public void characters(char ch[], int start, int length) {
        if (isInto()) {
            String parsedChunk = new String(ch, start, length);
            if (Objects.isNull(parsedCharacters)) {
                parsedCharacters = parsedChunk;
            } else {
                parsedCharacters += parsedChunk;
            }
        }

    }

    public void endElement(String tagName) {
        if (isInto()) {
            handleEndElement(tagName);

        }

        for (int i = 0; i < currentTagHierarchy.size(); i++) {
            String searchedTagName = currentTagHierarchy.get(i);
            if (searchedTagName.equalsIgnoreCase(tagName)) {
                currentTagHierarchy.subList(i, currentTagHierarchy.size()).clear();
            }
        }

        parsedCharacters = null;
    }

    public String getCleanParsedCharacters() {
        return parsedCharacters
                .replaceAll("\r", " ")
                .replaceAll("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    /**
     * Handle start element.
     */
    protected void handleStartElement(String tagName, Attributes attributes) {
    }

    /**
     * Handle end element.
     */
    protected void handleEndElement(String tagName) {
    }

    protected Flat getFlat() {
        return parser.getFlat();
    }

    protected void addError(String error) {
        parser.getParseErrors().add(error);
    }

    private boolean isInto() {
        List<String> searchedUpperCasedTagHierarchy = searchedTagHierarchy.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        List<String> currentUpperCasedTagHierarchy = currentTagHierarchy.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        String lastSearchedTag = searchedUpperCasedTagHierarchy.get(searchedUpperCasedTagHierarchy.size() - 1);
        String lastCurrentTag = currentUpperCasedTagHierarchy.get(currentUpperCasedTagHierarchy.size() - 1);
        boolean equalsLastTags = Objects.equals(lastSearchedTag, lastCurrentTag);
        boolean sublist = Collections.
                indexOfSubList(currentUpperCasedTagHierarchy, searchedUpperCasedTagHierarchy) != -1;

        return sublist && equalsLastTags;
    }

}
