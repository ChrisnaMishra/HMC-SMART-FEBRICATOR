package com.hulas.hmc;
import android.os.Bundle; import android.widget.*; import androidx.appcompat.app.AppCompatActivity; import com.journeyapps.barcodescanner.ScanContract; import com.journeyapps.barcodescanner.ScanOptions;
public class ScannerActivity extends AppCompatActivity {
 protected void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_scanner);findViewById(R.id.start).setOnClickListener(v->{ScanOptions o=new ScanOptions();o.setPrompt("Scan HULAS QR");o.setBeepEnabled(true);launcher.launch(o);});}
 private final androidx.activity.result.ActivityResultLauncher<ScanOptions> launcher=registerForActivityResult(new ScanContract(),r->{if(r.getContents()!=null)((TextView)findViewById(R.id.result)).setText("QR detected. Backend verification is required before awarding points.\n"+r.getContents());});
}
