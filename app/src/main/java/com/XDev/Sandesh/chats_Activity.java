package com.XDev.Sandesh;

import static com.XDev.Sandesh.R.drawable.*;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class chats_Activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Button u_button, c_button;
    ImageView imageView;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    Uri vediouri;
    ProgressBar progressBar;
    String vediourl;
    String vediomainurl;
    String extension;

//    Uri uri;

    boolean videos = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.Chats);
        u_button = findViewById(R.id.upload_button);
        c_button = findViewById(R.id.clear_button);
        imageView = findViewById(R.id.upload_button_image_view);
        progressBar = findViewById(R.id.progressBar);

        u_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (vediouri != null) {
                    uploadfirebase(vediouri);
                } else {

                    Intent gaintent = new Intent();
                    gaintent.setAction(Intent.ACTION_GET_CONTENT);
                    gaintent.setType("image/*|application/pdf|audio/*");
                    startActivityForResult(gaintent, 2);
                }
            }


        });
        c_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gaintent = new Intent();
                gaintent.setAction(Intent.ACTION_GET_CONTENT);
                gaintent.setType("*/*");
                startActivityForResult(gaintent, 2);
            }
        });


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(chats_Activity.this, Home_Activity.class));
                        overridePendingTransition(0, 0);

                        return true;

                    case R.id.Chats:


                        return true;
                    case R.id.setting:
                        startActivity(new Intent(chats_Activity.this, Settings_Activity.class));
                        overridePendingTransition(0, 0);

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


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            vediouri = data.getData();
            if (extension == "pdf") {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    imageView.setImageResource(acorbat);
                } else {
                    System.out.println("khatam");
                }
            }

        }
    }

    private String getfileExtensiom(Uri muri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
         extension = mime.getExtensionFromMimeType(cr.getType(muri));
        System.out.println(extension);

        return extension;
    }








    private void uploadfirebase(Uri uri) {
        StorageReference fileref = storageReference.child(System.currentTimeMillis() + "." + getfileExtensiom(uri));
        fileref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                            progressBar.setVisibility(View.VISIBLE);
                        vediourl = uri.toString();
                        vediomainurl = uri.toString();
                        udata udata = new udata(vediomainurl);


                        HashMap urldata = new HashMap();
                        urldata.put("vediourl", vediourl);
                        reference.child("unidata").push().setValue(udata);
                        reference.child("1202283").child("vediourl").updateChildren(urldata).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (task.isSuccessful()) {
                                        progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(chats_Activity.this, "upload sucess", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(chats_Activity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                    }
                });


            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    progressBar.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(chats_Activity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

