package com.epam.task5.handler;

import com.epam.task5.entity.*;
import com.epam.task5.exception.ElementNotFoundException;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Никита on 29.12.2015.
 */
public class CardsSAXHandler extends DefaultHandler {
    private Set<Card> cards;
    private GreetingCard greetingCard;
    private PromotionalCard promotionalCard;
    private MusicCard musicCard;
    private CardEnum currentEnum;
    private EnumSet<CardEnum> enumSet;
    private String flag;
    private static Logger log = Logger.getLogger(CardsSAXHandler.class);

    public CardsSAXHandler(){
        cards = new HashSet<>();
        enumSet = EnumSet.range(CardEnum.YEAR, CardEnum.SONG);
    }

    public Set<Card> getCards(){
        return cards;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
       if ("greeting-card".equals(localName)) {
           greetingCard = new GreetingCard();
           flag = "greeting-card";
       } else if ("promotional-card".equals(localName)) {
           promotionalCard = new PromotionalCard();
           promotionalCard.setAdvertisingPhone(attrs.getValue(0));
           flag = "promotional-card";
       } else if ("music-card".equals(localName)) {
           musicCard = new MusicCard();
           flag = "music-card";
       } else {
           CardEnum temp = CardEnum.valueOf(modifyString(localName));
           if (enumSet.contains(temp)) {
               currentEnum = temp;
           }
       }
    }

    public String modifyString(String localName) {
        Pattern pattern = Pattern.compile("[a-z]+-[a-z]+");
        Matcher matcher = pattern.matcher(localName);
        String result;

        if (matcher.matches()){
            String[] strings = localName.split("-");
            result = strings[0].toUpperCase() + "_" + strings[1].toUpperCase();
        } else {
            result = localName.toUpperCase();
        }
        return result;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        try {
            if ("greeting-card".equals(flag)) {
                constructGreetingCard(s);
            } else if ("promotional-card".equals(flag)) {
                constructPromotionalCard(s);
            } else if ("music-card".equals(flag)) {
                constructMusicCard(s);
            }
        }catch (ElementNotFoundException e) {
            log.error("Can not construct card object");
        }

    }

    public void constructGreetingCard(String s) throws ElementNotFoundException {
        if (currentEnum != null) {
            switch (currentEnum) {
                case YEAR:
                    greetingCard.setYear(s);
                    break;
                case THEMA:
                    greetingCard.setThema(s);
                    break;
                case SENDING:
                    greetingCard.setSending(Boolean.valueOf(s));
                    break;
                case COUNTRY:
                    greetingCard.setCountry(s);
                    break;
                case VALUABLE:
                    greetingCard.setValuable(s);
                    break;
                case NAME:
                    greetingCard.getAuthor().setName(s);
                    break;
                case BIRTH_DATE:
                    greetingCard.getAuthor().setBirthDate(s);
                    break;
                case BIRTH_PLACE:
                    greetingCard.getAuthor().setBirthPlace(s);
                    break;
                default:
                    throw new ElementNotFoundException("Element not found");
            }
        }
        currentEnum = null;
    }

    public void constructPromotionalCard(String s) throws ElementNotFoundException {
        if (currentEnum != null) {
            switch (currentEnum) {
                case YEAR:
                    promotionalCard.setYear(s);
                    break;
                case THEMA:
                    promotionalCard.setThema(s);
                    break;
                case SENDING:
                    promotionalCard.setSending(Boolean.valueOf(s));
                    break;
                case COUNTRY:
                    promotionalCard.setCountry(s);
                    break;
                case VALUABLE:
                    promotionalCard.setValuable(s);
                    break;
                case COMPANY_NAME:
                    promotionalCard.setCompanyName(s);
                    break;
                default:
                    throw new ElementNotFoundException("Element not found");
            }
        }
        currentEnum = null;
    }

    public void constructMusicCard(String s) throws ElementNotFoundException {
        if (currentEnum != null) {
            switch (currentEnum) {
                case YEAR:
                    musicCard.setYear(s);
                    break;
                case THEMA:
                    musicCard.setThema(s);
                    break;
                case SENDING:
                    musicCard.setSending(Boolean.valueOf(s));
                    break;
                case COUNTRY:
                    musicCard.setCountry(s);
                    break;
                case VALUABLE:
                    musicCard.setValuable(s);
                    break;
                case NAME:
                    musicCard.getAuthor().setName(s);
                    break;
                case BIRTH_DATE:
                    musicCard.getAuthor().setBirthDate(s);
                    break;
                case BIRTH_PLACE:
                    musicCard.getAuthor().setBirthPlace(s);
                    break;
                case SONG:
                    musicCard.setSong(s);
                    break;
                default:
                    throw new ElementNotFoundException("Element not found");
            }
        }
        currentEnum = null;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("greeting-card".equals(localName)) {
            cards.add(greetingCard);
        } else if ("promotional-card".equals(localName)) {
            cards.add(promotionalCard);
        } else if ("music-card".equals(localName)) {
            cards.add(musicCard);
        }
    }

}
