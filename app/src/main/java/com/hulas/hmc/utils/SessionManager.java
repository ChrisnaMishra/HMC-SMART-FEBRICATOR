package com.hulas.hmc.utils;
import android.content.Context;
import android.content.SharedPreferences;
public final class SessionManager {
    private static final String P="hmc_session";
    public static boolean loggedIn(Context c){return c.getSharedPreferences(P,0).getBoolean("logged",false);}
    public static void login(Context c,String name,String phone){SharedPreferences.Editor e=c.getSharedPreferences(P,0).edit();e.putBoolean("logged",true).putString("name",name).putString("phone",phone).apply();}
    public static String name(Context c){return c.getSharedPreferences(P,0).getString("name","Fabricator");}
    public static String phone(Context c){return c.getSharedPreferences(P,0).getString("phone","");}
    public static void logout(Context c){c.getSharedPreferences(P,0).edit().clear().apply();}
}
