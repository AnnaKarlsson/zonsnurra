package se.tot.zonsnurra.domain;

public abstract class ZoneCalculator<T extends TestMeasure> {

  public ZoneResult<T> calc(final T measure) {
    return ZoneResult.of(
        calcRange(measure, 1),
        calcRange(measure, 2),
        calcRange(measure, 3),
        calcRange(measure, 4),
        calcRange(measure, 5),
        calcRange(measure, 6),
        calcRange(measure, 7));
  }

  protected abstract Range<T> calcRange(final T measure, final int zoneNbr);

  protected abstract boolean handle(final Sport sport);
}
