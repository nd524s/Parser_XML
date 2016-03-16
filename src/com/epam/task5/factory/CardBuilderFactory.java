package com.epam.task5.factory;

import com.epam.task5.builder.AbstractCardsBuilder;
import com.epam.task5.builder.CardsDOMBuilder;
import com.epam.task5.builder.CardsSAXBuilder;
import com.epam.task5.builder.CardsStAXBuilder;

/**
 * Created by Никита on 03.01.2016.
 */
public class CardBuilderFactory {
    private enum TypeParser {
        SAX,
        STAX,
        DOM
    }

    public AbstractCardsBuilder createCardBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new CardsDOMBuilder();
            case STAX:
                return new CardsStAXBuilder();
            case SAX:
                return new CardsSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}

