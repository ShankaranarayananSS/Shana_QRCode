package com.example.qr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JavaFile1 extends AppCompatActivity{

    Button btn;
    EditText lnk,nme;
    TextView txt;
    ImageView img;
    String name,link;

    @Override
    protected void onCreate(Bundle s){
        super.onCreate(s);
        setContentView(R.layout.xmlfile1);

        btn = findViewById(R.id.button);
        lnk = findViewById(R.id.link);
        nme = findViewById(R.id.name);
        img = findViewById(R.id.image);
        txt = findViewById(R.id.show);

        btn.setOnClickListener(v -> {
            link = lnk.getText().toString().trim();
            name = nme.getText().toString().trim();
            if(link.isEmpty() || name.isEmpty()){
                Toast.makeText(this,"Fill the Link and Name!",Toast.LENGTH_SHORT).show();
                return;
            }
            try{
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(link, BarcodeFormat.QR_CODE, 400, 400);
                img.setImageBitmap(bitmap);
                saveQRImage(bitmap);
            }
            catch (WriterException exception){
                exception.printStackTrace();
            }
            lnk.setText("");
            nme.setText("");
        });
    }
    private void saveQRImage(Bitmap bitmap){
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/QR" ;
        File dir = new File(path);
        if(!dir.exists()) dir.mkdir();

        File file = new File(dir,name + System.currentTimeMillis() + ".png");
        try (FileOutputStream out = new FileOutputStream(file)){
            bitmap.compress(Bitmap.CompressFormat.PNG,100,out);
            Toast.makeText(this,"Saved:" + file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
            txt.setText("QR Code Name:"+ name + System.currentTimeMillis());
        }
        catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }
    }
}