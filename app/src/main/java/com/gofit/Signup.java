package com.gofit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText userNameET;
    EditText passwordET;
    FirebaseAuth mAuth;
    private static final String USER_CREATION_SUCCESS =  "Successfully created user";
    private static final String USER_CREATION_ERROR =  "User creation error";
    private static final String EMAIL_INVALID =  "email is invalid :";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup = (Button) findViewById(R.id.btn_signup2);
        mAuth = FirebaseAuth.getInstance();
        userNameET = (EditText)findViewById(R.id.edit_text_email2);
        passwordET = (EditText)findViewById(R.id.edit_text_password2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }
    // Validate email address for new accounts.
    private boolean isEmailValid(String email) {
        boolean isGoodEmail = (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            userNameET.setError(EMAIL_INVALID + email);
            return false;
        }
        return true;
    }
    // create a new user in Firebase
    public void createUser() {
        if(userNameET.getText() == null ||  !isEmailValid(userNameET.getText().toString())) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(userNameET.getText().toString(),passwordET.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mAuth.signOut();
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Snackbar snackbar = Snackbar.make(userNameET, USER_CREATION_ERROR, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
        });
    }
}



