package com.e.firebaseapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2;
    Button lgoutbtn;
    Button btnbrowse, btnupload;
    ImageView imageView;
    Uri imagepath;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textName);
        textView2=findViewById(R.id.textEmail);
        btnbrowse=findViewById(R.id.brosebtn);
        btnupload=findViewById(R.id.uploadbtn);
        imageView=findViewById(R.id.image);
        lgoutbtn=findViewById(R.id.click);
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");
        storageReference = FirebaseStorage.getInstance().getReference();
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imagepath!=null)
                {
                    final ProgressDialog pd = new ProgressDialog(MainActivity.this);
                    pd.setMessage("please wait");
                    pd.show();

                    StorageReference str=storageReference.child("image/"+System.currentTimeMillis() + "." + getFileExt(imagepath));
                    str.putFile(imagepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> data = taskSnapshot.getStorage().getDownloadUrl();
                            while (!data.isSuccessful()) ;
                            String imageUrl = data.getResult().toString();

                            Myrecord record = new Myrecord();
                            record.setUrl(imageUrl);

                            String childId = databaseReference.push().getKey();
                            databaseReference.child(childId).setValue(record);

                            pd.dismiss();
                            Toast.makeText(MainActivity.this, "Data Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            pd.dismiss();
                            Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = taskSnapshot.getBytesTransferred() * 100 / taskSnapshot.getTotalByteCount();

                            pd.setMessage(progress + " %");

                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this, "please select image", Toast.LENGTH_SHORT).show();
                }

            }
        });
        textView1.setText("Emai Id="+getIntent().getStringExtra("Email").toString());
        textView2.setText("User Id="+getIntent().getStringExtra("Uid").toString());
        //textView2.setText("User Id="+getIntent().getStringExtra("ImageUrl").toString());
        lgoutbtn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login_form.class));
            }
        });
        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 1);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            imagepath = data.getData();
            imageView.setImageURI(imagepath);
        }

    }
    public String getFileExt(Uri imagepath)
    {
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imagepath));
    }
}