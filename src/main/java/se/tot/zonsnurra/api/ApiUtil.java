package se.tot.zonsnurra.api;

import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

class ApiUtil {

  private ApiUtil(){}

  static ZoneResponse collectToResponse(final ZoneResult<? extends TestMeasure> zoneResult) {
    return new ZoneResponse(
        zoneResult.get(1).jsonValue(),
        zoneResult.get(2).jsonValue(),
        zoneResult.get(3).jsonValue(),
        zoneResult.get(4).jsonValue(),
        zoneResult.get(5).jsonValue(),
        zoneResult.get(6).jsonValue(),
        zoneResult.sweetSpot().jsonValue()

    );
  }
}
