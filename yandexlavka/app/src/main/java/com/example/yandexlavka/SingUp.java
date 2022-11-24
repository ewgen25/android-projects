package com.example.yandexlavka;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

import java.util.HashMap;
import java.util.Map;

public class SingUp extends AppCompatActivity {

    MaterialButton back, singUp;
    TextInputEditText name, eMail, password, confirm;
    CardView cardView;
    FirebaseAuth mAuth;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        back = findViewById(R.id.back);
        cardView = findViewById(R.id.cardview);
        singUp = findViewById(R.id.signup1);
        name = findViewById(R.id.name);
        eMail = findViewById(R.id.eMail);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);

        singUp.setOnClickListener(new View.OnClickListener() {
            Toast toast;

            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.signup1) {
                    if (!eMail.getText().toString().trim().equals("") ||
                            !name.getText().toString().trim().equals("") ||
                            !password.getText().toString().trim().equals("") ||
                            !confirm.getText().toString().trim().equals("")) {
                        if (password.getText().toString().equals(confirm.getText().toString())
                                && name.length() <= 15) {

                            singUp(eMail.getText().toString(), password.getText().toString());

                        } else {
                            confirm.setError("Doesn't match");
                            confirm.requestFocus();

                        }
                    } else {
                        toast = Toast.makeText(getApplicationContext(), "Fill in the fields", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.TOP, 0, 0);
                        toast.show();


                    }
                }

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View view) {
                if (view.getId() == R.id.back) {
                    intent = new Intent(SingUp.this, MainActivity.class);
                    SingUp.this.startActivity(intent);
                    SingUp.this.finish();
                }
            }
        });


    }

    public void singUp(String email, String pass) {


        mAuth = FirebaseAuth.getInstance();


        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Register();

                            Intent intent = new Intent(SingUp.this, Profile.class);
                            startActivity(intent);
                            SingUp.this.finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                            Toast toast = Toast.makeText(getApplicationContext(), "The email address is already in use by another account.", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.TOP, 0, 0);
                            toast.show();
                        }
                    }
                });


    }

    public void Register() {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("Name", name.getText().toString());
        user.put("Email", eMail.getText().toString());


        db.collection(currentUserser.getEmail()).document(currentUserser.getUid())
                .set(user, SetOptions.merge());


    }
}
