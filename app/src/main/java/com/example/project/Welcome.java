package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

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
        return super.onOptionsItemSelected(item);
    }
}