package se.tot.zonsnurra.domain;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class PercentRange extends Range<Percent> {

  public static final PercentRange SWEET_SPOT = new PercentRange(Percent.of(87), Percent.of(93));

  protected PercentRange(final Percent low, final Percent high) {
    super(low, high);
  }

  public static PercentRange of(final Integer low, final Integer high) {
    requireNonNull(low);
    requireNonNull(high);
    return new PercentRange(Percent.of(low), Percent.of(high));
  }

  public <T extends TestMeasure> Range<T> toRange(final Function<Percent, T> fkn) {
    return new Range<>(fkn.apply(low), fkn.apply(high));
  }

}
