package com.hulas.hmc;
import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.hulas.hmc.data.PipeWeightData;
public class CalculatorActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_calculator);
  Spinner grade=findViewById(R.id.grade),size=findViewById(R.id.size); Switch heavy=findViewById(R.id.heavy); EditText qty=findViewById(R.id.qty); TextView result=findViewById(R.id.result);
  String[] gs={"202","304"}, ss={"1/2","3/4","1","1.5","2"}; grade.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,gs));size.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ss));
  findViewById(R.id.calculate).setOnClickListener(v->{double q=0;try{q=Double.parseDouble(qty.getText().toString());}catch(Exception e){} double kg=PipeWeightData.weight(size.getSelectedItem().toString(),heavy.isChecked())*q;double net=kg*PipeWeightData.rate(grade.getSelectedItem().toString());double total=net*(1+PipeWeightData.VAT);result.setText(String.format("Weight  %.3f kg\nSubtotal  Rs %.2f\nVAT 13%%  Rs %.2f\nTotal  Rs %.2f",kg,net,net*PipeWeightData.VAT,total));});
 }
}
