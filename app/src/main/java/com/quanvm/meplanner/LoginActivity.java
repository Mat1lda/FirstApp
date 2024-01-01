package com.quanvm.meplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.quanvm.meplanner.task.TaskManagement;

public class LoginActivity extends AppCompatActivity {
    EditText  stuEmailEt, stuPassEt;
    Button loginBtn;
    TextView registerTxt;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        stuEmailEt = (EditText) findViewById(R.id.stulemail);
        stuPassEt = (EditText) findViewById(R.id.stulpass);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerTxt = (TextView) findViewById(R.id.registerTxt);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        firebaseAuth = FirebaseAuth.getInstance();

        registerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = String.valueOf(stuEmailEt.getText());
                String pass = String.valueOf(stuPassEt.getText());

                if(email.isEmpty()){
                    stuEmailEt.setError("Email is not entered");
                }
                if(pass.isEmpty()){
                    stuPassEt.setError("Password is not entered");
                }
                if(!email.isEmpty() && !pass.isEmpty()){
                    firebaseAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.VISIBLE);

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Toast.makeText(LoginActivity.this, "Login OK",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(LoginActivity.this, TaskManagement.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}