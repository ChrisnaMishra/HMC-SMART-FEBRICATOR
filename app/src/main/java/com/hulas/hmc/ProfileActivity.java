package com.hulas.hmc;
import android.content.Intent; import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.hulas.hmc.utils.SessionManager;
public class ProfileActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_profile); ((TextView)findViewById(R.id.name)).setText(SessionManager.name(this)); ((TextView)findViewById(R.id.phone)).setText(SessionManager.phone(this)); findViewById(R.id.logout).setOnClickListener(v->{SessionManager.logout(this);startActivity(new Intent(this,LoginActivity.class));finishAffinity();});}
}
