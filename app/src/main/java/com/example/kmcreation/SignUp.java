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

public class SignUp extends AppCompatActivity {
    EditText email, password;
    String user,pass;
    TextView already;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        already = findViewById(R.id.already);
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, LogIn.class);
                startActivity(i);
                finish();
            }
        });

        btn=findViewById(R.id.SignUpBtn);
        email=(EditText)findViewById(R.id.emailInput);
        password=(EditText)findViewById(R.id.passwordInput);
        //nlasa9ha to go to home page
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createAnAccount(v);
            }
        });
    }


    //Create a new acc
    public void createAnAccount(View view) {
        user=email.getText().toString();
        pass=password.getText().toString();

        if(user.isEmpty()){
            Toast.makeText(SignUp.this,"Enter your email",Toast.LENGTH_LONG).show();
            return;

        }
        if(pass.isEmpty()){
            Toast.makeText(SignUp.this,"Enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Account successfully created, login to continue",Toast.LENGTH_SHORT).show();

                }else
                    Toast.makeText(SignUp.this,"Failure:"+ task.getException().getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}