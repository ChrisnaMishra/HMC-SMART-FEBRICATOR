package com.hulas.hmc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.hulas.hmc.utils.PointsManager;

public class RewardsActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_rewards);
        TextView points = findViewById(R.id.points);
        points.setText(String.valueOf(PointsManager.get(this)));
        findViewById(R.id.scan).setOnClickListener(v ->
                startActivity(new Intent(this, ScannerActivity.class)));
        findViewById(R.id.order).setOnClickListener(v ->
                startActivity(new Intent(this, EstimatorActivity.class)));
    }

    @Override protected void onResume() {
        super.onResume();
        TextView points = findViewById(R.id.points);
        if (points != null) points.setText(String.valueOf(PointsManager.get(this)));
    }
}
