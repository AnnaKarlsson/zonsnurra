package se.tot.zonsnurra.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ZoneResult<T extends TestMeasure> {

  private final List<Range<T>> zones;

  private ZoneResult(
      final Range<T> h1, final Range<T> h2, final Range<T> h3,
      final Range<T> h4, final Range<T> h5, final Range<T> h6
  ) {
    this.zones = Arrays.asList(h1, h2, h3, h4, h5, h6);
  }

  public static <T extends TestMeasure> ZoneResult<T> of(
      final Range<T> range1, final Range<T> range2, final Range<T> range3,
      final Range<T> range4, final Range<T> range5, final Range<T> range6
  ) {
    return new ZoneResult<>(range1, range2, range3, range4, range5, range6);
  }

  public Integer nbrOfZones() {
    return zones.size();
  }

  public Range<T> get(final Integer zoneNbr) {
    requireNonNull(zoneNbr);
    if (0 < zoneNbr && zoneNbr <= nbrOfZones()) {
      return zones.get(zoneNbr - 1);
    }
    throw new IllegalArgumentException("Number of zones is " + nbrOfZones() + ", due to cannot get zone " + zoneNbr);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ZoneResult)) {
      return false;
    }
    final ZoneResult that = (ZoneResult) o;
    return Objects.equals(zones, that.zones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zones);
  }

  @Override
  public String toString() {
    return "ZoneResult " + zones;
  }
}
