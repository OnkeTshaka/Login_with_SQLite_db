package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.usernameLogin);
        EditText password = findViewById(R.id.passwordLogin);
        Button signIn = findViewById(R.id.btnSign);
        DBHelper db = new DBHelper(this );
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                if(user.equals("")|| pass.equals("")){
                    Toast.makeText(LoginActivity.this, "Please enter Login Details!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean result = db.chkUserNamePassword(user,pass);
                    if(result == true){
                        Toast.makeText(LoginActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Invalid Login credentials!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}