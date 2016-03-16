package com.epam.task5.sort;

import com.epam.task5.entity.Card;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Никита on 18.01.2016.
 */
public class CardTypeSort {
    public Map<String, HashSet<Card>> sortByCardType(Set<Card> cards) {
        Map<String, HashSet<Card>> map = new HashMap<>();
        map.put("GreetingCard", new HashSet<>());
        map.put("MusicCard", new HashSet<>());
        map.put("PromotionalCard", new HashSet<>());

        for (Card card : cards) {
            if ("GreetingCard".equals(card.getClass().getSimpleName())) {
                map.get("GreetingCard").add(card);
            } else if ("MusicCard".equals(card.getClass().getSimpleName())) {
                map.get("MusicCard").add(card);
            } else if ("PromotionalCard".equals(card.getClass().getSimpleName())) {
                map.get("PromotionalCard").add(card);
            }
        }
        return map;
    }
}
