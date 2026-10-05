package com.hulas.hmc;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class ScannerActivity extends AppCompatActivity {
    private TextView result;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_scanner);
        result = findViewById(R.id.result);

        findViewById(R.id.start).setOnClickListener(v -> {
            ScanOptions options = new ScanOptions();
            options.setPrompt("Scan the QR printed on a genuine HULAS pipe");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            launcher.launch(options);
        });
    }

    private final androidx.activity.result.ActivityResultLauncher<ScanOptions> launcher =
            registerForActivityResult(new ScanContract(), r -> {
                if (r.getContents() == null) return;
                result.setText("QR captured\n\n" + r.getContents() +
                        "\n\nPending HULAS verification. Points are not added until the backend confirms the product and weight.");
            });
}
