package com.hulas.hmc;
import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity;
public class RewardsActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_rewards); TextView points=findViewById(R.id.points); points.setText("0"); findViewById(R.id.scan).setOnClickListener(v->Toast.makeText(this,"Scan a verified HULAS pipe QR to earn points.",Toast.LENGTH_LONG).show());}
}
