package com.company;

import com.company.card.Card;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hand {

    private final SortedSet<Card> combinedCards;

    public Hand(Builder builder) {
        this.combinedCards = builder.build();
    }

    public SortedSet<Card> getCombinedCards() {
        return combinedCards;
    }

    public static class Builder {

        private final SortedSet<Card> holeCards;
        private final SortedSet<Card> communityCards;

        public Builder() {
            this.holeCards = new TreeSet<>();
            this.communityCards = new TreeSet<>();
        }

        public Builder addHoleCard(final Optional<Card> card) {
            this.holeCards.add(card.orElseThrow(IllegalStateException::new));
            return this;
        }

        public Builder addCommunityCard(final Optional<Card> card) {
            this.communityCards.add(card.orElseThrow(IllegalStateException::new));
            return this;
        }

        public Builder addCommunityCards(final Set<Optional<Card>> cards) {
            final Stream<Card> cardStream = cards.stream().
                    flatMap(optionalCard -> optionalCard.map((Stream::of)).orElseThrow(IllegalStateException::new));
            this.communityCards.addAll(cardStream.collect(Collectors.toSet()));
            return this;
        }

        public SortedSet<Card> build() {

            return Collections.unmodifiableSortedSet(init(holeCards, communityCards));
        }

        private static SortedSet<Card> init(final SortedSet<Card> holeCards,
                                            final SortedSet<Card> communityCards) {
            final SortedSet<Card> combinedCards = new TreeSet<>();
            combinedCards.addAll(holeCards);
            combinedCards.addAll(communityCards);
            return Collections.unmodifiableSortedSet(combinedCards);
        }

    }




}
