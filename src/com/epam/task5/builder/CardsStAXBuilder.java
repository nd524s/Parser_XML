package com.epam.task5.builder;

import com.epam.task5.entity.*;
import com.epam.task5.entity.CardEnum;
import com.epam.task5.exception.ElementNotFoundException;
import com.epam.task5.handler.CardsSAXHandler;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Никита on 03.01.2016.
 */
public class CardsStAXBuilder extends AbstractCardsBuilder {
    private XMLInputFactory inputFactory;
    private CardsSAXHandler cardsSAXHandler= new CardsSAXHandler();
    private static Logger log = Logger.getLogger(CardsStAXBuilder.class);


    public CardsStAXBuilder() {
        inputFactory = XMLInputFactory.newFactory();
    }

    public void buildSetCards(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader;
        CardEnum currentCard;
        String name;

        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    currentCard = CardEnum.valueOf(cardsSAXHandler.modifyString(name));
                    if (currentCard == CardEnum.GREETING_CARD || currentCard == CardEnum.PROMOTIONAL_CARD || currentCard == CardEnum.MUSIC_CARD) {
                        Card card = buildCard(reader, currentCard);
                        cards.add(card);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            log.error("StAX parsing error! " + ex);
        } catch (FileNotFoundException ex) {
            log.error("File " + fileName + " not found! " + ex);
        } catch (ElementNotFoundException ex) {
            log.error("Unknown element was founded! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Card buildCard(XMLStreamReader reader, CardEnum currentCard) throws XMLStreamException, ElementNotFoundException {
        Card card = createCard(currentCard);
        String name;
        PromotionalCard promotionalCard;
        GreetingCard greetingCard;
        MusicCard musicCard;

        if("promotional-card".equals(currentCard.getValue())) {
            promotionalCard =(PromotionalCard)card;
            promotionalCard.setAdvertisingPhone(reader.getAttributeValue(null, CardEnum.ADVERTISING_PHONE.getValue()));
        }
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CardEnum.valueOf(cardsSAXHandler.modifyString(name))) {
                        case YEAR:
                            card.setYear(getXMLText(reader));
                            break;
                        case THEMA:
                            card.setThema(getXMLText(reader));
                            break;
                        case SENDING:
                            card.setSending(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case COUNTRY:
                            card.setCountry(getXMLText(reader));
                            break;
                        case VALUABLE:
                            card.setValuable(getXMLText(reader));
                            break;
                        case AUTHOR:
                            if (currentCard == CardEnum.GREETING_CARD) {
                                greetingCard = (GreetingCard) card;
                                greetingCard.setAuthor(getXMLAuthor(reader));
                            } else if (currentCard == CardEnum.MUSIC_CARD) {
                                musicCard = (MusicCard) card;
                                musicCard.setAuthor(getXMLAuthor(reader));
                            }
                            break;
                        case SONG:
                            musicCard = (MusicCard) card;
                            musicCard.setSong(getXMLText(reader));
                            break;
                        case COMPANY_NAME:
                            promotionalCard = (PromotionalCard) card;
                            promotionalCard.setCompanyName(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CardEnum.valueOf(cardsSAXHandler.modifyString(name)) == currentCard) {
                        return card;
                    }
                    break;
                }
            }
        throw new ElementNotFoundException();
    }

    private Author getXMLAuthor(XMLStreamReader reader) throws XMLStreamException, ElementNotFoundException {
        Author author = new Author();
        int type;
        String name;

        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (CardEnum.valueOf(cardsSAXHandler.modifyString(name))) {
                        case NAME:
                            author.setName(getXMLText(reader));
                            break;
                        case BIRTH_DATE:
                            author.setBirthDate(getXMLText(reader));
                            break;
                        case BIRTH_PLACE:
                            author.setBirthPlace(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (CardEnum.valueOf(cardsSAXHandler.modifyString(name)) == CardEnum.AUTHOR) {
                        return author;
                    }
                    break;
            }
        }
        throw new ElementNotFoundException();
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;

        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

    private Card createCard(CardEnum currentCard) {
        Card card = null;
        String cardName = currentCard.getValue();

        if ("greeting-card".equals(cardName)) {
            card = new GreetingCard();
        } else if ("promotional-card".equals(cardName)) {
            card = new PromotionalCard();
        } else if ("music-card".equals(cardName)) {
            card = new MusicCard();
        }
        return card;
    }
}
