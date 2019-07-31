package se.tot.zonsnurra.api;

import se.tot.zonsnurra.domain.Range;
import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

class ApiUtil {

  private ApiUtil() {
  }

  static ZoneResponse collectToResponse(final ZoneResult<? extends TestMeasure> zoneResult) {
    return new ZoneResponse(
        zoneResponse(zoneResult.get(1)),
        zoneResponse(zoneResult.get(2)),
        zoneResponse(zoneResult.get(3)),
        zoneResponse(zoneResult.get(4)),
        zoneResponse(zoneResult.get(5)),
        zoneResponse(zoneResult.get(6)),
        zoneResponse(zoneResult.sweetSpot())
    );
  }

  private static ZoneResponse.Zone zoneResponse(final Range<? extends TestMeasure> zoneRange) {
    return new ZoneResponse.Zone(zoneRange.lowString(), zoneRange.highString());
  }
}
