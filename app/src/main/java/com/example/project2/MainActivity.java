package com.example.project2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
   DatabaseHelper myDb;
   EditText username,password;
   Button registerbtn,loginbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(this);
        username=findViewById(R.id.edittextUsername);
        password=findViewById(R.id.edittextPassword);
        registerbtn=(Button)findViewById(R.id.btnregister);
        loginbtn=(Button)findViewById(R.id.btnlogin);
        adddata();

    }

    private void adddata()
    {

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username=username.getText().toString().trim();
                String Secret=password.getText().toString().trim();
                if (Username.isEmpty()&&Secret.isEmpty())
                {

                    Toast.makeText(getApplicationContext(), "sorry your field is empty", Toast.LENGTH_LONG).show();

                }
                else {
                    boolean check= myDb.insertdata(Username,Secret);
                    if (check==true)
                    {
                        Toast.makeText(getApplicationContext(), "Congratutsytion,you have registr!", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),Login.class));
                    }
                }
            }
        });

    }
}




