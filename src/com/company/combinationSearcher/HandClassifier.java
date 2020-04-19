package com.company.combinationSearcher;

import com.company.card.Card;
import com.company.card.Rank;

import java.util.*;
import java.util.stream.Collectors;

public class HandClassifier {

    private long classificationPower;


    public void setClassificationPower (final SortedSet<Card> cards){


        final RankGroup rankGroup = new RankGroup(cards);
        final SuitGroup suitGroup = new SuitGroup(cards);
        final Classifier handDetector = new Classifier(rankGroup, suitGroup, cards);
        Classification classification = handDetector.classify();
        final Iterator<Map.Entry<Integer, List<Card>>> handIterator = handDetector.getRankGroup().iterator();


        this.classificationPower = (long)(classification.getClassificationRank()) * 10000000000L;
/**/        switch (classification.getClassificationRank()){

            case ClassificationRank.HIGH_CARD:
            case ClassificationRank.FLUSH:
                iteration(handIterator, 5);
                break;
            case ClassificationRank.PAIR:
                iteration(handIterator, 4);
                break;
            case ClassificationRank.TWO_PAIR:
            case ClassificationRank.SET:
                iteration(handIterator, 3);
                break;
            case ClassificationRank.FULL_HOUSE:
            case ClassificationRank.FOUR_OF_A_KIND:
                iteration(handIterator, 2);
                break;
            case ClassificationRank.STRAIGHT:
            case ClassificationRank.STRAIGHT_FLUSH:
                this.classificationPower += classification.getClassifiedCards().last().getRank() * 100000000L;
                break;

            case ClassificationRank.WHEEL:
            case ClassificationRank.STRAIGHT_FLUSH_WHEEL:
            case ClassificationRank.ROYAL_FLUSH:
                break;

            default:
                throw new RuntimeException();
        } /**/
    }

    private void iteration (Iterator<Map.Entry<Integer, List<Card>>> handIterator, int iteration){
        int counter = 0;
        long step = 100000000L;
        while (counter < iteration && handIterator.hasNext()){
            this.classificationPower += handIterator.next().getKey() * step;
            step /= 100;
            counter++;
        }
    }



    public static Classification classifyPokerHand(final SortedSet<Card> cards) {
        final RankGroup rankGroup = new RankGroup(cards);
        final SuitGroup suitGroup = new SuitGroup(cards);
        final Classifier handDetector = new Classifier(rankGroup, suitGroup, cards);
        return handDetector.classify();
    }

    public long getClassificationPower() {
        return classificationPower;
    }

    public static class Classifier{
        private final RankGroup rankGroup;
        private final SuitGroup suitGroup;
        private final SortedSet<Card> cards;

        Classifier(final RankGroup rankGroup,
                            final SuitGroup suitGroup,
                            final SortedSet<Card> cards) {
            this.rankGroup = rankGroup;
            this.suitGroup = suitGroup;
            this.cards = Collections.unmodifiableSortedSet(cards);
        }

        public RankGroup getRankGroup() {
            return rankGroup;
        }

        public SuitGroup getSuitGroup() {
            return suitGroup;
        }

        public SortedSet<Card> getCards() {
            return cards;
        }

        private SortedSet<Card> calculateHighCards() {
            return new TreeSet<>(this.cards.stream().limit(5).collect(Collectors.toSet()));
        }

        private Classification isPair() {
            if (this.rankGroup.getPairCount() == 1) {
                final Iterator<Map.Entry<Integer, List<Card>>> rankGroup = this.rankGroup.iterator();
                final SortedSet<Card> cards = new TreeSet<>();
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                return new Classification(ClassificationRank.PAIR, cards);
            }
            return new Classification(ClassificationRank.HIGH_CARD, calculateHighCards());
        }

        private Classification detectTwoPair() {
            if (this.rankGroup.getPairCount() == 2) {
                final Iterator<Map.Entry<Integer, List<Card>>> rankGroup = this.rankGroup.iterator();
                final SortedSet<Card> cards = new TreeSet<>();
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                return new Classification(ClassificationRank.TWO_PAIR, cards);
            }
            return isPair();
        }

        private Classification isSet() {
            if (this.rankGroup.getSetCount() == 1) {
                final Iterator<Map.Entry<Integer, List<Card>>> rankGroup = this.rankGroup.iterator();
                final SortedSet<Card> cards = new TreeSet<>();
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                cards.addAll(rankGroup.next().getValue());
                return new Classification(ClassificationRank.SET, cards);
            }
            return detectTwoPair();
        }

        private Classification detectNormalStraight() {
            final Set<Integer> cardRanks = this.rankGroup.getRankMap().keySet();

            if (cardRanks.containsAll(Straights.STRAIGHT_TEN_TO_ACE)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_TEN_TO_ACE));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_NINE_TO_KING)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_NINE_TO_KING));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_EIGHT_TO_QUEEN)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_EIGHT_TO_QUEEN));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_SEVEN_TO_JACK)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_SEVEN_TO_JACK));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_SIX_TO_TEN)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_SIX_TO_TEN));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_FIVE_TO_NINE)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_FIVE_TO_NINE));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_FOUR_TO_EIGHT)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_FOUR_TO_EIGHT));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_THREE_TO_SEVEN)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_THREE_TO_SEVEN));
            } else if (cardRanks.containsAll(Straights.STRAIGHT_TWO_TO_SIX)) {
                return new Classification(ClassificationRank.STRAIGHT, calculateStraight(Straights.STRAIGHT_TWO_TO_SIX));
            }
            return isSet();
        }

        private SortedSet<Card> calculateStraight(final List<Integer> ranks) {
            final SortedSet<Card> results = new TreeSet<>();
            final Map<Integer, List<Card>> rankGroup = this.rankGroup.getRankMap();
            for (final Integer rank : ranks) {
                final List<Card> values = rankGroup.get(rank);
                if (values != null) {
                    results.add(values.iterator().next());
                }
            }
            return results;
        }

        private Classification detectWheel() {
            final List<Integer> wheelRanks = Arrays.asList(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE);
            final Set<Integer> handRanks = new TreeSet<>(this.rankGroup.getRankMap().keySet());
            handRanks.retainAll(wheelRanks);
            if (handRanks.containsAll(wheelRanks)) {
                final SortedSet<Card> cards = new TreeSet<>();
                for (final Map.Entry<Integer, List<Card>> entry : this.rankGroup.getRankMap().entrySet()) {
                    if (wheelRanks.contains(entry.getKey())) {
                        cards.add(entry.getValue().iterator().next());
                    }
                }
                if (cards.size() != 5) {
                    throw new RuntimeException("something went wrong!");
                }
                return new Classification(ClassificationRank.WHEEL, cards);
            }
            return detectNormalStraight();
        }

        private Classification detectFlush() {
            final Map<Integer, List<Card>> suitGroup = this.suitGroup.getSuitMap();
            for (final Map.Entry<Integer, List<Card>> entry : suitGroup.entrySet()) {
                if (entry.getValue().size() == 5) {
                    return new Classification(ClassificationRank.FLUSH, new TreeSet<>(entry.getValue()));
                }
            }
            return detectWheel();
        }

        private Classification detectFullHouse() {
            if (this.rankGroup.getSetCount() == 2 ||
                    (this.rankGroup.getSetCount() == 1 && this.rankGroup.getPairCount() >= 1)) {
                final Iterator<Map.Entry<Integer, List<Card>>> handRankIterator = this.rankGroup.iterator();
                final SortedSet<Card> cards = new TreeSet<>();
                cards.addAll(handRankIterator.next().getValue());
                cards.addAll(extractFullHousePair(handRankIterator));
                return new Classification(ClassificationRank.FULL_HOUSE, cards);
            }
            return detectFlush();
        }

        private static Collection<Card> extractFullHousePair(final Iterator<Map.Entry<Integer, List<Card>>> handRankIterator) {
            final List<Card> fullHousePair = new ArrayList<>();
            final List<Card> pairOrSet = handRankIterator.next().getValue();
            if (pairOrSet.size() == 3) {
                final Iterator<Card> remainingCardsIterator = pairOrSet.iterator();
                fullHousePair.add(remainingCardsIterator.next());
                fullHousePair.add(remainingCardsIterator.next());
            } else if (pairOrSet.size() == 2) {
                fullHousePair.addAll(pairOrSet);
            } else {
                throw new RuntimeException("Should not reach here!");
            }
            return fullHousePair;
        }

        private static Card extractQuadKicker(final Iterator<Map.Entry<Integer, List<Card>>> rankGroup) {
            if (!rankGroup.hasNext()) {
                throw new RuntimeException("No kicker to extract!");
            }
            final SortedSet<Card> remainingCards = new TreeSet<>();
            rankGroup.forEachRemaining(rankListEntry -> remainingCards.addAll(rankListEntry.getValue()));
            return remainingCards.last();
        }

        private Classification detectFourOfAKind() {
            if (this.rankGroup.getQuadCount() == 1) {
                final Iterator<Map.Entry<Integer, List<Card>>> rankGroup = this.rankGroup.iterator();
                final SortedSet<Card> cards = new TreeSet<>();
                cards.addAll(rankGroup.next().getValue());
                cards.add(extractQuadKicker(rankGroup));
                return new Classification(ClassificationRank.FOUR_OF_A_KIND, cards);
            }
            return detectFullHouse();
        }

        private Classification detectStraightFlush() {
            final Map<Integer, List<Card>> suitGroup = this.suitGroup.getSuitMap();
            for (final Map.Entry<Integer, List<Card>> entry : suitGroup.entrySet()) {
                if (entry.getValue().size() == 5) {
                    final Card[] cardArray = entry.getValue().toArray(new Card[entry.getValue().size()]);
                    for (int i = 0; i < cardArray.length - 1; i++) {
                        if (cardArray[i].getRank() != cardArray[i + 1].getRank() - 1) {
                            return detectFourOfAKind();
                        }
                    }
                    return new Classification(ClassificationRank.STRAIGHT_FLUSH, new TreeSet<>(entry.getValue()));
                }
            }
            return detectFourOfAKind();
        }

        private Classification detectSraightFlushWheel() {
            final List<Card> handCards = new ArrayList<>(this.cards);
            if (handCards.containsAll(Straights.STRAIGHT_WHEEL_SPADES)) {
                handCards.retainAll(Straights.STRAIGHT_WHEEL_SPADES);
                return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.STRAIGHT_WHEEL_HEARTS)) {
                handCards.retainAll(Straights.STRAIGHT_WHEEL_HEARTS);
                return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.STRAIGHT_WHEEL_CLUBS)) {
                handCards.retainAll(Straights.STRAIGHT_WHEEL_CLUBS);
                return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.STRAIGHT_WHEEL_DIAMONDS)) {
                handCards.retainAll(Straights.STRAIGHT_WHEEL_DIAMONDS);
                return new Classification(ClassificationRank.STRAIGHT_FLUSH_WHEEL, new TreeSet<>(handCards));
            }
            return detectStraightFlush();
        }

        private Classification detectRoyalFlush() {
            final List<Card> handCards = new ArrayList<>(this.cards);
            if (handCards.containsAll(Straights.ROYAL_FLUSH_SPADES)) {
                handCards.retainAll(Straights.ROYAL_FLUSH_SPADES);
                return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.ROYAL_FLUSH_HEARTS)) {
                handCards.retainAll(Straights.ROYAL_FLUSH_HEARTS);
                return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.ROYAL_FLUSH_CLUBS)) {
                handCards.retainAll(Straights.ROYAL_FLUSH_CLUBS);
                return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
            } else if (handCards.containsAll(Straights.ROYAL_FLUSH_DIAMONDS)) {
                handCards.retainAll(Straights.ROYAL_FLUSH_DIAMONDS);
                return new Classification(ClassificationRank.ROYAL_FLUSH, new TreeSet<>(handCards));
            }
            return detectSraightFlushWheel();
        }

        private Classification detectImpl() {
            return detectRoyalFlush();
        }

        private static void validateCards(final SortedSet<Card> cards) {
            if (cards.size() != 5) {
                throw new RuntimeException("Invalid cards: " + cards);
            }
        }

        public Classification classify() {
            final Classification result = detectImpl();
            validateCards(result.getClassifiedCards());
            return result;
        }
    }
}
