package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
}