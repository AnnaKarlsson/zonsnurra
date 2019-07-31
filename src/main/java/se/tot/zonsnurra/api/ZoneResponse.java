package se.tot.zonsnurra.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ZoneResponse {

  @JsonProperty("Zone 1")
  public final Zone zone1;

  @JsonProperty("Zone 2")
  public final Zone zone2;

  @JsonProperty("Zone 3")
  public final Zone zone3;

  @JsonProperty("Zone 4")
  public final Zone zone4;

  @JsonProperty("Zone 5")
  public final Zone zone5;

  @JsonProperty("Zone 6")
  public final Zone zone6;

  @JsonProperty("Sweet spot")
  public final Zone sweetSpot;


  @JsonCreator
  public ZoneResponse(
      final Zone zone1, final Zone zone2, final Zone zone3,
      final Zone zone4, final Zone zone5, final Zone zone6,
      final Zone sweetSpot
  ) {
    this.zone1 = zone1;
    this.zone2 = zone2;
    this.zone3 = zone3;
    this.zone4 = zone4;
    this.zone5 = zone5;
    this.zone6 = zone6;
    this.sweetSpot = sweetSpot;
  }

  public static class Zone {

    public final String low;
    public final String high;

    public Zone(final String low, final String high) {
      this.low = low;
      this.high = high;
    }
  }
}
