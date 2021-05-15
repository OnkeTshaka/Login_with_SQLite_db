package com.example.mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.service.controls.actions.BooleanAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        EditText repassword = findViewById(R.id.repassword);
        Button register=findViewById(R.id.btnRegister);
        Button login =findViewById(R.id.btnLogin);

        DBHelper db = new DBHelper(this );
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")|| pass.equals("")||repass.equals("")){
                    Toast.makeText(MainActivity.this, "Please Fill in all the Fileds!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repass)){
                        Boolean usercheckResullt =db.chkUserName(user);
                        if(usercheckResullt ==false){
                            Boolean regResult = db.insertData(user,pass);
                            if(regResult ==true){
                                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists!\n Please Sign In", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{
                        Toast.makeText(MainActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        //Button btnRegister = findViewById(R.id.btnRegister);

//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView Name = findViewById(R.id.txtViewName);
//        TextView LastName = findViewById(R.id.txtViewLastName);
//        TextView Email = findViewById(R.id.txtViewEmail);
//        EditText name = findViewById(R.id.editTxtName);
//        EditText lastName = findViewById(R.id.editTxtLastName);
//        EditText email = findViewById(R.id.editTxtEmailAddress);
//        Name.setText("Name, "+name.getText().toString());
//        LastName.setText("Last Name, "+lastName.getText().toString());
//        Email.setText("Email, "+email.getText().toString());
//                Toast.makeText(MainActivity.this, "INPUT WAS SUCCESSFUL", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
//    public void btnRegister(View v){
//        TextView Name = findViewById(R.id.txtViewName);
//        TextView LastName = findViewById(R.id.txtViewLastName);
//        TextView Email = findViewById(R.id.txtViewEmail);
//        EditText name = findViewById(R.id.editTxtName);
//        EditText lastName = findViewById(R.id.editTxtLastName);
//        EditText email = findViewById(R.id.editTxtEmailAddress);
//        //TODO output.setText("Hello, "+name.getText().toString()+" "+lastName.getText().toString());
//        Name.setText("Name, "+name.getText().toString());
//        LastName.setText("Last Name, "+lastName.getText().toString());
//        Email.setText("Email, "+email.getText().toString());
//    }
}