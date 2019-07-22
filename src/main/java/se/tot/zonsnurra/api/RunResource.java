package se.tot.zonsnurra.api;

import org.springframework.web.bind.annotation.PathVariable;
import se.tot.zonsnurra.domain.Pulse;
import se.tot.zonsnurra.domain.RunningPulseCalculator;
import se.tot.zonsnurra.domain.RunningTimeCalculator;
import se.tot.zonsnurra.domain.Seconds;

import static se.tot.zonsnurra.api.ApiUtil.collectToResponse;

@ApiResource("/run")
public class RunResource {

  private final RunningPulseCalculator runningPulseCalculator;
  private final RunningTimeCalculator runningTimeCalculator;

  public RunResource(
      final RunningPulseCalculator runningPulseCalculator,
      final RunningTimeCalculator runningTimeCalculator
  ) {
    this.runningPulseCalculator = runningPulseCalculator;
    this.runningTimeCalculator = runningTimeCalculator;
  }


  @GetJSON("/time/{minutes}/{seconds}")
  public ZoneResponse time(
      @PathVariable final Integer minutes,
      @PathVariable final Integer seconds
  ) {
    return collectToResponse(runningTimeCalculator.calc(Seconds.of(minutes, seconds)));
  }

  @GetJSON("/pulse/{pulse}")
  public ZoneResponse pulse(@PathVariable final Integer pulse) {
    return collectToResponse(runningPulseCalculator.calc(Pulse.of(pulse)));
  }

}
