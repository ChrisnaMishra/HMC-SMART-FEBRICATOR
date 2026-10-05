package com.hulas.hmc;
import android.content.Intent; import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.hulas.hmc.utils.SessionManager;
public class MainActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main);
  TextView greeting=findViewById(R.id.greeting); greeting.setText("Hello, "+SessionManager.name(this));
  findViewById(R.id.calculator).setOnClickListener(v->startActivity(new Intent(this,CalculatorActivity.class)));
  findViewById(R.id.estimator).setOnClickListener(v->startActivity(new Intent(this,EstimatorActivity.class)));
  findViewById(R.id.scan).setOnClickListener(v->startActivity(new Intent(this,ScannerActivity.class)));
  findViewById(R.id.rewards).setOnClickListener(v->startActivity(new Intent(this,RewardsActivity.class)));
  findViewById(R.id.orders).setOnClickListener(v->startActivity(new Intent(this,OrdersActivity.class)));
  findViewById(R.id.profile).setOnClickListener(v->startActivity(new Intent(this,ProfileActivity.class)));
 }
}
