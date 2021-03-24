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
   EditText e1,e2,e3,e4,e5;
   Button btn_register;
   ProgressBar progressBar;
   private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        //remove status bar.......
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        e1=findViewById(R.id.edit1);
        e2=findViewById(R.id.edit2);
        e3=findViewById(R.id.edit3);
        e4=findViewById(R.id.edit4);
        e5=findViewById(R.id.edit5);
        btn_register=findViewById(R.id.register);
        progressBar=findViewById(R.id.progress);

        firebaseAuth=FirebaseAuth.getInstance();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // ProgressDialog pd=new ProgressDialog(Signup_form.this);

                // progressBar.setVisibility(view.VISIBLE);
//                String FName=e1.getText().toString().trim();//trim remove spaces,it's not  compusary
//                String UName=e2.getText().toString().trim();
                String Email=e3.getText().toString().trim();
                String password=e4.getText().toString().trim();
//                String con_password=e5.getText().toString().trim();
               // for validate...
               /* check if any of the fields are vaccant
                if(Email.equals("")||password.equals("")||confirmPassword.equals(""))
                {
                Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                return;
                }
                 check if both password matches
                 if(!password.equals(confirmPassword))
                     {
                       Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                       return;
                      }
                */
                //or
                // progressBar.setVisibility(view.VISIBLE);
//                if(TextUtils.isEmpty(FName)){
//                    Toast.makeText(Signup_form.this, "Please fill Full Name", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if(TextUtils.isEmpty(UName)){
//                    Toast.makeText(Signup_form.this, "Please fill User Name", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Signup_form.this, "Please fill Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Signup_form.this, "Please fill password", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if(TextUtils.isEmpty(con_password)){
//                    Toast.makeText(Signup_form.this, "Please fill confirm_password", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                //for length password...
//                if (password.length()<8){
//                    Toast.makeText(Signup_form.this, "Too short password", Toast.LENGTH_SHORT).show();
//                }
                //for progressbar show...

                //for matching password....
               // if (password.equals(con_password)){

                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(Signup_form.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        e1.setText("");
//                                        e2.setText("");
//                                        e3.setText("");
//                                        e4.setText("");
//                                        e5.setText("");
                                        Toast.makeText(Signup_form.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));


                                    } else {
//                                        e1.setText("");
//                                        e2.setText("");
//                                        e3.setText("");
//                                        e4.setText("");
//                                        e5.setText("");
                                        Toast.makeText(Signup_form.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                    // ...
                                }
                            });// progressBar.setVisibility(view.GONE);
                }

            //}
        });
    }
}