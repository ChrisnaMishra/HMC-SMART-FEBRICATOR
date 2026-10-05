package com.hulas.hmc.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public final class WhatsAppOrder {
    private static final String NUMBER = "9779845656595";

    public static void send(Context context, String message) {
        try {
            Intent i = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://wa.me/" + NUMBER + "?text=" + Uri.encode(message)));
            context.startActivity(i);
        } catch (Exception e) {
            Toast.makeText(context, "WhatsApp is not available on this device.", Toast.LENGTH_LONG).show();
        }
    }

    private WhatsAppOrder() {}
}
