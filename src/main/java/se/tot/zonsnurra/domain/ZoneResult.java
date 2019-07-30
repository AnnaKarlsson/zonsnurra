package se.tot.zonsnurra.domain;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class ZoneResult<T extends TestMeasure> {

  private static final int NBR_OF_ZONES = 6;

  private final List<Range<T>> zones;
  private final Range<T> sweetSpot;

  private ZoneResult(final List<Range<T>> zones, final Range<T> sweetSpot) {
    this.zones = requireNonNull(zones);
    if (zones.size() != NBR_OF_ZONES) {
      throw new IllegalArgumentException("There should be exact " + NBR_OF_ZONES + " zones");
    }
    this.sweetSpot = requireNonNull(sweetSpot);
  }

  public static <T extends TestMeasure> ZoneResult<T> of6ZonesWith(final List<Range<T>> zones, final Range<T> sweetSpot
  ) {
    return new ZoneResult<>(zones, sweetSpot);
  }

  public Range<T> get(final Integer zoneNbr) {
    requireNonNull(zoneNbr);
    if (0 < zoneNbr && zoneNbr <= NBR_OF_ZONES) {
      return zones.get(zoneNbr - 1);
    }
    throw new IllegalArgumentException("Number of zones is " + NBR_OF_ZONES + ", due to cannot get zone " + zoneNbr);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ZoneResult)) {
      return false;
    }
    final ZoneResult<?> that = (ZoneResult<?>) o;
    return Objects.equals(zones, that.zones) &&
        Objects.equals(sweetSpot, that.sweetSpot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(zones, sweetSpot);
  }

  @Override
  public String toString() {
    return "ZoneResult " + zones + " (sweet spot " + sweetSpot + ")";
  }

  public Range<T> sweetSpot() {
    return sweetSpot;
  }
}
