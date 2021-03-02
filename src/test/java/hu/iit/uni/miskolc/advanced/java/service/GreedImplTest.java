package hu.iit.uni.miskolc.advanced.java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class GreedImplTest {

    @InjectMocks
    private GreedImpl greed;

    @Test
    @DisplayName("score function throws exception for more than 6 dice")
    void scoreShouldThrowExceptionForTooManyDice() {
        // given
        final int[] tooManyDice = new int[]{1, 2, 3, 4, 5, 6, 7};
        // when
        assertThatThrownBy(() -> {
            greed.score(tooManyDice);
        }).isInstanceOf(IllegalArgumentException.class);
        // then
    }

    @Test
    @DisplayName("score method throws exception if a dice value less than 1")
    void scoreShouldThrowExceptionIfDiceValueIsTooLow() {
        // given
        final int[] diceWithTooLowValue = new int[]{1, 2, 3, 4, 5, 0};
        // when
        assertThatThrownBy(() -> {
            greed.score(diceWithTooLowValue);
        }).isInstanceOf(IllegalArgumentException.class);
        // then
    }

    @Test
    @DisplayName("score method throws exception if dice value is too high")
    void scoreShouldThrowExceptionIfDiceValueIsTooHigh() {
        // given
        final int[] diceWithTooHighValue = new int[]{1, 2, 3, 4, 5, 7};
        // when
        assertThatThrownBy(() -> {
            greed.score(diceWithTooHighValue);
        }).isInstanceOf(IllegalArgumentException.class);
        // then
    }

}