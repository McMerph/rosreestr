package ru.mcmerphy.rosreestr.model.domain.parser;

import org.xml.sax.InputSource;
import ru.mcmerphy.rosreestr.model.FlatParserFailException;
import ru.mcmerphy.rosreestr.model.domain.Flat;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves to parse {@link Flat} instance from .xml-file.
 */
public class FlatParser {

    private final File file;
    private final Flat flat;
    private final List<String> parseErrors = new ArrayList<>();

    private boolean flatParsed = false;

    public FlatParser(File file) {
        this.file = file;
        this.flat = new Flat();
    }

    /**
     * Parse file to get {@link Flat} instance.
     *
     * @throws FlatParserFailException on parser fail.
     */
    public void parse() throws FlatParserFailException {
        try {
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "UTF-8");
            InputSource inputSource = new InputSource(reader);
            inputSource.setEncoding("UTF-8");
            SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
            saxParser.parse(inputSource, new SaxHandlerProducer(this).produce());
        } catch (Exception e) {
            parseErrors.add("Ошибка ввода-вывода");
            throw new FlatParserFailException(this);
        }
        handleParseResult();
    }

    private void handleParseResult() throws FlatParserFailException {
        if (!flatParsed) {
            parseErrors.add("Не является помещением");
        }
        if (!parseErrors.isEmpty()) {
            throw new FlatParserFailException(this);
        }
    }

    public void setFlatParsed() {
        this.flatParsed = true;
    }

    public boolean isFlatParsed() {
        return parseErrors.isEmpty();
    }

    public File getFile() {
        return file;
    }

    public List<String> getParseErrors() {
        return parseErrors;
    }

    public Flat getFlat() {
        return flat;
    }

}
