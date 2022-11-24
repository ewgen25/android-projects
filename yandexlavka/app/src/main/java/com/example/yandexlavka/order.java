package com.example.yandexlavka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class order extends AppCompatActivity {

    MaterialButton makeOrder, back, btncurer;
    Intent intent;
    FirebaseAuth mAuth;
    TextInputEditText phone, adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();



        makeOrder = findViewById(R.id.oformOrder);
        back = findViewById(R.id.back2);
        phone = findViewById(R.id.phone);
        adress = findViewById(R.id.adress);
        btncurer = findViewById(R.id.btncurer);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.back2) {
                    intent = new Intent(order.this, Profile.class);
                    order.this.startActivity(intent);
                    order.this.finish();

                }
            }
        });

        makeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.oformOrder) {
                    Send();
                    Toast toast = Toast.makeText(getApplicationContext(), "Заказ оформлен", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }
        });

        btncurer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btncurer) {
                    Intent intent = new Intent(order.this, Curer.class);
                    order.this.startActivity(intent);
                    order.this.finish();
                }
            }
        });
    }



    public void Send () {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserser = mAuth.getCurrentUser();
        Map<String, Object> user = new HashMap<>();
        user.put("Phone", phone.getText().toString());
        user.put("Address", adress.getText().toString());



// Add a new document with a generated ID


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(currentUserser.getEmail()).document(currentUserser.getUid())
                .set(user, SetOptions.merge());
        }
    }
