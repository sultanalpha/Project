package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText userREG = findViewById(R.id.usernametextREG);
        EditText passREG = findViewById(R.id.passwordtextREG);
        EditText confirmpassREG = findViewById(R.id.confirmpasswordREG);
        Button registerBTN=findViewById(R.id.RegisterBTN);

        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Toast.makeText(Register.this, ""+passREG.getText().toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(Register.this, ""+confirmpassREG.getText().toString(), Toast.LENGTH_SHORT).show();*/



                if (passREG.getText().toString().equals(confirmpassREG.getText().toString()))
                {
                    SQL mydb=new SQL(getApplicationContext());
                    mydb.insert(userREG.getText().toString(),passREG.getText().toString());
                    if(mydb.insert(userREG.getText().toString(),passREG.getText().toString())==true)
                    {
                        Toast.makeText(Register.this, "Added", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Register.this, "Password don't match", Toast.LENGTH_SHORT).show();
                }

            }
        });


        
    }
}