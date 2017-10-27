package ru.mcmerphy.rosreestr.model.domain.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.*;
import ru.mcmerphy.rosreestr.model.domain.parser.handlers.tags.attributes.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Serves to produce {@link DefaultHandler} instance to parse flat.
 */
public class SaxHandlerProducer {

    private final List<TagHandler> tagHandlers = new ArrayList<>();

    public SaxHandlerProducer(FlatParser parser) {
        tagHandlers.add(new KpoksHandler(parser));
        tagHandlers.add(new AreaHandler(parser));
        tagHandlers.add(new RegionHandler(parser));
        tagHandlers.add(new DistrictHandler(parser));
        tagHandlers.add(new CityHandler(parser));
        tagHandlers.add(new LocalityHandler(parser));
        tagHandlers.add(new StreetHandler(parser));
        tagHandlers.add(new BuildingHandler(parser));
        tagHandlers.add(new ApartmentHandler(parser));
        tagHandlers.add(new RightHandler(parser));
        tagHandlers.add(new PersonContentHandler(parser));
        tagHandlers.add(new OrganizationContentHandler(parser));
        tagHandlers.add(new GovernanceContentHandler(parser));
        tagHandlers.add(new RegistrationNameHandler(parser));
        tagHandlers.add(new NoOwnerHandler(parser));
        tagHandlers.add(new NoRegistrationHandler(parser));
    }

    /**
     * @return {@link DefaultHandler} instance to parse flat.
     */
    public DefaultHandler produce() {
        return new DefaultHandler() {

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)
                    throws SAXException {
                tagHandlers.forEach(handler -> handler.startElement(qName, attributes));
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                tagHandlers.forEach(handler -> handler.endElement(qName));
            }

            @Override
            public void characters(char ch[], int start, int length) throws SAXException {
                tagHandlers.forEach(handler -> handler.characters(ch, start, length));
            }

        };
    }

}
