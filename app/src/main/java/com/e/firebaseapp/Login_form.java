package com.e.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_form extends AppCompatActivity {
    EditText e1, e2;
    Button btn_login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("Login Form");
        e1 = findViewById(R.id.edit1);
        e2 = findViewById(R.id.edit2);
        btn_login = findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = e1.getText().toString().trim();//trim remove spaces,it's not  compusary
                String password = e2.getText().toString().trim();
                //for validate....
                if (TextUtils.isEmpty(Email)) {
                    Toast.makeText(Login_form.this, "Please fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                   Toast.makeText(Login_form.this, "Please fill password", Toast.LENGTH_SHORT).show();
                    return;

                }
                //for length...
                if (password.length() < 4) {
                    Toast.makeText(Login_form.this, "Too short password", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.signInWithEmailAndPassword(Email, password)


                        .addOnCompleteListener(Login_form.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login_form.this, "login failed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }

            //for signup button...


        });

    }
    public void btn_signup_form(View view) {
        startActivity(new Intent(getApplicationContext(), Signup_form.class));
    }
}

