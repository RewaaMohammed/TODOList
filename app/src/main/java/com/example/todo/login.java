package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);


        EditText email=findViewById(R.id.email);
        EditText pass=findViewById(R.id.pass);
        Button signIn=findViewById(R.id.button4);
        TextView signup=findViewById(R.id.button5);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email.getText()+"",pass.getText()+"");

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(login.this,signUp.class);
                startActivity(i);
            }
        });
    }



    private void signIn(String email, String password) {
        Log.d("TAG", "signIn:" + email);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();



        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i= new Intent(login.this,lists.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // [START_EXCLUDE]
                            // [END_EXCLUDE]
                        }

                        // [START_EXCLUDE]

                        if (!task.isSuccessful()) {
                            Toast.makeText(login.this, "invaled email or password",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }
}
