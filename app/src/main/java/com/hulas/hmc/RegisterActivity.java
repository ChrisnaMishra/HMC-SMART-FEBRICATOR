package com.hulas.hmc;
import android.content.Intent; import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.hulas.hmc.utils.SessionManager;
public class RegisterActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_register);
  EditText name=findViewById(R.id.name),phone=findViewById(R.id.phone),password=findViewById(R.id.password);
  findViewById(R.id.create).setOnClickListener(v->{if(name.getText().length()<2||phone.getText().length()<3||password.getText().length()<4){Toast.makeText(this,"Complete all fields",Toast.LENGTH_SHORT).show();return;}SessionManager.login(this,name.getText().toString(),phone.getText().toString());startActivity(new Intent(this,MainActivity.class));finish();});
 }
}
