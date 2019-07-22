package se.tot.zonsnurra.api;

import org.springframework.web.bind.annotation.PathVariable;
import se.tot.zonsnurra.domain.BikePulseCalculator;
import se.tot.zonsnurra.domain.BikeWattCalculator;
import se.tot.zonsnurra.domain.Pulse;
import se.tot.zonsnurra.domain.Watt;

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
  public ZoneResponse watt(@PathVariable final Integer watt) {
    return collectToResponse(bikeWattCalculator.calc(Watt.of(watt)));
  }

  @GetJSON("/pulse/{pulse}")
  public ZoneResponse pulse(@PathVariable final Integer pulse) {
    return collectToResponse(bikePulseCalculator.calc(Pulse.of(pulse)));
  }

}
