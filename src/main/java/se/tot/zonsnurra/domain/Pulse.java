package se.tot.zonsnurra.domain;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Pulse extends TestMeasure<Pulse> {

  private static final Pulse ZERO = new Pulse(0);

  private final Integer value;

  private Pulse(final Integer value) {
    this.value = requireNonNull(value);
    if (value < 0) {
      throw new IllegalArgumentException("Muse be positive");
    }
  }

  public static Pulse of(final Integer value) {
    requireNonNull(value);
    return new Pulse(value);
  }

  @Override
  public int compareTo(final Pulse o) {
    return value.compareTo(o.value);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pulse)) {
      return false;
    }
    final Pulse pulse = (Pulse) o;
    return value.equals(pulse.value);
  }

  @Override
  public Pulse increment() {
    return new Pulse(value + 1);
  }

  @Override
  public Pulse zero() {
    return ZERO;
  }

  @Override
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
    return "Pulse " + value;
  }
}
