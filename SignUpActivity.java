package com.example.beathunger1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button btn_signup;
    Spinner spn_gender;
    EditText etSignupEmail,etSignupPassword,etSignupAge;
    FirebaseAuth mFirebaseAuth;
    String email,password,age,gender;
    CheckBox showPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_signup = findViewById(R.id.btn_signup);
        spn_gender = findViewById(R.id.spn_gender);
        etSignupEmail= findViewById(R.id.et_signup_email);
        etSignupPassword = findViewById(R.id.et_signup_password);
        etSignupAge = findViewById(R.id.et_signup_age);
        showPassword = findViewById(R.id.show_password2);
        mFirebaseAuth = FirebaseAuth.getInstance();

        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if(b){
                    etSignupPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    etSignupPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = etSignupEmail.getText().toString().trim();
                password = etSignupPassword.getText().toString().trim();
                age = etSignupAge.getText().toString().trim();
                gender = spn_gender.getSelectedItem().toString();

                if (TextUtils.isEmpty(email)) {
                    etSignupEmail.setError("Enter Your Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    etSignupPassword.setError("Set You Password");
                    return;
                }
                if (password.length() < 6) {
                    etSignupPassword.setError("Please make sure password length is more than 6 characters!");
                    return;
                }
                if (TextUtils.isEmpty(age)) {
                    etSignupAge.setError("Enter Your Age");
                    return;
                }

                mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            finish();
                            addUserInfo();
                            Toast.makeText(SignUpActivity.this,"Account Created",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "Error. Please try again." +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void addUserInfo() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");
        DatabaseReference ref = firebaseDatabase.getReference(mFirebaseAuth.getUid());
        User users = new User(email,age,gender);
        ref.setValue(users);
    }
}