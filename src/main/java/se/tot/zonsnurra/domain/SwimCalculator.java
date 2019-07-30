package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class SwimCalculator extends ZoneCalculator<Seconds> {

  private static final List<PercentRange> PERCENT_OF_TIME = Arrays.asList(
      PercentRange.of(0, 129),
      PercentRange.of(114, 129),
      PercentRange.of(106, 113),
      PercentRange.of(101, 105),
      PercentRange.of(97, 100),
      PercentRange.of(90, 96));

  @Override
  protected Function<Percent, Seconds> calcRange(final Seconds measure) {
    return p -> Seconds.ofSeconds(calc(measure, p));
  }

  private static long calc(final Seconds measure, final Percent p) {
    return p.multiplyAndRoundToLong(measure.toBigDecimal());
  }

  @Override
  protected Stream<PercentRange> percentRangeStream() {
    return PERCENT_OF_TIME.stream();
  }

}
