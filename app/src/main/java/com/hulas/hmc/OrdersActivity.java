package com.hulas.hmc;
import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity;
public class OrdersActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_orders); ((TextView)findViewById(R.id.empty)).setText("No orders yet\nCreate an order from HMC and send it to Hulas.");}
}
