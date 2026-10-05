package com.hulas.hmc;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.hulas.hmc.utils.OrderHistory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Locale;

public class OrdersActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_orders);

        TextView summary = findViewById(R.id.summary);
        LinearLayout list = findViewById(R.id.order_list);
        JSONArray orders = OrderHistory.get(this);
        summary.setText(orders.length() == 0 ? "No saved order requests" :
                orders.length() + (orders.length() == 1 ? " saved request" : " saved requests"));

        if (orders.length() == 0) {
            TextView empty = new TextView(this);
            empty.setText("Create an estimate and tap ORDER ON WHATSAPP.\nYour request will appear here.");
            empty.setTextColor(getColor(R.color.hulas_gray));
            empty.setTextSize(13);
            empty.setGravity(Gravity.CENTER);
            empty.setPadding(24, 28, 24, 28);
            list.addView(empty);
        } else {
            for (int i = 0; i < orders.length(); i++) {
                try {
                    JSONObject o = orders.getJSONObject(i);
                    LinearLayout card = new LinearLayout(this);
                    card.setOrientation(LinearLayout.VERTICAL);
                    card.setPadding(16, 14, 16, 14);
                    TextView title = new TextView(this);
                    title.setText(String.format(Locale.US, "%s • %s %s", o.optString("grade"), o.optString("type"), o.optString("size")));
                    title.setTextSize(15);
                    title.setTextColor(getColor(R.color.hulas_dark));
                    title.setTypeface(null, 1);
                    TextView detail = new TextView(this);
                    detail.setText(String.format(Locale.US, "%s • %.0f pipes • %.3f kg\nRs %.2f incl. VAT\n%s",
                            o.optBoolean("heavy") ? "Heavy" : "Light",
                            o.optDouble("quantity"), o.optDouble("kg"), o.optDouble("total"),
                            o.optString("status", "Request saved")));
                    detail.setTextSize(12);
                    detail.setTextColor(getColor(R.color.hulas_gray));
                    detail.setPadding(0, 6, 0, 0);
                    card.addView(title);
                    card.addView(detail);
                    list.addView(card);
                } catch (Exception ignored) {}
            }
        }

        findViewById(R.id.new_order).setOnClickListener(v ->
                startActivity(new Intent(this, EstimatorActivity.class)));
    }
}
