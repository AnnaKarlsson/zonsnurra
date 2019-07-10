package se.tot.zonsnurra.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class TestMeasure<T> implements Comparable<T> {

  abstract BigDecimal toBigDecimal();

  public Integer toInteger(){
    return toBigDecimal().setScale(0, RoundingMode.HALF_UP)
        .intValueExact();
  }

  protected static Integer round(final BigDecimal bigDecimal){
    return bigDecimal.setScale(0,RoundingMode.HALF_UP).intValueExact();
  }

  public abstract String jsonValue();
}
