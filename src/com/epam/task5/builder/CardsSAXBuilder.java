package com.epam.task5.builder;

import com.epam.task5.handler.CardsSAXHandler;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

/**
 * Created by Никита on 29.12.2015.
 */
public class CardsSAXBuilder extends AbstractCardsBuilder {
    private CardsSAXHandler cardsSAXHandler;
    private XMLReader reader;
    private static Logger log = Logger.getLogger(CardsSAXBuilder.class);

    public CardsSAXBuilder() {
        cardsSAXHandler = new CardsSAXHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
        } catch (SAXException e) {
            log.error("Can not create SAX_XMLReader object: " + e);
        }
        reader.setContentHandler(cardsSAXHandler);
    }

    public void buildSetCards(String filename) {
        try {
            reader.parse(filename);
        } catch (SAXException e) {
            log.error("SAX parser error: " + e);
        } catch (IOException e) {
            log.error("File error or I/O error: " + e);
        }
        cards = cardsSAXHandler.getCards();
    }
}
