package hu.iit.uni.miskolc.advanced.java.service;

import java.util.Map;

public class GreedImpl implements Greed{

    private static final int MIN_DICE_VALUE = 1;
    private static final int MAX_DICE_VALUE = 6;
    private static final int MAX_DICE_COUNT = 6;
    private static final int STRAIGHT_SCORE = 1200;
    private static final int THREE_PAIR_SCORE = 800;
    private static final int SINGLE_ONE_SCORE = 100;
    private static final int SINGLE_FIVE_SCORE = 50;

    private static final Map<Integer, Integer> TRIPLE_SCORES;
    private static final Map<Integer, Integer> TRIPLE_SCORE_MULTIPLIER;

    static {
        TRIPLE_SCORES = Map.of(
                1, 1000,
                2, 200,
                3, 300,
                4, 400,
                5, 500,
                6, 600
                );
        TRIPLE_SCORE_MULTIPLIER = Map.of(
                3, 1,
                4, 2,
                5, 4,
                6, 8
        );
    }

    @Override
    public int score(int[] dice) {
        int result = 0;
        this.checkDice(dice);
        int[] diceValueCounts = valueCounts(dice);
        if(isSingleOne(diceValueCounts)){
           result += SINGLE_ONE_SCORE;
        }
        if(isSingleFive(diceValueCounts)){
            result += SINGLE_FIVE_SCORE;
        }
        for(int diceValue = MIN_DICE_VALUE; diceValue <= MAX_DICE_VALUE; diceValue++){
            int diceCount = diceValueCounts[diceValue-1];
            result += TRIPLE_SCORE_MULTIPLIER.getOrDefault(diceCount, 0) * TRIPLE_SCORES.get(diceValue);
        }
        if(isStraight(diceValueCounts)){
            result = STRAIGHT_SCORE;
        }
        if(isThreePair(diceValueCounts)){
            result = THREE_PAIR_SCORE;
        }
        return result;
    }

    private void checkDice(int[] dice){
        if(dice.length > MAX_DICE_COUNT){
            throw new IllegalArgumentException("Too many dice!");
        }
        for(int die : dice) {
            if(die < MIN_DICE_VALUE || die > MAX_DICE_VALUE){
                throw new IllegalArgumentException("Invalid Die Value");
            }
        }

    }

    private int[] valueCounts(int[] dice){
        int[] result = new int[MAX_DICE_VALUE];
        for(int i = 0; i < result.length; i++){
            result[i] = countValue(dice, i+1);
        }
        return result;
    }

    private int countValue(int[] dice, int value){
        int result = 0;
        for(int die : dice) {
            if(die == value){
                result++;
            }
        }
        return result;
    }

    private boolean isSingleOne(int[] diceValueCounts){
        return diceValueCounts[0] == 1;
    }
    private boolean isSingleFive(int[] diceValueCounts){
        return diceValueCounts[4] == 1;
    }
    private boolean isStraight(int[] diceValueCounts){
        for(int valueCount : diceValueCounts){
            if(valueCount != 1){
                return false;
            }
        }
        return true;
    }

    private boolean isThreePair(int[] diceValueCounts){
        int numberOfPairs = 0;
        for(int valueCount: diceValueCounts){
            if(valueCount == 2){
                numberOfPairs++;
            }
        }
        return numberOfPairs == 3;
    }

}
