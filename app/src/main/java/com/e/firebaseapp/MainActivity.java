package com.e.firebaseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.textEmail);
        textView2=findViewById(R.id.textUid);
       // textView3=findViewById(R.id.textUrl);
        btn=findViewById(R.id.logot);
        textView1.setText("Emai Id="+getIntent().getStringExtra("Email").toString());
        textView2.setText("User Id="+getIntent().getStringExtra("Uid").toString());
        //textView2.setText("User Id="+getIntent().getStringExtra("ImageUrl").toString());
        btn.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login_form.class));
            }
        });

    }
}