package se.tot.zonsnurra;

import org.junit.Assert;
import org.junit.Test;
import se.tot.zonsnurra.domain.*;

public class RunningTimeCalculatorTest {

  private final RunningTimeCalculator calculator = new RunningTimeCalculator();
  private Seconds givenTime;
  private ZoneResult result;

  @Test
  public void test() {
    given_time(4, 51);
    when_calculate_time();
    assert_zone_is_between(1, time(0, 0), time(6, 34));
    assert_zone_is_between(2, time(5, 48), time(6, 34));
    assert_zone_is_between(3, time(5, 24), time(5, 45));
    assert_zone_is_between(4, time(5, 9), time(5, 21));
    assert_zone_is_between(5, time(4, 56), time(5, 6));
    assert_zone_is_between(6, time(4, 35), time(4, 53));
  }

  private void when_calculate_time() {
    result = calculator.calc(givenTime);
  }

  private void given_time(final int min, final int sec) {
    givenTime = Seconds.of(min, sec);
  }

  private <T extends TestMeasure> void assert_zone_is_between(final int zoneNbr, final T low, final T high) {
    final Range zoneRange = result.get(zoneNbr);
    Assert.assertEquals(Range.of(low, high), zoneRange);
  }

  private static Seconds time(final int min, final int sec) {
    return Seconds.of(min, sec);
  }

}