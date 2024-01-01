package com.quanvm.meplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.quanvm.meplanner.task.TaskManagement;

public class MainActivity extends AppCompatActivity {
    TextInputEditText stuNameEt, stuEmailEt, stuPassEt, stuCPassEt;
    Button registerBtn;
    TextView loginTxt;
    CheckBox privacyCheck;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stuNameEt = (TextInputEditText) findViewById(R.id.stuname);
        stuEmailEt = (TextInputEditText) findViewById(R.id.stuemail);
        stuPassEt = (TextInputEditText) findViewById(R.id.stupass);
        stuCPassEt = (TextInputEditText) findViewById(R.id.stucompass);
        loginTxt = (TextView) findViewById(R.id.loginTxt);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        privacyCheck = (CheckBox) findViewById(R.id.acceptcheck);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();


        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = stuNameEt.getText().toString();
                String email = String.valueOf(stuEmailEt.getText());
                String pass = String.valueOf(stuPassEt.getText());
                String cpass =String.valueOf(stuCPassEt.getText());
                boolean checked = privacyCheck.isChecked();

                if(name.isEmpty()){
                    stuNameEt.setError("Student's name is not entered");
                }
                if(email.isEmpty()){
                    stuEmailEt.setError("Email is not entered");
                }
                if(pass.isEmpty()){
                    stuPassEt.setError("Password is not entered");
                }
                if(pass.length() < 6){
                    stuPassEt.setError("Password length is less than 6");
                }
                if(cpass.isEmpty()){
                    stuCPassEt.setError("Confirm password is not entered");
                }

                if(!pass.equals(cpass)){
                    stuCPassEt.setError("Passwords are not matching");
                }

                if (!checked){
                    privacyCheck.setError("Didn't accept statements and conditions");
                }

                if(!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && pass.length() >= 6 && !cpass.isEmpty() && pass.equals(cpass) && checked){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull  Task<AuthResult> task) {
                                    progressBar.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, "Account created.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Account already created",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                }
            }
        });
        loginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }
            }
        });
    }


}