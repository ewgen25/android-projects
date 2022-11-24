package com.example.retrofitdiag;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView da;
    BarChart barChart;
    ArrayList<BarEntry> countryPopulation;
    ArrayList<String> countyName;
    RadioGroup radioGroup;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barChart = findViewById(R.id.barChart);
        da = findViewById(R.id.da);
        radioGroup = findViewById(R.id.radioGroup);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        JsonApi json = retrofit.create(JsonApi.class);
        Call<List<Country>> call = json.getPosts();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (!response.isSuccessful()) {
                    da.setText(response.code());
                    return;
                }


                List<Country> posts = response.body();

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int button) {
                        countryPopulation = new ArrayList<>();
                        countyName = new ArrayList<>();
                        if (button == R.id.top) {
                            Collections.sort(posts);
                            da.setText(null);

                            for (int i = 1; i < 6; i++) {
                                countryPopulation.add(new BarEntry(Integer.parseInt((posts.get(posts.size() - i)).getPopulation()), i - 1));
                                countyName.add((posts.get(posts.size() - i)).getName());
                                da.append(" " + i + " - " + posts.get(posts.size() - i).getName() + "\n");

                            }


                        } else if (button == R.id.random) {
                            da.setText(null);
                            random = new Random();
                            for (int i = 1; i < 6; i++) {
                                int countryIndex = random.nextInt(posts.size());
                                countryPopulation.add(new BarEntry(Integer.parseInt((posts.get(countryIndex)).getPopulation()), i - 1));
                                countyName.add((posts.get(countryIndex).getName()));
                                da.append(" " + i + " - " + posts.get(countryIndex).getName() + "\n");

                            }
                        }

                        BarDataSet dataSet = new BarDataSet(countryPopulation, "");
                        dataSet.setColor(Color.rgb(165, 201, 202));
                        //dataSet.setBarSpacePercent(1); //расстояние между столбцами диаграммы
                        dataSet.setBarShadowColor(Color.rgb(57, 91, 100)); //цвет фона диаграммы
                        BarData barData = new BarData(countyName, dataSet);
                        barChart.animateY(5000); //анимация диаграммы
                        barChart.setData(barData);
                        barChart.setDrawGridBackground(false);
                        barChart.setDrawYLabels(false);
                        barChart.setDrawXLabels(false);
                        barChart.setValueTextColor(Color.BLACK);
                        barChart.setDrawValueAboveBar(false); //значения погода над столбцом
                        barChart.setDescription(" " + posts.size());

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                da.setText(t.getMessage());

            }
        });

    }
}