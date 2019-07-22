package se.tot.zonsnurra.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ZoneResponse {

  @JsonProperty("Zone 1")
  public final String zone1;

  @JsonProperty("Zone 2")
  public final String zone2;

  @JsonProperty("Zone 3")
  public final String zone3;

  @JsonProperty("Zone 4")
  public final String zone4;

  @JsonProperty("Zone 5")
  public final String zone5;

  @JsonProperty("Zone 6")
  public final String zone6;

  @JsonProperty("Sweet spot")
  public final String sweetSpot;


  @JsonCreator
  public ZoneResponse(
      final String zone1, final String zone2, final String zone3,
      final String zone4, final String zone5, final String zone6,
      final String sweetSpot
  ) {
    this.zone1 = zone1;
    this.zone2 = zone2;
    this.zone3 = zone3;
    this.zone4 = zone4;
    this.zone5 = zone5;
    this.zone6 = zone6;
    this.sweetSpot = sweetSpot;
  }
}
