package se.tot.zonsnurra.domain;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class PercentRange {

  static final PercentRange SWEET_SPOT_TIME = new PercentRange(Percent.of(105), Percent.of(108));
  static final PercentRange SWEET_SPOT_DEFAULT = new PercentRange(Percent.of(91), Percent.of(98));
  private final Percent low;
  private final Percent high;

  private PercentRange(final Percent low, final Percent high) {
    this.low = requireNonNull(low);
    this.high = requireNonNull(high);
    if (low.compareTo(high) > 0) {
      throw new IllegalArgumentException("low must be less than high");
    }
  }

  public static PercentRange of(final Integer low, final Integer high) {
    requireNonNull(low);
    requireNonNull(high);
    return new PercentRange(Percent.of(low), Percent.of(high));
  }

  public <T extends TestMeasure> Range<T> toRange(final Function<Percent, T> fkn) {
    return new Range<>(fkn.apply(low), fkn.apply(high));
  }

  public <T extends TestMeasure> T high(final Function<Percent, T> fkn) {
    return fkn.apply(high);
  }

  public <T extends TestMeasure> T low(final Function<Percent, T> fkn) {
    return fkn.apply(low);
  }

}
