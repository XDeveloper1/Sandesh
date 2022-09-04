package com.XDev.Sandesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.concurrent.ConcurrentHashMap;

public class login_Activity extends AppCompatActivity {
    TextView admin_login;
    EditText uid;
    EditText password;
    FirebaseDatabase db;
    Button button;

    DatabaseReference reference;
    public static final String PREFS_NAME ="myprefsfile" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       admin_login=(TextView)findViewById(R.id.tv_admin_login);
       button=findViewById(R.id.ulogin);
        uid =findViewById(R.id.userids);
        password=findViewById(R.id.passwords);
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("students");




        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_Activity.this, admin_login_Activity.class));
                overridePendingTransition(0, 0);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
                checking();

            }
        });

    }

    private void getdata() {

    }

    private void checking() {
       try{
           String uids =uid.getText().toString();
           String passwords = password.getText().toString();
           reference.child(uids).addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   String userid = snapshot.child("userid").getValue(String.class);
                   String pass =snapshot.child("pass").getValue(String.class);
                   if (uids.equals(userid)){

                       if(passwords.equals(pass)){
                           SharedPreferences sharedPreferences = getSharedPreferences(login_Activity.PREFS_NAME, Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = sharedPreferences.edit();
                           editor.putBoolean("hasloggedIN", true);
                           editor.putString("uid",uids);
                           editor.apply();
                          startActivity(new Intent(getApplicationContext(),Home_Activity.class));


                       }else{
                           password.setError("invalid password");
                       }
                   }else{
                       uid.setError("invalid uid");
                   }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }catch (Exception e){
           uid.setError("somethings want worng");
       }






    }

}