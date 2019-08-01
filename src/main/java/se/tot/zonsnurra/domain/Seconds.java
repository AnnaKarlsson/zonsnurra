package se.tot.zonsnurra.domain;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class Seconds extends TestMeasure<Seconds> {

  private static final Seconds ZERO = Seconds.ofSeconds(0L);

  private final Duration duration;

  private Seconds(final Duration duration) {
    this.duration = requireNonNull(duration);
  }

  public static Seconds ofSeconds(final Long seconds) {
    requireNonNull(seconds);
    return new Seconds(Duration.ofSeconds(seconds));
  }

  public static Seconds of(final Integer minutes, final Integer seconds) {
    requireNonNull(minutes);
    requireNonNull(seconds);
    return new Seconds(Duration.ofSeconds(minutes * 60 + (long) seconds));
  }

  @Override
  public Seconds increment() {
    return new Seconds(duration.plusSeconds(1));
  }

  @Override
  public Seconds zero() {
    return ZERO;
  }

  @Override
  public BigDecimal toBigDecimal() {
    return new BigDecimal(duration.getSeconds());
  }

  @Override
  public String jsonValue() {
    if (this.equals(ZERO)) {
      return "";
    }
    return toString();
  }

  public Seconds calc(final Percent p) {
    final long result = round(p.calc(this));
    return Seconds.ofSeconds(result);
  }

  @Override
  public int compareTo(final Seconds o) {
    requireNonNull(o);
    return toInteger().compareTo(o.toInteger());
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seconds)) {
      return false;
    }
    final Seconds seconds = (Seconds) o;
    return Objects.equals(duration, seconds.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(duration);
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d",
        duration.getSeconds() / 60,
        duration.getSeconds() % 60);
  }
}
