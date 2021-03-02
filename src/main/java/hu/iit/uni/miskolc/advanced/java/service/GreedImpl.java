package hu.iit.uni.miskolc.advanced.java.service;

public class GreedImpl implements Greed{

    private static final int MIN_DICE_VALUE = 1;
    private static final int MAX_DICE_VALUE = 6;
    private static final int MAX_DICE_COUNT = 6;

    @Override
    public int score(int[] dice) {
        int result = 0;
        this.checkDice(dice);
        int[] diceValueCounts = valueCounts(dice);
        if(diceValueCounts[0] == 1){
           result += 100;
        }
        if(diceValueCounts[4] == 1){
            result += 50;
        }
        if(diceValueCounts[0] == 3){
            result += 1000;
        }
        if(diceValueCounts[1] == 3){
            result += 200;
        }
        if(diceValueCounts[2] == 3){
            result += 300;
        }
        if(diceValueCounts[3] == 3){
            result += 400;
        }
        if(diceValueCounts[4] == 3){
            result +=  500;
        }
        if(diceValueCounts[5] == 3){
            result += 600;
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
}
