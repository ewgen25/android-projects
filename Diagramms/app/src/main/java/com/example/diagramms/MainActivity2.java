package com.example.diagramms;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    BarChart barChart;
    MaterialButton first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        barChart = findViewById(R.id.pieChar);
        first = findViewById(R.id.first);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(Float.parseFloat("23"), 0));
        entries.add(new BarEntry(Float.parseFloat("15"), 1));
        entries.add(new BarEntry(Float.parseFloat("17"), 2));
        entries.add(new BarEntry(Float.parseFloat("24"), 3));

        BarDataSet dataSet = new BarDataSet(entries, "");

        String[] weeks = new String[]{"1 неделя", "2 неделя", "3 неделя", "4 неделя"};

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setBarSpacePercent(0); //расстояние между столбцами диаграммы
        dataSet.setBarShadowColor(Color.rgb(165, 201, 202)); //цвет фона диаграммы
        BarData data = new BarData(weeks, dataSet);
        barChart.animateY(5000); //анимация диаграммы
        barChart.setData(data);
        barChart.setValueTextColor(Color.BLACK);
        barChart.setDrawValueAboveBar(false); //значения погода над столбцом
        barChart.setDescription("Средняя недельная погода за июль");
    }

    public void onClicked(View view) {
        if (view.getId() == R.id.first) {
            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
            MainActivity2.this.startActivity(intent);
            MainActivity2.this.finish();
        }
    }
}