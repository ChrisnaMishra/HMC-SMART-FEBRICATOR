package com.hulas.hmc.data;
import java.util.LinkedHashMap;
import java.util.Map;

public final class PipeWeightData {
    public static final double VAT=0.13, PRICE_202=290.0, PRICE_304=475.0;
    private static final Map<String,double[]> ROUND=new LinkedHashMap<>();
    static {
        ROUND.put("1/2", new double[]{2.070,2.520});
        ROUND.put("3/4", new double[]{3.228,4.070});
        ROUND.put("1", new double[]{4.160,5.100});
        ROUND.put("1.5", new double[]{6.520,7.780});
        ROUND.put("2", new double[]{8.480,10.690});
    }
    public static double weight(String size, boolean heavy) {
        double[] v=ROUND.get(size); return v==null?0:v[heavy?1:0];
    }
    public static double rate(String grade){ return "304".equals(grade)?PRICE_304:PRICE_202; }
    private PipeWeightData(){}
}
