package com.example.project2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DatabaseHelper myDab;
    EditText username,password;
    Button btnlogin,btnregister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myDab=new DatabaseHelper(this);
        username=findViewById(R.id.edittextUsername);
        password=findViewById(R.id.edittextPassword);
        btnlogin=findViewById(R.id.btnlogin);
        login();
    }

    private void login()
    {
       btnlogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String usernames=username.getText().toString().trim();
               String pass=password.getText().toString().trim();

               if (usernames.isEmpty()&&pass.isEmpty())
               {

                   Toast.makeText(Login.this, "your field is empty", Toast.LENGTH_LONG).show();
               }
               if (pass.length()<8)
               {
                   Toast.makeText(Login.this, "your password is to short", Toast.LENGTH_LONG).show();
               }
               Cursor cursor= myDab.getdatalogin(usernames,pass);
               if (cursor!=null)
               {

                   if (cursor.getCount()>0)
                   {
                       cursor.moveToNext();
                       Toast.makeText(Login.this, "you have succefull login thanks", Toast.LENGTH_LONG).show();
                       startActivity(new Intent(Login.this,listviewsearch.class));

                   }
                   else
                   {
                       Toast.makeText(Login.this, "invalid username and password", Toast.LENGTH_LONG).show();
                   }
               }
           }
       });

    }
}
