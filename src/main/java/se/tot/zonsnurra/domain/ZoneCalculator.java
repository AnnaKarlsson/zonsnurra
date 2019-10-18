package se.tot.zonsnurra.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class ZoneCalculator<T extends TestMeasure<T>> {

  public ZoneResult<T> calc(final T measure) {
    final List<Range<T>> zones;
    if (measure instanceof Seconds) {
      zones = time(measure);
    } else {
      zones = pulseAndWatt(measure);
    }

    final Range<T> sweetSpotRange = sweetSpot().toRange(calcRange(measure));
    return ZoneResult.of6ZonesWith(zones, sweetSpotRange);
  }

  private List<Range<T>> pulseAndWatt(final T measure) {
    final List<T> highZones = percentRangeStream()
        .map(pr -> pr.high(calcRange(measure)))
        .collect(toList());

    final List<Range<T>> zones = new ArrayList<>();

    for (int i = 0; i < highZones.size(); i++) {
      if (i == 0) {
        zones.add(new Range<>(measure.zero(), highZones.get(0)));
      } else {
        zones.add(new Range<>(highZones.get(i - 1).increment(), highZones.get(i)));
      }
    }
    return zones;
  }

  private List<Range<T>> time(final T measure) {
    final List<T> lowZones = percentRangeStream()
        .map(pr -> pr.low(calcRange(measure)))
        .collect(toList());

    final List<T> highZones = percentRangeStream()
        .map(pr -> pr.high(calcRange(measure)))
        .collect(toList());

    final List<Range<T>> zones = new ArrayList<>();

    for (int i = 0; i < highZones.size(); i++) {
      if (i == 0) {
        zones.add(new Range<>(highZones.get(i + 1).increment(), highZones.get(0)));
      } else if (i == highZones.size() - 1) {
        zones.add(new Range<>(lowZones.get(i), highZones.get(i)));
      } else {
        zones.add(new Range<>(highZones.get(i + 1).increment(), highZones.get(i)));
      }

    }
    return zones;
  }

  protected abstract Function<Percent, T> calcRange(final T measure);

  protected abstract Stream<PercentRange> percentRangeStream();

  protected abstract PercentRange sweetSpot();

}
