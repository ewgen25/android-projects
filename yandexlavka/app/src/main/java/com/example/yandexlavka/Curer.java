package com.example.yandexlavka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static android.content.ContentValues.TAG;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Curer extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView infa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curer);
        Read();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        infa = findViewById(R.id.infa);

    }

    public void Read() {


        ArrayList<String> arrayList = new ArrayList<>();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(currentUserser.getEmail())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String name = String.valueOf(document.get("Name"));
                                String phone = String.valueOf(document.get("Phone"));
                                String address = String.valueOf(document.get("Address"));
                                String cost = String.valueOf(document.get("Cost"));

                                Integer count = Integer.parseInt((String) document.get("Count"));

                                for (int i = 0; i <= count; i++) {
                                    arrayList.add (String.valueOf(document.get(""+(i+1))));
                                    infa.append( arrayList.get(i) +"\n");
                                }







                                infa.append("\nИмя клиента: " + name + "\n" + "Номер: " + phone + "\n"
                                + "Адрес: " + address + "\n" + "Стоимость заказа: " + cost + " руб") ;


                            }
                        }
                    }
                });
    }
}