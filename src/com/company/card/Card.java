package com.company.card;

import java.io.Serializable;

public class Card implements Comparable<Card>, Serializable {

    private int suit;
    private int rank;


    public Card(int rank, int suit){
        this.suit = suit;
        this.rank = rank;
    }

    public Card(int card){
        switch (card%100){
            case 2: this.rank = Rank.TWO; break;
            case 3: this.rank = Rank.THREE; break;
            case 4: this.rank = Rank.FOUR; break;
            case 5: this.rank = Rank.FIVE; break;
            case 6: this.rank = Rank.SIX; break;
            case 7: this.rank = Rank.SEVEN; break;
            case 8: this.rank = Rank.EIGHT; break;
            case 9: this.rank = Rank.NINE; break;
            case 10: this.rank = Rank.TEN; break;
            case 11: this.rank = Rank.JACK; break;
            case 12: this.rank = Rank.QUEEN; break;
            case 13: this.rank = Rank.KING; break;
            case 14: this.rank = Rank.ACE; break;
        }
        switch (card/100){
            case 1: this.suit = Suit.DIAMONDS;break;
            case 2: this.suit = Suit.CLUBS;break;
            case 3: this.suit = Suit.HEARTS;break;
            case 4: this.suit = Suit.SPADES;break;
        }
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public int compareTo(final Card o) {
        final int rankComparison = Integer.compare(this.rank, o.rank);
        return rankComparison != 0 ? rankComparison : Integer.compare(this.suit, o.suit);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return this.rank == card.rank && this.suit == card.suit;
    }

    public Integer getCardCode(){
        return this.rank + this.suit*100;
    }
}