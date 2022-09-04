package com.XDev.Sandesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class new_regis extends AppCompatActivity {
    Spinner spinner;
    EditText firstname;
    EditText lastname;
    EditText phone;
    EditText password;
    EditText confrimpassword;
    EditText uid;
    TextView result;
    Button button;
    int year;
    int date;
    int month;
    String y, m, d;
    String uids;

    FirebaseDatabase db;

    DatabaseReference reference;


    String fname, lname, pass, cpass, courses,  userid;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_regis);
        button = findViewById(R.id.new_regis_button);
        spinner = findViewById(R.id.spinner);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confrimpassword = findViewById(R.id.confrimpassword);
        spinner = findViewById(R.id.spinner);
        result = findViewById(R.id.textView);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        date = calendar.get(Calendar.DATE);
        uid = findViewById(R.id.UID);
        y = String.valueOf(year);
        m = String.valueOf(month);
        d = String.valueOf(date);
        String uids;
        db = FirebaseDatabase.getInstance();
        reference = db.getReference("students");
        




        genrateuid();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fname = firstname.getText().toString();
                lname =lastname.getText().toString();
                pass = password.getText().toString();
                cpass = confrimpassword.getText().toString();
                courses =spinner.getSelectedItem().toString();
                userid =uid.getText().toString();

                new_regis_model newRegisModel=new new_regis_model(fname, lname, pass, cpass, courses,  userid);
                if(reference.child(userid).setValue(newRegisModel).isSuccessful()){
                    Toast.makeText(new_regis.this, "sucess", Toast.LENGTH_SHORT).show();
                }


            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.courses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        lastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confrimpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setdata() {
       
        }
        
            






    private void genrateuid() {
        uids = 1+y+m+d;
        uid.setText(uids);






    }


    private void checkInputs() {
        if (!TextUtils.isEmpty(firstname.getText())) {
            if (!TextUtils.isEmpty(lastname.getText())) {
                if (!TextUtils.isEmpty(password.getText()) && password.length() >= 8) {
                    if (password.getText().toString().equals(confrimpassword.getText().toString())) {
                        if (!TextUtils.isEmpty(phone.getText()) && phone.length() >= 10) {


                            button.setEnabled(true);

                        } else {
                            phone.setError("please check phone number");
                        }
                        button.setEnabled(true);

                    } else {
                        button.setEnabled(false);
                        confrimpassword.setError("password does not match");
                    }


                } else {
                    password.setError("password length minimum 8 charcters");
                }
            } else {
                button.setEnabled(false);
                lastname.setError("please enter last name");
            }
        } else {
            button.setEnabled(false);
            firstname.setError("please enter firstname");
        }



    }
}