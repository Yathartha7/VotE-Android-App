package com.example.votingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_Activity extends AppCompatActivity {
    TextInputEditText emailTextL,passwordTextL;
    Button btnLogin;
    FirebaseAuth mAuth;
    TextView txt;
   /* @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            String email = currentUser.getEmail();
            assert email != null;
            if (email.charAt(0) == 'v') {
                Intent intent = new Intent(getApplicationContext(), voter_dashboard.class);
                startActivity(intent);
                finish();

            } else if (email.charAt(0) == 'c') {
                Intent intent1 = new Intent(getApplicationContext(), candidate_dashboard.class);
                startActivity(intent1);
                finish();

            } else if (email.charAt(0) == 'a') {
                Intent intent2 = new Intent(getApplicationContext(), admin_dashboard.class);
                startActivity(intent2);
                finish();

            }

        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailTextL= findViewById(R.id.login_email);
        passwordTextL = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.login_button);
        txt = findViewById(R.id.signupNow);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),signUp_Activity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email = String.valueOf(emailTextL.getText());
                password = String.valueOf(passwordTextL.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login_Activity.this,"Enter email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(login_Activity.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(login_Activity.this, "Login successful",
                                            Toast.LENGTH_SHORT).show();
                                    if (email.charAt(0) == 'v') {
                                        Intent intent = new Intent(getApplicationContext(), voter_dashboard.class);
                                        startActivity(intent);
                                        finish();

                                    } else if (email.charAt(0) == 'c') {
                                        Intent intent1 = new Intent(getApplicationContext(), candidate_dashboard.class);
                                        startActivity(intent1);
                                        finish();

                                    } else if (email.charAt(0) == 'a') {
                                        Intent intent2 = new Intent(getApplicationContext(), admin_dashboard.class);
                                        startActivity(intent2);
                                        finish();

                                    }
                                }else{
                                // If sign in fails, display a message to the user.
                                    Toast.makeText(login_Activity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
    }
