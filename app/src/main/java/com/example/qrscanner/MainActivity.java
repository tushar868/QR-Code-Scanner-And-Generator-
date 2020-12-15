package com.example.qrscanner;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.scanBtn);
        btn2=findViewById(R.id.genBtn);
        // Camera Permission
       if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED) {
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
               requestPermissions(new String[]{Manifest.permission.CAMERA}, 101);

       }
       //btn 1 scan code
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanCode();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,GenerateActivity.class);
                startActivity(i);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }
    private void generateCode() {
        //pop up message show-
        Toast.makeText(this,"Generate code",Toast.LENGTH_SHORT).show();}

    private void scanCode() {
        //pop up message show-
      Toast.makeText(this,"Scan code",Toast.LENGTH_SHORT).show();
        IntentIntegrator intentIntegrator=new IntentIntegrator(this);

        intentIntegrator.initiateScan();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
      //  onCreate(onGetDirectActions(VIBRATOR_SERVICE));
        if(result==null)
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
        else
        {
            String r=result.getContents();
            if (r==null)
            {
                //
            }else
            {
                showBox(r);
            }
        }
    }

  //  private Bundle onGetDirectActions(String vibratorService) {}

    private void showBox(final String r) {
        Toast.makeText(this,"Your code has been scanned",Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
      //  AlertDialog.Builder b2=new AlertDialog.Builder(this);
        builder.setTitle("Scanned Result");
        builder.setMessage(r);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        builder.setNeutralButton("visit",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(r));
                        startActivity(browserIntent);
                    }
                });


                builder.create().show();
                builder.create().show();
    }
}

