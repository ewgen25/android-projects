package com.example.a4tassk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;

public class CarDescription extends AppCompatActivity {

    InputStream inputStream;
    TextView act2CarName, carDescription;
    ImageView act2CarImg, backBtn;
    String carName, carId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_description);

        act2CarImg = (ImageView) findViewById(R.id.act2CarImg);
        act2CarName = (TextView) findViewById(R.id.act2CarName);
        backBtn = (ImageView) findViewById(R.id.backBtn);

        carName = getIntent().getStringExtra("carName");
        carId = getIntent().getStringExtra("carId");
        act2CarImg.setImageResource(getResources().getIdentifier(carId, "drawable", getPackageName()));
        act2CarName.setText(carName);

        switch (carId) {
            case "id1":
                inputStream = getResources().openRawResource(R.raw.id1);
                fulling();
                break;
            case "id2":
                inputStream = getResources().openRawResource(R.raw.id2);
                fulling();
                break;
            case "id3":
                inputStream = getResources().openRawResource(R.raw.id3);
                fulling();
                break;
            case "id4":
                inputStream = getResources().openRawResource(R.raw.id4);
                fulling();
                break;
            case "id5":
                inputStream = getResources().openRawResource(R.raw.id5);
                fulling();
                break;
        }
    }

    public void fulling() {
        carDescription = (TextView) findViewById(R.id.carDescription);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        carDescription.setText(byteArrayOutputStream.toString());
    }

    public void onClicked(View v){
        if (v.getId()==R.id.backBtn){
            Intent intent = new Intent(CarDescription.this, MainActivity.class);
            startActivity(intent);
        }
    }
}