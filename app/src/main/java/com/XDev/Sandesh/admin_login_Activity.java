package com.XDev.Sandesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class admin_login_Activity extends AppCompatActivity {
    Button button,backl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        button=(Button) findViewById(R.id.new_regis);
        backl=(Button)findViewById(R.id.back_to_login);

        backl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(admin_login_Activity.this,login_Activity.class));
                overridePendingTransition(0, 0);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent= new Intent(admin_login_Activity.this,new_regis.class);
               startActivity(intent);
                overridePendingTransition(0, 0);


            }
        });
    }

    private void checking() {

    }
}