package com.epam.task5.handler;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Никита on 29.12.2015.
 */
public class CardsErrorHandler extends DefaultHandler {
    private static Logger log = Logger.getLogger(CardsErrorHandler.class);

    public CardsErrorHandler() {
    }

    public void warning(SAXParseException e) {
        log.warn(getLineAddress(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e) {
        log.error(getLineAddress(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        log.fatal(getLineAddress(e) + " - " + e.getMessage());
    }
    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}
