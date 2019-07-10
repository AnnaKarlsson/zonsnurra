package se.tot.zonsnurra.api;

import se.tot.zonsnurra.domain.TestMeasure;
import se.tot.zonsnurra.domain.ZoneResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class ApiUtil {

  static Map<String,Object> collectToResponse(final ZoneResult<? extends TestMeasure> result) {
    return IntStream.rangeClosed(1, 6)
        .mapToObj(zone -> Map.entry("Zone " + zone , result.get(zone).jsonValue()))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }
}
