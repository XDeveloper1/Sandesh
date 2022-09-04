package com.XDev.Sandesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Settings_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FirebaseDatabase db;
    DatabaseReference reference;
    TextView tv_fullname, courses, uid,logout;
    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.setting);
        tv_fullname = findViewById(R.id.full_name);
        courses = findViewById(R.id.course);
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        finish();
                        Toast.makeText(Settings_Activity.this, "logout Sucessfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),login_Activity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
        });
        uid = findViewById(R.id.ID);

        String id = getIntent().getStringExtra("data");
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("students");
        reference.child("1202283").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("fname").getValue(String.class);
                String lname = snapshot.child("lname").getValue(String.class);
                String coursess = snapshot.child("courses").getValue(String.class);
                String userid = snapshot.child("userid").getValue(String.class);

                tv_fullname.setText(name + lname);
                uid.setText(userid);
                courses.setText(coursess);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(Settings_Activity.this, Home_Activity.class));
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Chats:
                        startActivity(new Intent(Settings_Activity.this, chats_Activity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.setting:
//                        Intent intent = new Intent(getApplicationContext(), Settings_Activity.class);
//                        overridePendingTransition(0, 0);
//                        startActivity(intent);
//                        finish();

                        return true;

                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Home_Activity.class));
        overridePendingTransition(0, 0);
    }
}