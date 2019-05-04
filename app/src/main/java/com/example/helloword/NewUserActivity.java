package com.example.helloword;

import android.support.annotation.NonNull;
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

public class NewUserActivity extends AppCompatActivity {
    private static final String TAG = "NewUserActivity";
    private TextView emailRegister;
    private TextView passwordRegister;
    private Button submitRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        this.mAuth = FirebaseAuth.getInstance();
        this.emailRegister = findViewById(R.id.emailRegister);
        this.passwordRegister = findViewById(R.id.passwordRegister);
        this.submitRegister = findViewById(R.id.submitRegister);
        this.submitRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }
    protected void createAccount() {
        mAuth.createUserWithEmailAndPassword(this.emailRegister.getText().toString(), this.passwordRegister.getText().toString())
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(NewUserActivity.this, "Usuario registrado correctamente :)",
                            Toast.LENGTH_LONG).show();
                    finish();
                    // updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(NewUserActivity.this, "No se pudo registar el usuario.",
                            Toast.LENGTH_SHORT).show();
                    // updateUI(null);
                }

                // ...
            }
        });

    }
}
