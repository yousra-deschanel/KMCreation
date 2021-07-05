package com.example.kmcreation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    TextView create;
    EditText email, password;
    String user,pass;
    Button btnLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        create = findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, SignUp.class);
                startActivity(i);
                finish();
            }
        });

        btnLog=findViewById(R.id.login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        //nlasa9ha to go to home page
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login(v);
            }
        });

    }

    public void login(View view) {
        user=email.getText().toString();
        pass=password.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(LogIn.this,"Enter an email",Toast.LENGTH_SHORT).show();
            return;

        }
        if(pass.isEmpty()){
            Toast.makeText(LogIn.this,"Enter a password",Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    finish();

                }else
                    Toast.makeText(LogIn.this,"Failure:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

            }

        });
    }


    public void resetPassword(View view) {
        user=email.getText().toString();
        if(user.isEmpty()){
            Toast.makeText(LogIn.this,"Entrer une @ email",Toast.LENGTH_SHORT).show();
            return;

        }
        FirebaseAuth auth= FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LogIn.this,"Reset email envoyé avec succés, verifier votre email.",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(LogIn.this,"Echec:"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();


            }
        });
    }
}