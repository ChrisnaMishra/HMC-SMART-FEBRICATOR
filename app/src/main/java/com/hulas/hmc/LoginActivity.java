package com.hulas.hmc;
import android.content.Intent; import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.hulas.hmc.utils.SessionManager;
public class LoginActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b); setContentView(R.layout.activity_login);
  if(SessionManager.loggedIn(this)){startActivity(new Intent(this,MainActivity.class));finish();return;}
  EditText phone=findViewById(R.id.phone), password=findViewById(R.id.password);
  findViewById(R.id.login).setOnClickListener(v->{if(phone.getText().length()<3||password.getText().length()<1){Toast.makeText(this,"Enter phone and password",Toast.LENGTH_SHORT).show();return;} SessionManager.login(this,"Fabricator",phone.getText().toString());startActivity(new Intent(this,MainActivity.class));finish();});
  findViewById(R.id.register).setOnClickListener(v->startActivity(new Intent(this,RegisterActivity.class)));
 }
}
