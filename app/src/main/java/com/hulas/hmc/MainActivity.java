package com.hulas.hmc;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.hulas.hmc.utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        ((android.widget.TextView)findViewById(R.id.greeting)).setText("Hello, " + SessionManager.name(this));
        ((android.widget.TextView)findViewById(R.id.header_points)).setText("0 PTS");
        findViewById(R.id.calculator).setOnClickListener(v -> startActivity(new Intent(this, CalculatorActivity.class)));
        findViewById(R.id.estimator).setOnClickListener(v -> startActivity(new Intent(this, EstimatorActivity.class)));
        findViewById(R.id.hero_estimator).setOnClickListener(v -> startActivity(new Intent(this, EstimatorActivity.class)));
        findViewById(R.id.scan).setOnClickListener(v -> startActivity(new Intent(this, ScannerActivity.class)));
        findViewById(R.id.nav_orders).setOnClickListener(v -> startActivity(new Intent(this, OrdersActivity.class)));
        findViewById(R.id.nav_rewards).setOnClickListener(v -> startActivity(new Intent(this, RewardsActivity.class)));
        findViewById(R.id.nav_profile).setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }
}