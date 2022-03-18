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
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText user=findViewById(R.id.usernametext);
        EditText pass=findViewById(R.id.passwordtext);
        Button Login=findViewById(R.id.LoginBTN);
        Button Register=findViewById(R.id.RegisterBTN);

        final SharedPreferences sp=getSharedPreferences("xmlfile",0);
        final SharedPreferences.Editor edit=sp.edit();

        final String state=sp.getString("state","0");

        if(state.equals("1"))
        {
            startActivity(new Intent(MainActivity.this,Welcome.class));
            finish();
        }


        /*
        Pemission pop up work oncreate
        -------------------------------------------------------------------------------------------------------
         */

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {


        }
        else
        {
            requeststoraepermission();
        }


/* ------------------------------------------------------------------------------------------------------------ */


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL mydb=new SQL(getApplicationContext());

                if(user.getText().toString().equals("")&&pass.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Username and Password must not be empty!!!", Toast.LENGTH_SHORT).show();
                }
                else if(user.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Username must not be empty!!", Toast.LENGTH_SHORT).show();
                }
                else if(pass.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Password must not be empty!!", Toast.LENGTH_SHORT).show();
                }
                else {

                    mydb.checkuser(user.getText().toString());
                    if (mydb.checkuser(user.getText().toString()) == true) {
                        mydb.checkuserandpass(user.getText().toString(), pass.getText().toString());
                        if (mydb.checkuserandpass(user.getText().toString(), pass.getText().toString()) == true) {
                            Toast.makeText(MainActivity.this, "Intresting", Toast.LENGTH_SHORT).show();
                        /*
                        startActivity(new Intent(MainActivity.this,Welcome.class));
                         */
                            edit.putString("state", "1");
                            edit.commit();
                            startActivity(new Intent(MainActivity.this, Welcome.class));
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong Pass", Toast.LENGTH_SHORT).show();
                        }


                    } else {
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

    }

    public void requeststoraepermission () {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            new AlertDialog.Builder(this)
                    .setTitle("Permission Needed")
                    .setMessage("Enable Storage permission please")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
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
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                /*
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                 */
            } else {

                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(this)
                        .setTitle("Permission Needed")
                        .setMessage("Enable Storage permission please")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
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
        }
    }

}