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

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity {



    private static final String TAG = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);


        EditText username=findViewById(R.id.editTextTextPersonName);
        EditText pass=findViewById(R.id.editTextTextPersonName2);
        EditText email=findViewById(R.id.editTextTextPersonName3);
        Button signUp=findViewById(R.id.button2);
        TextView login=findViewById(R.id.button3);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(email.getText()+"",pass.getText()+"",username.getText()+"");

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(signUp.this,login.class);
                startActivity(i);
            }
        });


    }



    private void createAccount(String email, String password,String username) {
        Log.d(TAG, "createAccount:" + email);


        FirebaseAuth  mAuth = FirebaseAuth.getInstance();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUser(email,password,username,mAuth.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(signUp.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }


    public void addUser(String email,String pass ,String username,String Id){

        DatabaseReference mDatabase;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
// ...
        Map<String,String> userData=new HashMap<>();
        userData.put("name",username);
        userData.put("pass",pass);
        userData.put("email",email);
        userData.put("id",Id);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(Id).setValue(userData);
    }



}
