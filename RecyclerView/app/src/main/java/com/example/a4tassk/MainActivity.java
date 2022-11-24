package com.example.a4tassk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Car> cars;

    Intent intent;
    TextView next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setInitialData();
        //поиск recyclerview в разметке
        RecyclerView rwCars = (RecyclerView) findViewById(R.id.rwCars);
        next = (TextView) findViewById(R.id.next);
        //определяем слушателя нажатия элемента в списке
        CarsAdapter.OnCarClickListener carClickListener = new CarsAdapter.OnCarClickListener() {
            @Override
            public void onCarClick(Car car, int position) {

                intent = new Intent(MainActivity.this, CarDescription.class);
                intent.putExtra("carName", car.getCarName());
                intent.putExtra("carId", car.getCarId());
                startActivity(intent);
            }
        };
        cars = Car.createCarList();
        //создание адаптера
        CarsAdapter adapter = new CarsAdapter(cars, carClickListener);
        //установка адаптера к RecyclerView для заполнения элементов
        rwCars.setAdapter(adapter);
        //установка layoutManager
        rwCars.setLayoutManager(new LinearLayoutManager(this));
    }
}

