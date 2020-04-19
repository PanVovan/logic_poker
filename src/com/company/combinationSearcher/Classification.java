package com.company.combinationSearcher;

import com.company.card.Card;

import java.util.Collections;
import java.util.SortedSet;

public class Classification {

    private final Integer classificationRank;
    private final SortedSet<Card> classifiedCards;

    Classification(final Integer classificationRank,
                   final SortedSet<Card> cards) {
        this.classificationRank = classificationRank;
        this.classifiedCards = Collections.unmodifiableSortedSet(cards);
    }

    public SortedSet<Card> getClassifiedCards() {
        return this.classifiedCards;
    }

    public Integer getClassificationRank() {
        return this.classificationRank;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder= new StringBuilder(this.classificationRank.toString() + " ");
        this.classifiedCards.stream().forEach(card -> stringBuilder.append(card.getCardCode().toString() + " "));
        return stringBuilder.toString();
    }
}
