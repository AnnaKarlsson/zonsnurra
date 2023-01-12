package se.tot.zonsnurra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.tot.zonsnurra.domain.Range;
import se.tot.zonsnurra.domain.Seconds;
import se.tot.zonsnurra.domain.SwimCalculator;
import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

public class SwimmingCalculatorTest {

  private final SwimCalculator calculator = new SwimCalculator();
  private Seconds givenTime;
  private ZoneResult result;

  @Test
  public void test() {
    given_time(1, 55);
    when_calculate_time();
    assert_zone_is_between(1, time(2, 29), time(2, 35));
    assert_zone_is_between(2, time(2, 11), time(2, 28));
    assert_zone_is_between(3, time(2, 2), time(2, 10));
    assert_zone_is_between(4, time(1, 56), time(2, 1));
    assert_zone_is_between(5, time(1, 51), time(1, 55));
    assert_zone_is_between(6, time(1, 43), time(1, 50));
  }

  private void when_calculate_time() {
    result = calculator.calc(givenTime);
  }

  private void given_time(final int min, final int sec) {
    givenTime = Seconds.of(min, sec);
  }

  private <T extends TestMeasure> void assert_zone_is_between(final int zoneNbr, final T low, final T high) {
    final Range zoneRange = result.get(zoneNbr);
    Assertions.assertEquals(Range.of(low, high), zoneRange);
  }

  private static Seconds time(final int min, final int sec) {
    return Seconds.of(min, sec);
  }

}
