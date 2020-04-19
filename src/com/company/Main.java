package com.company;

import com.company.card.Card;
import com.company.combinationSearcher.HandClassifier;

import java.util.Optional;
import java.util.SortedSet;

public class Main {

    public static void main(String[] args) {

        Hand.Builder builder = new Hand.Builder();

        builder
                .addCommunityCard(Optional.of(new Card(307)))
                .addCommunityCard(Optional.of(new Card(102)))
                .addCommunityCard(Optional.of(new Card(107)))
                .addCommunityCard(Optional.of(new Card(414)))
                .addCommunityCard(Optional.of(new Card(407)))
                .addHoleCard(Optional.of(new Card(114)))
                .addHoleCard(Optional.of(new Card(207)));

        Hand hand = new Hand(builder);

        System.out.println(HandClassifier.classifyPokerHand(hand.getCombinedCards()).toString());

        HandClassifier handClassifier = new HandClassifier();
        handClassifier.setClassificationPower(hand.getCombinedCards());
        System.out.println(handClassifier.getClassificationPower());

    }
}
