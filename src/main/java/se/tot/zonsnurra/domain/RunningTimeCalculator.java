package se.tot.zonsnurra.domain;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Component
public class RunningTimeCalculator extends ZoneCalculator<Seconds> {

  private static final List<PercentRange> PERCENT_OF_TIME = Arrays.asList(
      PercentRange.of(0, 129),
      PercentRange.of(114, 129),
      PercentRange.of(106, 113),
      PercentRange.of(101, 105),
      PercentRange.of(97, 100),
      PercentRange.of(90, 96));

  @Override
  protected Range<Seconds> calcRange(final Seconds measure, final int zoneNbr) {
    final BigDecimal timePlus5percentInSeconds = Percent.of(105).calc(measure);
    return PERCENT_OF_TIME.get(zoneNbr - 1)
        .toRange(p -> secondsOf(timePlus5percentInSeconds, p));
  }

  private static Seconds secondsOf(final BigDecimal timePlus5percentInSeconds, final Percent p) {
    BigDecimal resultInSecondAndDecimals = p.multiply(timePlus5percentInSeconds);
    Long resultInSeconds = resultInSecondAndDecimals.setScale(0, RoundingMode.HALF_UP).longValueExact();
    return Seconds.ofSeconds(resultInSeconds);
  }

  @Override
  protected boolean handle(final Sport sport) {
    return Sport.RUNNING == sport;
  }
}

