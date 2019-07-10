package se.tot.zonsnurra.api;

import org.springframework.web.bind.annotation.PathVariable;
import se.tot.zonsnurra.domain.*;

import java.util.List;
import java.util.Map;

import static se.tot.zonsnurra.api.ApiUtil.collectToResponse;

@ApiResource("/bike")
public class BikeResource {

  private final BikePulseCalculator bikePulseCalculator;
  private final BikeWattCalculator bikeWattCalculator;

  public BikeResource(
      final BikePulseCalculator bikePulseCalculator,
      final BikeWattCalculator bikeWattCalculator
  ) {
    this.bikePulseCalculator = bikePulseCalculator;
    this.bikeWattCalculator = bikeWattCalculator;
  }


  @GetJSON("/watt/{watt}")
  public Map<String, Object> watt(@PathVariable final Integer watt) {
    return collectToResponse(bikeWattCalculator.calc(Watt.of(watt)));
  }

  @GetJSON("/pulse/{pulse}")
  public Map<String, Object> pulse(@PathVariable final Integer pulse) {
    return collectToResponse(bikePulseCalculator.calc(Pulse.of(pulse)));
  }

}
