package com.e.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {
   EditText e1,e3,e4;
   Button btn_register;

   private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        //remove status bar.......
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        e1=findViewById(R.id.edit1);
        e3=findViewById(R.id.edit3);
        e4=findViewById(R.id.edit4);
        btn_register=findViewById(R.id.register);
        firebaseAuth=FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=e3.getText().toString().trim();
                String password=e4.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) { if (task.isSuccessful()) {

                                        Toast.makeText(Signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                    } else {

                                        Toast.makeText(Signup_form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }

        });
    }
}