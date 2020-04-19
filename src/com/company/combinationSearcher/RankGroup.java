package com.company.combinationSearcher;

import com.company.card.Card;

import java.util.*;
import java.util.stream.Collectors;

public class RankGroup implements Iterable<Map.Entry<Integer, List<Card>>>{

    private final Map<Integer, List<Card>> rankMap;
    private final int quadCount;
    private final int setCount;
    private final int pairCount;

    public RankGroup(final SortedSet<Card> cards) {
        this.rankMap = initRankGroup(cards);
        this.quadCount = groupCount(4);
        this.setCount = groupCount(3);
        this.pairCount = groupCount(2);
    }

    Map<Integer, List<Card>> getRankMap() {
        return this.rankMap;
    }

    int getQuadCount() {
        return this.quadCount;
    }

    int getSetCount() {
        return this.setCount;
    }

    int getPairCount() {
        return this.pairCount;
    }

    //Инициализация мапы группы рангов
    private static Map<Integer, List<Card>> initRankGroup(final SortedSet<Card> cards) {

        //Условие сортировки мапы
        final Comparator<Map.Entry<Integer, List<Card>>> valueComparator =
                (o1, o2) -> o2.getValue().size() == o1.getValue().size() ? o2.getKey() - o1.getKey() :
                        o2.getValue().size() - o1.getValue().size();

        //Лист мапов
        final List<Map.Entry<Integer, List<Card>>> listOfEntries =
                new ArrayList<>(cards.stream().collect(Collectors.groupingBy(Card::getRank)).entrySet());

        //Отсортируем лист листов для создания из них карты по условию
        Collections.sort(listOfEntries, valueComparator);

        final LinkedHashMap<Integer, List<Card>> sortedResults = new LinkedHashMap<>();

        for (final Map.Entry<Integer, List<Card>> entry : listOfEntries) {
            sortedResults.put(entry.getKey(), entry.getValue());
        }

        return Collections.unmodifiableMap(sortedResults);
    }

    private int groupCount(final int groupSize) {
        int counter = 0;
        for (Map.Entry<Integer, List<Card>> i: rankMap.entrySet()){
            if(i.getValue().size() == groupSize)
                counter++;
        }
        return counter;
    }

    @Override
    public Iterator<Map.Entry<Integer, List<Card>>> iterator() {
        return this.rankMap.entrySet().iterator();
    }

}
