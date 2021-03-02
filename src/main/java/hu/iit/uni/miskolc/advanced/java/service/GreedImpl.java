package hu.iit.uni.miskolc.advanced.java.service;

public class GreedImpl implements Greed{



    @Override
    public int score(int[] dice) {
        this.checkDice(dice);
        return 0;
    }

    private void checkDice(int[] dice){
        if(dice.length > 6){
            throw new IllegalArgumentException("Too many dice!");
        }
        for(int die : dice) {
            if(die < 1 || die > 6){
                throw new IllegalArgumentException("Invalid Die Value");
            }
        }

    }
}
