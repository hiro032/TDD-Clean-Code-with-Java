package nextstep.ladder.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LinesTest {

    /*
    setUp Lines
    0      1      2      3      4      5
    |------|      |------|      |      |
    |      |      |      |------|      |
    |------|      |------|      |------|
    0      1      4      3      5      2
     */
    private Lines lines;

    @BeforeEach
    void setUp() {
        List<Line> lineList = Arrays.asList(
                Line.from(
                    Arrays.asList(
                            Column.head(Boolean.TRUE),
                            Column.body(Boolean.TRUE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.TRUE),
                            Column.body(Boolean.TRUE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.FALSE),
                            Column.tail(Boolean.FALSE)
                    )
                ),
                Line.from(
                    Arrays.asList(
                            Column.head(Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.TRUE),
                            Column.body(Boolean.TRUE, Boolean.FALSE),
                            Column.tail(Boolean.FALSE)
                    )
                ),
                Line.from(
                    Arrays.asList(
                            Column.head(Boolean.TRUE),
                            Column.body(Boolean.TRUE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.TRUE),
                            Column.body(Boolean.TRUE, Boolean.FALSE),
                            Column.body(Boolean.FALSE, Boolean.TRUE),
                            Column.tail(Boolean.TRUE)
                    )
                )
        );

        lines = Lines.from(lineList);
    }

    @ParameterizedTest
    @DisplayName("Lines ??? ??????????????? 1???????????? ????????? ????????????")
    @NullAndEmptySource
    void createException(List<Line> lines) {
        assertThatThrownBy(() -> Lines.from(lines)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("?????? index??? ?????? ?????? index??? ????????????")
    @MethodSource
    void resultIndex(int start, int result) {
        assertThat(lines.resultIndex(start)).isEqualTo(result);
    }

    static Stream<Arguments> resultIndex() {
        return Stream.of(
                Arguments.of(
                        0, 0
                ),
                Arguments.of(
                        1, 1
                ),
                Arguments.of(
                        2, 5
                ),
                Arguments.of(
                        3, 3
                ),
                Arguments.of(
                        4, 2
                ),
                Arguments.of(
                        5, 4
                )
        );
    }
}