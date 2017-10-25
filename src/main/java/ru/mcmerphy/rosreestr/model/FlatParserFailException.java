package ru.mcmerphy.rosreestr.model;

import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Signals that flat parse is failed.
 */
public class FlatParserFailException extends Exception {

    private List<FlatParser> flatParsers = new ArrayList<>();

    public FlatParserFailException(FlatParser parser) {
        super("Ошибка при парсинге помещения из файла: " + parser.getFile().getPath());
        flatParsers.add(parser);
    }

    public FlatParserFailException(List<FlatParser> flatParsers) {
        this.flatParsers = flatParsers;
    }

    public List<FlatParser> getFlatParsers() {
        return flatParsers;
    }

}
