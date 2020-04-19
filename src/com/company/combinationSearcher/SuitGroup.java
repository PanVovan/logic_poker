package com.company.combinationSearcher;

import com.company.card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class SuitGroup implements Iterable<Map.Entry<Integer, List<Card>>>{

    private final Map<Integer, List<Card>> suitMap;


    public SuitGroup(final SortedSet<Card> cards) {
        this.suitMap = initSuitGroup(cards);
    }

    Map<Integer, List<Card>> getSuitMap() {
        return this.suitMap;
    }

    @Override
    public Iterator<Map.Entry<Integer, List<Card>>> iterator() {
        return this.suitMap.entrySet().iterator();
    }


    private static Map<Integer, List<Card>> initSuitGroup(final SortedSet<Card> cards) {
        //Возвращаем мапу, сгруппированную по масти
        return Collections.unmodifiableMap(new TreeMap<>(cards.stream().collect(Collectors.groupingBy(Card::getSuit))));
    }
}
