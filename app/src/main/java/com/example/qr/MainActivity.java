package com.example.qr;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder bul1 = new AlertDialog.Builder(this);
        AlertDialog.Builder bul2 = new AlertDialog.Builder(this);
        AlertDialog.Builder bul3 = new AlertDialog.Builder(this);

        Button b1 = findViewById(R.id.btn1);
        Button b2 = findViewById(R.id.btn2);
        Button b3 = findViewById(R.id.btn3);

        bul1.setCancelable(false);
        bul1.setTitle("CREATOR INFO");
        bul1.setMessage("Creator\t\t: SHANKARANARAYANAN S S \nRoll No\t\t: P24MCA120 \nCls Sec\t\t: 2nd MCA \nCollege\t\t: Nehru Memorial College");
        bul1.setPositiveButton("Next",((dialog, which) -> {
            bul2.show();
        }));

        bul2.setCancelable(false);
        bul2.setTitle("APP INFO");
        bul2.setMessage("App Name\t: ANDROID QR KIT \nVersion\t\t\t: 1.0");
        bul2.setPositiveButton("Next",((dialog, which) -> {
            bul3.show();
        }));

        bul3.setCancelable(false);
        bul3.setTitle("Purpose!");
        bul3.setMessage("App created for Mini Project");
        bul3.setPositiveButton("OK",((dialog, which) -> {
            dialog.dismiss();
        }));

        b1.setOnClickListener(v->{
            Intent in1 = new Intent(MainActivity.this,JavaFile1.class);
            startActivity(in1);
        });

        b2.setOnClickListener(v -> {
            Intent in2 = new Intent(MainActivity.this,JavaFile2.class);
            startActivity(in2);
        });

        b3.setOnClickListener(v -> {
            bul1.show();
        });
    }
}