package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class RunningPulseCalculator extends ZoneCalculator<Pulse> {

  private static final List<PercentRange> PERCENT_OF_PULSE = Arrays.asList(
      PercentRange.of(0, 85),
      PercentRange.of(85, 89),
      PercentRange.of(90, 94),
      PercentRange.of(95, 99),
      PercentRange.of(100, 102),
      PercentRange.of(103, 106));

  @Override
  protected Function<Percent, Pulse> calcRange(final Pulse measure) {
    return p -> Pulse.of(calc(measure, p));
  }

  private static int calc(final Pulse measure, final Percent p) {
    return p.multiplyAndRoundToInt(Percent.PERCENT_95.calc(measure));
  }

  @Override
  protected Stream<PercentRange> percentRangeStream() {
    return PERCENT_OF_PULSE.stream();
  }
}
