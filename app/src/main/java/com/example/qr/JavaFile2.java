package com.example.qr;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class JavaFile2 extends AppCompatActivity{
    Button btn,tog;
    TextView txt;
    Boolean bol=false;

    @Override
    protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.xmlfile2);
        txt = findViewById(R.id.copy);
        btn = findViewById(R.id.button);
        tog = findViewById(R.id.toggle);
        tog.setOnClickListener(v -> {
            if(tog.getText().toString().equals("Torch Off")){
                bol = true;
                tog.setText("Torch On");
                tog.setTextColor(Color.parseColor("#67C090"));
            }
            else{
                bol = false;
                tog.setText("Torch Off");
                tog.setTextColor(Color.parseColor("#ff0000"));
            }
        });

        btn.setOnClickListener(v->{
            IntentIntegrator integrator = new IntentIntegrator(JavaFile2.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Scanning");
            integrator.setCameraId(0);
            integrator.setBeepEnabled(true);
            integrator.setTorchEnabled(bol);
            integrator.setOrientationLocked(true);
            integrator.setCaptureActivity(CustomScanner.class);
            integrator.initiateScan();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()!=null){
                String sdata = result.getContents();
                txt.setText("Scanned Link:\n" + sdata);
                android.content.ClipboardManager clipboardManager = (android.content.ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("QR Result",sdata);
                clipboardManager.setPrimaryClip(clipData);
            }
            else{
                Toast.makeText(this,"Scan Cancelled",Toast.LENGTH_SHORT).show();
            }
        }
    }
}