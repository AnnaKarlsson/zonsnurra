package se.tot.zonsnurra.api;

import org.springframework.web.bind.annotation.PathVariable;
import se.tot.zonsnurra.domain.Seconds;
import se.tot.zonsnurra.domain.SwimCalculator;

import static se.tot.zonsnurra.api.ApiUtil.collectToResponse;

@ApiResource("/swim")
public class SwimResource {

  private final SwimCalculator swimCalculator;

  public SwimResource(final SwimCalculator swimCalculator) {
    this.swimCalculator = swimCalculator;
  }


  @GetJSON("/time/{minutes}/{seconds}")
  public ZoneResponse time(
      @PathVariable final Integer minutes,
      @PathVariable final Integer seconds
  ) {
    return collectToResponse(swimCalculator.calc(Seconds.of(minutes, seconds)));
  }
}
