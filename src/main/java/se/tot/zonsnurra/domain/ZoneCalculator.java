package se.tot.zonsnurra.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class ZoneCalculator<T extends TestMeasure> {

  public ZoneResult<T> calc(final T measure) {
    final List<Range<T>> zones = percentRangeStream()
        .map(pr -> pr.toRange(calcRange(measure)))
        .collect(toList());

    final Range<T> sweetSpot = PercentRange.SWEET_SPOT.toRange(calcRange(measure));
    return ZoneResult.of(zones, sweetSpot);
  }

  protected abstract Function<Percent, T> calcRange(final T measure);

  protected abstract Stream<PercentRange> percentRangeStream();

}
