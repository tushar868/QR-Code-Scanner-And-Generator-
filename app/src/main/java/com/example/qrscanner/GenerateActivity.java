package com.example.qrscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateActivity extends AppCompatActivity {
    Button genBtn;
    Button saveBtn;
    ImageView imageView;
    EditText field;

    BitmapDrawable drawable;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);
      //  getSupportActionBar().setTitle("Generate code");
        genBtn=findViewById (R.id.genBtn);
        saveBtn=findViewById(R.id.scanBtn);
        imageView=findViewById(R.id.img);
        field=findViewById(R.id.field);

        genBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateCode();
            }
        });
    }
    // code generate
    private void generateCode() {
        Toast.makeText(this,"Your code has been generated",Toast.LENGTH_SHORT).show();
        //IntentIntegrator intentIntegrator=new IntentIntegrator(this);

       // intentIntegrator.initiateScan();

        String content= field.getText().toString().trim();
        BarcodeEncoder encoder= new BarcodeEncoder();
        try {
            Bitmap bitmap = encoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 400, 400);
            imageView.setImageBitmap(bitmap);
            imageView.setVisibility(View.VISIBLE);
        }  catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }









    }



}
