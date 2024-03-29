package se.tot.zonsnurra.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Percent {

  public static final Percent PERCENT_95 = of(95);
  public static final Percent PERCENT_105 = of(105);

  private final Integer value;

  private Percent(final Integer value) {
    this.value = value;
    if (value < 0) {
      throw new IllegalArgumentException("Muse be positive");
    }
  }

  public static Percent of(final Integer value) {
    return new Percent(value);
  }

  public int compareTo(final Percent o) {
    return value.compareTo(o.value);
  }

  public BigDecimal toBigDecimal() {
    return BigDecimal.valueOf(percent());
  }

  public BigDecimal multiply(final BigDecimal number) {
    return toBigDecimal().multiply(number);
  }

  public long multiplyAndRoundToLong(final BigDecimal number) {
    return toBigDecimal().multiply(number)
        .setScale(0, RoundingMode.HALF_UP)
        .longValueExact();
  }

  public int multiplyAndRoundToInt(final BigDecimal number) {
    return toBigDecimal().multiply(number)
        .setScale(0, RoundingMode.HALF_UP)
        .intValueExact();
  }

  private float percent() {
    return (float) value / 100;
  }

  public BigDecimal calc(final TestMeasure measure) {
    return toBigDecimal().multiply(measure.toBigDecimal());
  }


}
