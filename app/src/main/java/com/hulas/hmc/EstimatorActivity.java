package com.hulas.hmc;
import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity;
public class EstimatorActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_estimator); EditText length=findViewById(R.id.length),qty=findViewById(R.id.qty); TextView out=findViewById(R.id.result);
  findViewById(R.id.calculate).setOnClickListener(v->{try{double l=Double.parseDouble(length.getText().toString()),q=Double.parseDouble(qty.getText().toString());out.setText(String.format("Estimated total length: %.2f m",l*q));}catch(Exception e){out.setText("Enter valid values");}});
 }
}
