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

public class MainActivity extends AppCompatActivity {

    MaterialButton second, third;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BarChart barChart = findViewById(R.id.barChart);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));
        BarDataSet dataSet = new BarDataSet(entries, "");


        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Январь");
        labels.add("Февраль");
        labels.add("Март");
        labels.add("Апрель");
        labels.add("Май");
        labels.add("Июнь");

        //BarChart chart = new BarChart(this);
        // setContentView(chart);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);



        BarData data = new BarData(labels, dataSet);
        barChart.animateY(5000);
        barChart.setData(data);
        barChart.setDrawGridBackground(false);

        barChart.setValueTextColor(Color.BLACK);
        barChart.setDescription("");

    }

    public void onClicked(View view) {
        if (view.getId() == R.id.second) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            MainActivity.this.startActivity(intent);
            MainActivity.this.finish();
        }

    }
}