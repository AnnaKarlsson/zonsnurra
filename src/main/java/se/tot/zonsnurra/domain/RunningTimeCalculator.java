package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class RunningTimeCalculator extends ZoneCalculator<Seconds> {

  private static final List<PercentRange> PERCENT_OF_TIME = Arrays.asList(
      PercentRange.of(130, 135),
      PercentRange.of(114, 129),
      PercentRange.of(106, 113),
      PercentRange.of(101, 105),
      PercentRange.of(97, 100),
      PercentRange.of(90, 96));

  private static final PercentRange PERCENT_SWEET_SPOT = PercentRange.of(105,108);

  private static Seconds secondsOf(final BigDecimal timePlus5percentInSeconds, final Percent p) {
    final Long result = p.multiplyAndRoundToLong(timePlus5percentInSeconds);
    return Seconds.ofSeconds(result);
  }

  @Override
  protected Function<Percent, Seconds> calcRange(final Seconds measure) {
    return p -> secondsOf(Percent.PERCENT_105.calc(measure), p);
  }

  @Override
  protected Stream<PercentRange> percentRangeStream() {
    return PERCENT_OF_TIME.stream();
  }

  @Override
  protected PercentRange sweetSpot() {
    return PERCENT_SWEET_SPOT;
  }
}

