package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText user=findViewById(R.id.usernametext);
        EditText pass=findViewById(R.id.passwordtext);
        Button Login=findViewById(R.id.LoginBTN);
        Button Register=findViewById(R.id.RegisterBTN);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQL mydb=new SQL(getApplicationContext());
                mydb.checkuser(user.getText().toString());
                if(mydb.checkuser(user.getText().toString())==true)
                {
                    mydb.checkuserandpass(user.getText().toString(),pass.getText().toString());
                    if(mydb.checkuserandpass(user.getText().toString(),pass.getText().toString())==true)
                    {
                        startActivity(new Intent(MainActivity.this,Welcome.class));
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong Pass", Toast.LENGTH_SHORT).show();
                    }



                }
                else
                {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
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
}