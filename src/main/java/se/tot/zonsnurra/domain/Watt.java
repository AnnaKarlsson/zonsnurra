package se.tot.zonsnurra.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Watt extends TestMeasure<Watt> {

  public static final Watt ZERO = Watt.of(0);

  private final Integer value;

  private Watt(final Integer value) {
    this.value = requireNonNull(value);
    if (value < 0) {
      throw new IllegalArgumentException("Muse be positive");
    }
  }

  public static Watt of(final Integer value) {
    requireNonNull(value);
    return new Watt(value);
  }

  @Override
  public int compareTo(final Watt o) {
    return value.compareTo(o.value);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Watt)) {
      return false;
    }
    final Watt watt = (Watt) o;
    return Objects.equals(value, watt.value);
  }

  @Override
  public Watt increment() {
    return new Watt(value + 1);
  }

  @Override
  public Watt zero() {
    return ZERO;
  }

  public BigDecimal toBigDecimal() {
    return new BigDecimal(value);
  }

  @Override
  public String jsonValue() {
    return String.valueOf(value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "Watt " + value;
  }
}
