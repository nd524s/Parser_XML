package com.epam.task5.builder;

import com.epam.task5.entity.Card;
import com.epam.task5.entity.GreetingCard;
import com.epam.task5.entity.MusicCard;
import com.epam.task5.entity.PromotionalCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Никита on 03.01.2016.
 */
public abstract class AbstractCardsBuilder {
    protected Set<Card> cards;

    public AbstractCardsBuilder() {
        cards = new HashSet<>();
    }

    public Set<Card> getCards(){
        return Collections.unmodifiableSet(cards);
    }

    abstract public void buildSetCards(String fileName);


}
