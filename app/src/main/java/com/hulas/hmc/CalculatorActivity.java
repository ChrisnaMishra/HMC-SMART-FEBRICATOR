package com.hulas.hmc;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.hulas.hmc.data.OrderDraft;
import com.hulas.hmc.data.PipeWeightData;
import com.hulas.hmc.utils.WhatsAppOrder;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {
    private Spinner grade, type, size;
    private Switch heavy;
    private EditText qty;
    private TextView result;
    private final OrderDraft draft = new OrderDraft();

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_calculator);
        grade=findViewById(R.id.grade); type=findViewById(R.id.type); size=findViewById(R.id.size);
        heavy=findViewById(R.id.heavy); qty=findViewById(R.id.qty); result=findViewById(R.id.result);
        grade.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"202","304"}));
        type.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Round","Square","Rectangle"}));
        updateSizes();
        type.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            public void onItemSelected(android.widget.AdapterView<?> p, android.view.View v, int pos, long id){ updateSizes(); }
            public void onNothingSelected(android.widget.AdapterView<?> p){}
        });
        findViewById(R.id.calculate).setOnClickListener(v -> calculate());
        findViewById(R.id.order).setOnClickListener(v -> {
            if (calculate()) {
                String msg=String.format(Locale.US,
                    "HMC Order Request\nGrade: %s\nType: %s\nSize: %s\n%s\nQty: %.0f pipes\nWeight: %.3f kg\nTotal incl. VAT: Rs %.2f",
                    draft.grade,draft.type,draft.size,draft.heavy?"Heavy":"Light",draft.quantity,draft.totalKg,draft.totalWithVat);
                WhatsAppOrder.send(this,msg);
            }
        });
    }

    private void updateSizes() {
        if (type.getSelectedItem()==null) return;
        size.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                PipeWeightData.sizes(type.getSelectedItem().toString())));
    }

    private boolean calculate() {
        try {
            draft.grade=grade.getSelectedItem().toString();
            draft.type=type.getSelectedItem().toString();
            draft.size=size.getSelectedItem().toString();
            draft.heavy=heavy.isChecked();
            draft.quantity=Double.parseDouble(qty.getText().toString());
            if (draft.quantity <= 0) throw new IllegalArgumentException();
            draft.calculate();
            double rate=PipeWeightData.rate(draft.grade)+PipeWeightData.surcharge(draft.type,draft.size);
            result.setText(String.format(Locale.US,
                "Weight / 6m pipe   %.3f kg\nTotal weight        %.3f kg\nRate                Rs %.2f/kg\nSubtotal            Rs %.2f\nVAT 13%%             Rs %.2f\nTOTAL               Rs %.2f",
                PipeWeightData.weight(draft.type,draft.size,draft.heavy),draft.totalKg,rate,
                draft.totalBeforeVat,draft.totalBeforeVat*PipeWeightData.VAT,draft.totalWithVat));
            return true;
        } catch(Exception e) {
            result.setText("Please enter a valid quantity.");
            return false;
        }
    }
}