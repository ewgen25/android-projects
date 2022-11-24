package com.example.yandexlavka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {


    ArrayList<Food> foods;
    ArrayList<String> choises;
    TextView bag, price;
    double totalPrice = 0;
    MaterialButton order, clear;
    int btnFlg = 0;
    FirebaseAuth mAuth;
    Map<String, Object> hinkaliSets = new HashMap<>();
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        choises = new ArrayList<>();
        bag = findViewById(R.id.bag);
        price = findViewById(R.id.price);
        RecyclerView rwFood = findViewById(R.id.rwFood);
        order = findViewById(R.id.order);
        order.setVisibility(View.INVISIBLE);
        clear = findViewById(R.id.clear);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        FoodAdapter.OnFoodClickListener foodClickListener = new FoodAdapter.OnFoodClickListener() {

            String orderFull;
            @Override
            public void onFoodClick(Food food, int position) {

                count++;
                bag.append(" " + food.getName() + "\n");
                totalPrice = totalPrice + food.getPrice();
                price.setText(" " + totalPrice);
                if (!bag.getText().toString().trim().equals("")) {
                    order.setVisibility(View.VISIBLE);
                }

                hinkaliSets.put("" + count, "" +food.getName());



            }

        };


        foods = Food.createFoodList();
        //создание адаптера
        FoodAdapter adapter = new FoodAdapter(foods, foodClickListener);
        //установка адаптера к recyclerview для заполнения элементов
        rwFood.setAdapter(adapter);
        //установка layoutManager
        rwFood.setLayoutManager(new LinearLayoutManager(Profile.this));

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.clear) {
                    bag.setText(null);
                    totalPrice = 0;
                    price.setText(null);
                    order.setVisibility(View.INVISIBLE);
                    hinkaliSets.clear();

                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.order) {
                    send();

                    Intent intent = new Intent(Profile.this, order.class);


                    Profile.this.startActivity(intent);
                    Profile.this.finish();
                }
            }
        });
    }


    public void send() {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUserser = mAuth.getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        hinkaliSets.put("Cost", "" + totalPrice);
        hinkaliSets.put("Count", String.valueOf(count));

        db.collection(currentUserser.getEmail()).document(currentUserser.getUid())
                .set(hinkaliSets, SetOptions.merge());


    }

}