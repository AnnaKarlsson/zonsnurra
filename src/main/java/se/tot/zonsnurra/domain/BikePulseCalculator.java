package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
public class BikePulseCalculator extends ZoneCalculator<Pulse> {

  private static final List<PercentRange> PERCENT_OF_PULSE = Arrays.asList(
      PercentRange.of(0, 81),
      PercentRange.of(81, 89),
      PercentRange.of(90, 93),
      PercentRange.of(94, 99),
      PercentRange.of(100, 102),
      PercentRange.of(103, 106));

  private static final PercentRange PERCENT_SWEET_SPOT = PercentRange.of(92,96);

  @Override
  protected Function<Percent, Pulse> calcRange(final Pulse measure) {
    return p -> {
      final BigDecimal pulseOf95percent = Percent.of(95).calc(measure);
      BigDecimal resultInDecimals = p.multiply(pulseOf95percent);
      Integer resultRounded = resultInDecimals.setScale(0, RoundingMode.HALF_UP).intValue();
      return Pulse.of(resultRounded);
    };
  }

  @Override
  protected Stream<PercentRange> percentRangeStream() {
    return PERCENT_OF_PULSE.stream();
  }

  @Override
  protected PercentRange sweetSpot() {
    return PERCENT_SWEET_SPOT;
  }

}
