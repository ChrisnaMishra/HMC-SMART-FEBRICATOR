package com.hulas.hmc.data;

import java.util.LinkedHashMap;
import java.util.Map;

public final class PipeWeightData {
    public static final double VAT = 0.13;
    public static final double PRICE_202 = 290.0;
    public static final double PRICE_304 = 475.0;
    public static final double LENGTH_M = 6.0;
    public static final double SQUARE_RECTANGLE_SURCHARGE = 30.0;
    public static final double THREE_QUARTER_SURCHARGE = 10.0;

    private static final Map<String, double[]> ROUND = new LinkedHashMap<>();
    private static final Map<String, double[]> SQUARE = new LinkedHashMap<>();
    private static final Map<String, double[]> RECTANGLE = new LinkedHashMap<>();

    static {
        ROUND.put("1/2", new double[]{2.070, 2.520});
        ROUND.put("3/4", new double[]{3.228, 4.070});
        ROUND.put("1", new double[]{4.160, 5.100});
        ROUND.put("1.5", new double[]{6.520, 7.780});
        ROUND.put("2", new double[]{8.480, 10.690});

        SQUARE.put("20x20", new double[]{4.160, 5.100});
        SQUARE.put("25x25", new double[]{5.280, 6.610});
        SQUARE.put("40x40", new double[]{8.480, 10.690});
        SQUARE.put("50x50", new double[]{11.150, 12.800});

        RECTANGLE.put("20x40", new double[]{6.520, 7.780});
        RECTANGLE.put("25x50", new double[]{8.480, 10.690});
    }

    public static double weight(String type, String size, boolean heavy) {
        Map<String, double[]> table = "Square".equals(type) ? SQUARE :
                "Rectangle".equals(type) ? RECTANGLE : ROUND;
        double[] values = table.get(size);
        return values == null ? 0 : values[heavy ? 1 : 0];
    }

    public static double rate(String grade) {
        return "304".equals(grade) ? PRICE_304 : PRICE_202;
    }

    public static double surcharge(String type, String size) {
        if ("Square".equals(type) || "Rectangle".equals(type)) return SQUARE_RECTANGLE_SURCHARGE;
        if ("3/4".equals(size)) return THREE_QUARTER_SURCHARGE;
        return 0;
    }

    public static String[] sizes(String type) {
        if ("Square".equals(type)) return SQUARE.keySet().toArray(new String[0]);
        if ("Rectangle".equals(type)) return RECTANGLE.keySet().toArray(new String[0]);
        return ROUND.keySet().toArray(new String[0]);
    }

    private PipeWeightData() {}
}
