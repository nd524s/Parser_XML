package com.epam.task5.builder;

import com.epam.task5.entity.*;
import com.epam.task5.exception.ElementNotFoundException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Никита on 03.01.2016.
 */
public class CardsDOMBuilder extends AbstractCardsBuilder {
    private DocumentBuilder documentBuilder;
    private static Logger log = Logger.getLogger(CardsDOMBuilder.class);

    public CardsDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("Parser configuration error: " + e);
        }
    }

    public void buildSetCards(String fileName) {
        Document doc;

        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList cardsList = root.getChildNodes();
            Element cardElement;
            for (int i = 0; i < cardsList.getLength(); i++) {
                if(cardsList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    cardElement = (Element) cardsList.item(i);
                    String localName = cardElement.getTagName();
                    Card card = buildCard(cardElement, localName);
                    cards.add(card);
                }
            }
        } catch (SAXException e) {
            log.error("Parsing failure: " + e);
        } catch (IOException e) {
            log.error("File error or I/O error: " + e);
        }
    }

    private Card buildCard(Element cardElement, String localName) {
        Card card = null;

        if ("greeting-card".equals(localName)) {
           card = buildGreetingCard(cardElement);
        } else if ("promotional-card".equals(localName)) {
            card = buildPromotionalCard(cardElement);
        } else if ("music-card".equals(localName)) {
            card = buildMusicCard(cardElement);
        }
        return card;
    }

    private Card buildGreetingCard(Element cardElement) {
        GreetingCard greetingCard = new GreetingCard();

        greetingCard = (GreetingCard) buildCommonPart(cardElement, greetingCard);
        Author author = greetingCard.getAuthor();
        Element element = (Element) cardElement.getElementsByTagName("author").item(0);
        author.setName(getElementTextContent(element, "name"));
        author.setBirthDate(getElementTextContent(element, "birth-date"));
        author.setBirthPlace(getElementTextContent(element, "birth-place"));
        return greetingCard;
    }

    private Card buildPromotionalCard(Element cardElement) {
        PromotionalCard promotionalCard = new PromotionalCard();

        promotionalCard = (PromotionalCard) buildCommonPart(cardElement, promotionalCard);
        promotionalCard.setCompanyName(getElementTextContent(cardElement, "company-name"));
        promotionalCard.setAdvertisingPhone(cardElement.getAttribute("advertising-phone"));
        return promotionalCard;
    }

    private Card buildMusicCard(Element cardElement) {
        MusicCard musicCard = new MusicCard();

        musicCard = (MusicCard) buildCommonPart(cardElement, musicCard);
        Author author = musicCard.getAuthor();
        Element element = (Element) cardElement.getElementsByTagName("author").item(0);
        author.setName(getElementTextContent(element, "name"));
        author.setBirthDate(getElementTextContent(element, "birth-date"));
        author.setBirthPlace(getElementTextContent(element, "birth-place"));
        musicCard.setSong(getElementTextContent(cardElement, "song"));
        return musicCard;
    }

    private Card buildCommonPart(Element cardElement, Card card) {
        card.setThema(getElementTextContent(cardElement, "thema"));
        card.setValuable(getElementTextContent(cardElement, "valuable"));
        card.setCountry(getElementTextContent(cardElement, "country"));
        card.setSending(Boolean.valueOf(getElementTextContent(cardElement, "sending")));
        card.setYear(getElementTextContent(cardElement, "year"));
        return card;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
