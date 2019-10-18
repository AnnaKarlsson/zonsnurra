package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static se.tot.zonsnurra.domain.Percent.PERCENT_95;

@Component
public class BikeWattCalculator extends ZoneCalculator<Watt> {

  private static final List<PercentRange> PERCENT_RANGES = Arrays.asList(
      PercentRange.of(0, 55),
      PercentRange.of(55, 74),
      PercentRange.of(75, 89),
      PercentRange.of(90, 104),
      PercentRange.of(105, 120),
      PercentRange.of(120, 200));

  private static final PercentRange PERCENT_SWEET_SPOT = PercentRange.of(88,94);

  @Override
  protected Function<Percent, Watt> calcRange(final Watt measure) {
    return p -> Watt.of(calc(measure, p));
  }

  private static int calc(final Watt measure, final Percent p) {
    return p.multiplyAndRoundToInt(PERCENT_95.calc(measure));
  }

  @Override
  protected Stream<PercentRange> percentRangeStream() {
    return PERCENT_RANGES.stream();
  }

  @Override
  protected PercentRange sweetSpot() {
    return PERCENT_SWEET_SPOT;
  }
}
