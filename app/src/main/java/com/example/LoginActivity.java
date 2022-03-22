package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username,password,confirmpassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB= new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user1=username.getText().toString();
                String pass=password.getText().toString();
                String confirmpass=confirmpassword.getText().toString();

                if(TextUtils.isEmpty(user1) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(confirmpass))
                    Toast.makeText(LoginActivity.this,"You must fill in all of the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(confirmpass)){
                        Boolean checkuser = DB.checkusername(user1);
                        if(checkuser==false){
                            Boolean insert = DB.insertdata(user1,pass);
                            if(insert==true){
                                Toast.makeText(LoginActivity.this,"Sign up Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainLoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(LoginActivity.this,"Sign up failed",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this,"User already Exists",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"Passwords are not correct",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}