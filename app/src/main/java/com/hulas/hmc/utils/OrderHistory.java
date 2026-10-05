package com.hulas.hmc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONObject;

public final class OrderHistory {
    private static final String PREF = "hmc_orders";
    private static final String KEY = "items";

    private OrderHistory() {}

    public static void add(Context context, String grade, String type, String size,
                            boolean heavy, double quantity, double totalKg, double total) {
        try {
            SharedPreferences p = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
            JSONArray old = new JSONArray(p.getString(KEY, "[]"));
            JSONArray next = new JSONArray();
            JSONObject item = new JSONObject();
            item.put("grade", grade);
            item.put("type", type);
            item.put("size", size);
            item.put("heavy", heavy);
            item.put("quantity", quantity);
            item.put("kg", totalKg);
            item.put("total", total);
            item.put("status", "WhatsApp request");
            item.put("time", System.currentTimeMillis());
            next.put(item);
            for (int i = 0; i < old.length() && next.length() < 20; i++) next.put(old.getJSONObject(i));
            p.edit().putString(KEY, next.toString()).apply();
        } catch (Exception ignored) {}
    }

    public static JSONArray get(Context context) {
        try {
            return new JSONArray(context.getSharedPreferences(PREF, Context.MODE_PRIVATE).getString(KEY, "[]"));
        } catch (Exception e) {
            return new JSONArray();
        }
    }
}
