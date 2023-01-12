package se.tot.zonsnurra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.tot.zonsnurra.domain.Pulse;
import se.tot.zonsnurra.domain.Range;
import se.tot.zonsnurra.domain.RunningPulseCalculator;
import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

public class RunningPulseCalculatorTest {

  private final RunningPulseCalculator calculator = new RunningPulseCalculator();
  private Pulse givenPulse;
  private ZoneResult result;

  @Test
  public void test() {
    given_pulse(170);
    when_calculate_pulse();
    assert_zone_is_between(1, Pulse.of(0), Pulse.of(137));
    assert_zone_is_between(2, Pulse.of(138), Pulse.of(144));
    assert_zone_is_between(3, Pulse.of(145), Pulse.of(152));
    assert_zone_is_between(4, Pulse.of(153), Pulse.of(160));
    assert_zone_is_between(5, Pulse.of(161), Pulse.of(165));
    assert_zone_is_between(6, Pulse.of(166), Pulse.of(171));
  }

   private <T extends TestMeasure> void assert_zone_is_between(final int zoneNbr, final T low, final T high) {
    final Range zoneRange = result.get(zoneNbr);
    Assertions.assertEquals(Range.of(low, high), zoneRange);
  }

  private void when_calculate_pulse() {
    result = calculator.calc(givenPulse);
  }

  private void given_pulse(final int pulse) {
    this.givenPulse = Pulse.of(pulse);
  }

}
