package com.company.combinationSearcher;

import com.company.card.Card;
import com.company.card.Rank;
import com.company.card.Suit;

import java.util.Arrays;
import java.util.List;

public class Straights {
    public static final List<Card> ROYAL_FLUSH_SPADES = Arrays.asList(new Card(Rank.ACE, Suit.SPADES),
            new Card(Rank.KING, Suit.SPADES),
            new Card(Rank.QUEEN, Suit.SPADES),
            new Card(Rank.JACK, Suit.SPADES),
            new Card(Rank.TEN, Suit.SPADES));


    public static final List<Card> ROYAL_FLUSH_HEARTS = Arrays.asList(new Card(Rank.ACE, Suit.HEARTS),
            new Card(Rank.KING, Suit.HEARTS),
            new Card(Rank.QUEEN, Suit.HEARTS),
            new Card(Rank.JACK, Suit.HEARTS),
            new Card(Rank.TEN, Suit.HEARTS));

    public static final List<Card> ROYAL_FLUSH_CLUBS = Arrays.asList(new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.KING, Suit.CLUBS),
            new Card(Rank.QUEEN, Suit.CLUBS),
            new Card(Rank.JACK, Suit.CLUBS),
            new Card(Rank.TEN, Suit.CLUBS));

    public static final List<Card> ROYAL_FLUSH_DIAMONDS = Arrays.asList(new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.KING, Suit.DIAMONDS),
            new Card(Rank.QUEEN, Suit.DIAMONDS),
            new Card(Rank.JACK, Suit.DIAMONDS),
            new Card(Rank.TEN, Suit.DIAMONDS));

    public static final List<Card> STRAIGHT_WHEEL_SPADES = Arrays.asList(new Card(Rank.ACE, Suit.SPADES),
            new Card(Rank.TWO, Suit.SPADES),
            new Card(Rank.THREE, Suit.SPADES),
            new Card(Rank.FOUR, Suit.SPADES),
            new Card(Rank.FIVE, Suit.SPADES));

    public static final List<Card> STRAIGHT_WHEEL_HEARTS = Arrays.asList(new Card(Rank.ACE, Suit.HEARTS),
            new Card(Rank.TWO, Suit.HEARTS),
            new Card(Rank.THREE, Suit.HEARTS),
            new Card(Rank.FOUR, Suit.HEARTS),
            new Card(Rank.FIVE, Suit.HEARTS));

    public static final List<Card> STRAIGHT_WHEEL_CLUBS = Arrays.asList(new Card(Rank.ACE, Suit.CLUBS),
            new Card(Rank.TWO, Suit.CLUBS),
            new Card(Rank.THREE, Suit.CLUBS),
            new Card(Rank.FOUR, Suit.CLUBS),
            new Card(Rank.FIVE, Suit.CLUBS));

    public static final List<Card> STRAIGHT_WHEEL_DIAMONDS = Arrays.asList(new Card(Rank.ACE, Suit.DIAMONDS),
            new Card(Rank.TWO, Suit.DIAMONDS),
            new Card(Rank.THREE, Suit.DIAMONDS),
            new Card(Rank.FOUR, Suit.DIAMONDS),
            new Card(Rank.FIVE, Suit.DIAMONDS));


    // Листы рангов флешей
    public static final List<Integer> STRAIGHT_TWO_TO_SIX = Arrays.asList(Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX);

    public static final List<Integer> STRAIGHT_THREE_TO_SEVEN = Arrays.asList(Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN);

    public static final List<Integer> STRAIGHT_FOUR_TO_EIGHT = Arrays.asList(Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT);

    public static final List<Integer> STRAIGHT_FIVE_TO_NINE = Arrays.asList(Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE);

    public static final List<Integer> STRAIGHT_SIX_TO_TEN = Arrays.asList(Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN);

    public static final List<Integer> STRAIGHT_SEVEN_TO_JACK = Arrays.asList(Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK);

    public static final List<Integer> STRAIGHT_EIGHT_TO_QUEEN = Arrays.asList(Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN);

    public static final List<Integer> STRAIGHT_NINE_TO_KING = Arrays.asList(Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING);

    public static final List<Integer> STRAIGHT_TEN_TO_ACE = Arrays.asList(Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING, Rank.ACE);

}
