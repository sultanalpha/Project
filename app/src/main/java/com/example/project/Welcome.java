package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final SharedPreferences sp=getSharedPreferences("xmlfile",0);
        final SharedPreferences.Editor edit=sp.edit();

        Button logout=findViewById(R.id.Logout);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                edit.putString("state","0");
                edit.commit();
                startActivity(new Intent(Welcome.this,MainActivity.class));

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.mym,menu);

        return super.onCreateOptionsMenu(menu);
    }
//option menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getTitle().equals("Exit")){
            AlertDialog.Builder b = new AlertDialog.Builder(Welcome.this);
            b.setMessage("do you want Exit ? ");
            b.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            b.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog a = b.create();
            a.show();
        }
        else if(item.getTitle().equals("Camera"))
        {

            if(ContextCompat.checkSelfPermission(Welcome.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
            {
                /*
                Toast.makeText(this, "Already granted", Toast.LENGTH_SHORT).show();
                 */
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            }
            else
            {
                requestcamerapermission();

            }

        }




        return super.onOptionsItemSelected(item);
    }
    public void requestcamerapermission () {
if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
{
new AlertDialog.Builder(this)
        .setTitle("Permission Needed")
        .setMessage("Enable Camera permission please")
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(Welcome.this,new String[] {Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
            }
        })
        .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        })
        .create().show();
}
else
{
    ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
}
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                /*
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                 */

                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

            }
        }
    }
}