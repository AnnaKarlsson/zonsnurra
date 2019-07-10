package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Component
public class BikeWattCalculator extends ZoneCalculator<Watt> {

  private static final List<PercentRange> PERCENT_RANGES = Arrays.asList(
      PercentRange.of(0, 55),
      PercentRange.of(55, 74),
      PercentRange.of(75, 89),
      PercentRange.of(90, 104),
      PercentRange.of(105, 120),
      PercentRange.of(120, 200));

  @Override
  protected Range<Watt> calcRange(final Watt measure, final int zoneNbr) {
    final BigDecimal measure95percent = Percent.of(95).calc(measure);
    return PERCENT_RANGES.get(zoneNbr-1).toRange(p -> {
      final int result = p.multiply(measure95percent).setScale(0, RoundingMode.HALF_UP).intValueExact();
      return Watt.of(result);
    });
  }

  @Override
  protected boolean handle(final Sport sport) {
    return Sport.BIKE == sport;
  }
}
