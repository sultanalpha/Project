package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        final SharedPreferences sp=getSharedPreferences("xmlfile",0);
        final SharedPreferences.Editor edit=sp.edit();

        final String state=sp.getString("state","0");

        if(state.equals("1"))
        {
            startActivity(new Intent(MainActivity.this,Welcome.class));
            finish();
        }




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
}