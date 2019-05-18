package com.example.helloword;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;
    private TextInputEditText email ;
    private TextInputEditText password;
    private Button submit;
    private TextView register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mAuth = FirebaseAuth.getInstance();
        this.email = findViewById(R.id.email);
        this.password = findViewById(R.id.password);
        this.submit = findViewById(R.id.submit);
        this.register = findViewById(R.id.register);
        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { login(email.getText().toString(), password.getText().toString()); }
        });
        this.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(NewUserActivity.class);
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        goTo(MainContainerActivity.class);
        // updateUI(currentUser);
    }
    private void login (String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(MainActivity.this, "Success Authentication", Toast.LENGTH_SHORT).show();
                    goTo(MainContainerActivity.class);
                    // updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    // updateUI(null);
                }
            }
        });
    }
    public void goTo (Class Activity) {
        Intent intent = new Intent(this, Activity);
        startActivity(intent);
    }

}
