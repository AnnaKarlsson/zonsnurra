package se.tot.zonsnurra.domain;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Range<T extends TestMeasure> {

  protected final T low;
  protected final T high;

  protected Range(final T low, final T high) {
    this.low = requireNonNull(low);
    this.high = requireNonNull(high);
    if (low.compareTo(high) > 0) {
      throw new IllegalArgumentException("low must be less than high");
    }
  }

  public static <T extends TestMeasure> Range<T> of(final T low, final T high) {
    return new Range<>(low, high);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Range)) {
      return false;
    }
    final Range<?> range = (Range<?>) o;
    return low.equals(range.low) &&
        high.equals(range.high);
  }

  @Override
  public int hashCode() {
    return Objects.hash(low, high);
  }

  @Override
  public String toString() {
    return "Range: " + low + "-" + high;
  }


  public String jsonValue() {
    return low.jsonValue() + " - " + high.jsonValue();
  }
}
