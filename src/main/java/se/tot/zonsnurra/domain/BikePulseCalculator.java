package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Component
public class BikePulseCalculator extends ZoneCalculator<Pulse> {

  private static final List<PercentRange> PERCENT_OF_PULSE = Arrays.asList(
      PercentRange.of(0, 81),
      PercentRange.of(81, 89),
      PercentRange.of(90, 93),
      PercentRange.of(94, 99),
      PercentRange.of(100, 102),
      PercentRange.of(103, 106));

  @Override
  protected Range<Pulse> calcRange(final Pulse measure, final int zoneNbr) {
    final BigDecimal pulseOf95percent = Percent.of(95).calc(measure);
    return PERCENT_OF_PULSE.get(zoneNbr - 1).toRange(p -> {
      BigDecimal resultInDecimals = p.multiply(pulseOf95percent);
      Integer resultRounded = resultInDecimals.setScale(0, RoundingMode.HALF_UP).intValue();
      return Pulse.of(resultRounded);
    });
  }

  @Override
  protected boolean handle(final Sport sport) {
    return Sport.BIKE == sport;
  }
}
