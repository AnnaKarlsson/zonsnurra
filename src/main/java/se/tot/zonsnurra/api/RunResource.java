package se.tot.zonsnurra.api;

import org.springframework.web.bind.annotation.PathVariable;
import se.tot.zonsnurra.domain.*;

import java.util.List;
import java.util.Map;

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
  public Map<String, Object> time(
      @PathVariable final Integer minutes,
      @PathVariable final Integer seconds
  ) {
    return collectToResponse(runningTimeCalculator.calc(Seconds.of(minutes, seconds)));
  }

  @GetJSON("/pulse/{pulse}")
  public Map<String, Object> pulse(@PathVariable final Integer pulse) {
    return collectToResponse(runningPulseCalculator.calc(Pulse.of(pulse)));
  }

}
