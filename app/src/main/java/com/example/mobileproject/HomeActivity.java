package com.example.mobileproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.transition.MaterialElevationScale;

public class HomeActivity extends AppCompatActivity {
    EditText name,price;
    Button add,update,delete,list;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.edtTxtName);
        price = findViewById(R.id.edtTxtPrice);
        add = findViewById(R.id.btnAdd);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        list = findViewById(R.id.btnView);
        db = new DBHelper(HomeActivity.this);

        //Methods
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = name.getText().toString();
                String cost = price.getText().toString();
                Boolean checkAddedData = db.addData(productName,cost);
                if(checkAddedData == true){
                    Toast.makeText(HomeActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(HomeActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = name.getText().toString();
                String cost = price.getText().toString();
                Boolean checkUpdatedData = db.updateData(productName,cost);
                if(checkUpdatedData == true){
                    Toast.makeText(HomeActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(HomeActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = name.getText().toString();
                Boolean checkdeleteData = db.deleteData(productName);
                if(checkdeleteData == true){
                    Toast.makeText(HomeActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(HomeActivity.this, "FAILED", Toast.LENGTH_SHORT).show();
                }
            }
        });
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res =db.getData();
                if(res.getCount() ==0){
                    Toast.makeText(HomeActivity.this, "No entry Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Price :"+res.getString(1 )+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Products");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}