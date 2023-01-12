package se.tot.zonsnurra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.tot.zonsnurra.domain.BikePulseCalculator;
import se.tot.zonsnurra.domain.Pulse;
import se.tot.zonsnurra.domain.Range;
import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

public class BikePulseCalculatorTest {

  private final BikePulseCalculator calculator = new BikePulseCalculator();
  private Pulse given;
  private ZoneResult result;

  @Test
  public void test() {
    given_pulse(176);
    when_calculate_pulse();
    assert_zone_is_between(1, Pulse.of(0), Pulse.of(135));
    assert_zone_is_between(2, Pulse.of(136), Pulse.of(149));
    assert_zone_is_between(3, Pulse.of(150), Pulse.of(155));
    assert_zone_is_between(4, Pulse.of(156), Pulse.of(166));
    assert_zone_is_between(5, Pulse.of(167), Pulse.of(171));
    assert_zone_is_between(6, Pulse.of(172), Pulse.of(177));
  }

   private <T extends TestMeasure> void assert_zone_is_between(final int zoneNbr, final T low, final T high) {
    final Range zoneRange = result.get(zoneNbr);
    Assertions.assertEquals(Range.of(low, high), zoneRange);
  }

  private void when_calculate_pulse() {
    result = calculator.calc(given);
  }

  private void given_pulse(final int pulse) {
    this.given = Pulse.of(pulse);
  }

}
