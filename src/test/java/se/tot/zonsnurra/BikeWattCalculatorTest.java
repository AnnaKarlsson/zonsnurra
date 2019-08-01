package se.tot.zonsnurra;

import org.junit.Assert;
import org.junit.Test;
import se.tot.zonsnurra.domain.*;

public class BikeWattCalculatorTest {

  private final BikeWattCalculator calculator = new BikeWattCalculator();
  private Watt given;
  private ZoneResult result;

  @Test
  public void test() {
    given_watt(250);
    when_calculate();
    assert_zone_is_between(1, Watt.ZERO, Watt.of(131));
    assert_zone_is_between(2, Watt.of(132), Watt.of(176));
    assert_zone_is_between(3, Watt.of(177), Watt.of(211));
    assert_zone_is_between(4, Watt.of(212), Watt.of(247));
    assert_zone_is_between(5, Watt.of(248), Watt.of(285));
    assert_zone_is_between(6, Watt.of(286), Watt.of(475));
  }

   private <T extends TestMeasure> void assert_zone_is_between(final int zoneNbr, final T low, final T high) {
    final Range zoneRange = result.get(zoneNbr);
    Assert.assertEquals(Range.of(low, high), zoneRange);
  }

  private void when_calculate() {
    result = calculator.calc(given);
  }

  private void given_watt(final int watt) {
    this.given = Watt.of(watt);
  }

}