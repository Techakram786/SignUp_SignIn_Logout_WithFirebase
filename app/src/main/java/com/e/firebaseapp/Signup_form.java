package com.e.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_form extends AppCompatActivity {
   EditText e1,e2,e3;
   Button btn_register;
   ProgressBar progressBar;
   private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);

        e1=findViewById(R.id.edit1);
        e2=findViewById(R.id.edit2);
        e3=findViewById(R.id.edit3);
        btn_register=findViewById(R.id.register);
        progressBar=findViewById(R.id.progress);

        firebaseAuth=FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email=e1.getText().toString().trim();//trim remove spaces,it's not  compusary
                String password=e2.getText().toString().trim();
                String con_password=e3.getText().toString().trim();
               // for validate...
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Signup_form.this, "Please fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_form.this, "Please fill password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(con_password)){
                    Toast.makeText(Signup_form.this, "Please fill confirm_password", Toast.LENGTH_SHORT).show();
                    return;
                }
                //for length password...
                if (password.length()<8){
                    Toast.makeText(Signup_form.this, "Too short password", Toast.LENGTH_SHORT).show();
                }
                //for progressbar show...
                progressBar.setVisibility(view.VISIBLE);
                //for matching password....
                if (password.equals(con_password)){

                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(Signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(Signup_form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                    // ...
                                }
                            }); progressBar.setVisibility(view.GONE);
                }

            }
        });
    }
}