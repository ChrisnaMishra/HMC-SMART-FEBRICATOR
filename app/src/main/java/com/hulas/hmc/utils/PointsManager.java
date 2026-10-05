package com.hulas.hmc.utils;

import android.content.Context;
import android.content.SharedPreferences;

public final class PointsManager {
    private static final String PREF = "hmc_points";
    private static final String KEY = "points";

    private PointsManager() {}

    public static int get(Context context) {
        return context.getSharedPreferences(PREF, Context.MODE_PRIVATE).getInt(KEY, 0);
    }

    // Call this only after HULAS backend verification confirms the QR and weight.
    public static void addVerifiedKg(Context context, double kg) {
        if (kg <= 0) return;
        int points = (int)Math.floor(kg);
        SharedPreferences p = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        p.edit().putInt(KEY, get(context) + points).apply();
    }

    public static void reset(Context context) {
        context.getSharedPreferences(PREF, Context.MODE_PRIVATE).edit().putInt(KEY, 0).apply();
    }
}
