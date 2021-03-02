package hu.iit.uni.miskolc.advanced.java.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

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
        assertThatThrownBy(() -> greed.score(tooManyDice)).isInstanceOf(IllegalArgumentException.class);
        // then
    }

    @Test
    @DisplayName("score method throws exception if a dice value less than 1")
    void scoreShouldThrowExceptionIfDiceValueIsTooLow() {
        // given
        final int[] diceWithTooLowValue = new int[]{1, 2, 3, 4, 5, 0};
        // when
        assertThatThrownBy(() -> greed.score(diceWithTooLowValue)).isInstanceOf(IllegalArgumentException.class);
        // then
    }

    @Test
    @DisplayName("score method throws exception if dice value is too high")
    void scoreShouldThrowExceptionIfDiceValueIsTooHigh() {
        // given
        final int[] diceWithTooHighValue = new int[]{1, 2, 3, 4, 5, 7};
        // when
        assertThatThrownBy(() -> greed.score(diceWithTooHighValue)).isInstanceOf(IllegalArgumentException.class);
        // then
    }

    @Test
    @DisplayName("checking single one rule")
    void scoreShouldBe100ForSingleOne(){
        // given
        final int expected = 100;
        final int[] dice = new int[] {1};
        // when
        final int actual = greed.score(dice);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("checking single five rule")
    void scoreShouldBe50ForSingleFive(){
        // given
        final int expected = 50;
        final int[] dice = new int[] {5};
        // when
        final int actual = greed.score(dice);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("manyOfKindScoreArgumentsProvider")
    @DisplayName("Many-of-a-kind Scores")
    void scoreShouldCalculateManyOfKindScores(int[] dice, int expected){
        // given
        // when
        final int actual = greed.score(dice);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> manyOfKindScoreArgumentsProvider(){
        return Stream.of(
                Arguments.of(new int[] {1,1,1},1000),
                Arguments.of(new int[] {2,2,2}, 200),
                Arguments.of(new int[] {3,3,3}, 300),
                Arguments.of(new int[] {4,4,4}, 400),
                Arguments.of(new int[] {5,5,5}, 500),
                Arguments.of(new int[] {6,6,6}, 600),
                Arguments.of(new int[] {1,1,1,1},2000),
                Arguments.of(new int[] {2,2,2,2}, 400),
                Arguments.of(new int[] {3,3,3,3}, 600),
                Arguments.of(new int[] {4,4,4,4}, 800),
                Arguments.of(new int[] {5,5,5,5}, 1000),
                Arguments.of(new int[] {6,6,6,6}, 1200),
                Arguments.of(new int[] {1,1,1,1,1},4000),
                Arguments.of(new int[] {2,2,2,2,2}, 800),
                Arguments.of(new int[] {3,3,3,3,3}, 1200),
                Arguments.of(new int[] {4,4,4,4,4}, 1600),
                Arguments.of(new int[] {5,5,5,5,5}, 2000),
                Arguments.of(new int[] {6,6,6,6,6}, 2400),
                Arguments.of(new int[] {1,1,1,1,1,1},8000),
                Arguments.of(new int[] {2,2,2,2,2,2}, 1600),
                Arguments.of(new int[] {3,3,3,3,3,3}, 2400),
                Arguments.of(new int[] {4,4,4,4,4,4}, 3200),
                Arguments.of(new int[] {5,5,5,5,5,5}, 4000),
                Arguments.of(new int[] {6,6,6,6,6,6}, 4800)
        );
    }
}