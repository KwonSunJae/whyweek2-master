package com.algomukja;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.algomukja.Eat.DidEatActivity;
import com.algomukja.Eat.WillEatAcitvity;
import com.algomukja.FactoringUserInformation.Setting_UserActicity;
import com.algomukja.FactoringUserInformation.UserHomeActivity;
import com.algomukja.FactoringUserInformation.UserSettingsw;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private View header;


    private int currentApiVersion;

    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }

        setContentView(R.layout.activity_main);


        UserSettingsw mSP = UserSettingsw.getmInstance(this);

        init();
        if (mSP.getUserID().equals("um")) {
            Intent intent = new Intent(this, LoadingSplashActivity.class);
            startActivity(intent);
            Intent intent1 = new Intent(this, UserHomeActivity.class);
            startActivity(intent1);
        }
        if (mSP.getAge() == 0) {
            Intent intent1 = new Intent(this, Setting_UserActicity.class);
            startActivity(intent1);
        }

    }
    private class LabelFormatter implements IAxisValueFormatter {

        String[] labels;
        BarLineChartBase<?> chart;

        LabelFormatter(BarLineChartBase<?> chart, String[] labels) {
            this.chart = chart;
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return labels[(int) value];
        }
    }

    private void init() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_main, null);




        BarChart chart = findViewById(R.id.barchart);
        chart.setDrawBarShadow(false);

        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        String[] label = {"", "","","","","",""};
        IAxisValueFormatter xAxisFormatter = new LabelFormatter(chart, label);
        XAxis xAxis = chart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1); // only intervals of 1 day
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularity(0);
        leftAxis.setLabelCount(4, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //// 탄단지나칼 값넣기
        float[] valOne = {10};
        float[] valTwo = {60};
        float[] valThree = {20};
        float[] valFour = {40};
        float[] valFive = {60};

        ArrayList<BarEntry> barOne = new ArrayList<>();
        ArrayList<BarEntry> barTwo = new ArrayList<>();
        ArrayList<BarEntry> barThree = new ArrayList<>();
        ArrayList<BarEntry> barFour = new ArrayList<>();
        ArrayList<BarEntry> barFive = new ArrayList<>();
        for (int i = 0; i < valOne.length; i++) {
            barOne.add(new BarEntry(i, valOne[i]));
            barTwo.add(new BarEntry(i, valTwo[i]));
            barThree.add(new BarEntry(i, valThree[i]));
            barFour.add(new BarEntry(i, valFour[i]));
            barFive.add(new BarEntry(i, valFive[i]));
        }
        int color = ContextCompat.getColor(this, R.color.chart_color);
        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        BarDataSet set1 = new BarDataSet(barOne, "barOne");
        set1.setColor(color);
        BarDataSet set2 = new BarDataSet(barTwo, "barTwo");
        set2.setColor(color);
        BarDataSet set3 = new BarDataSet(barThree, "barTwo");
        set3.setColor(color);
        BarDataSet set4 = new BarDataSet(barFour, "barTwo");
        set4.setColor(color);
        BarDataSet set5 = new BarDataSet(barFive, "barTwo");
        set5.setColor(color);

        set1.setHighlightEnabled(false);
        set1.setDrawValues(false);
        set2.setHighlightEnabled(false);
        set2.setDrawValues(false);
        set3.setHighlightEnabled(false);
        set3.setDrawValues(false);
        set4.setHighlightEnabled(false);
        set4.setDrawValues(false);
        set5.setHighlightEnabled(false);
        set5.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);
        dataSets.add(set4);
        dataSets.add(set5);
        BarData data = new BarData(dataSets);
        float groupSpace = 0f;
        float barSpace = 0.1f;
        float barWidth = 0.3f;
        // (barSpace + barWidth) * 5 + groupSpace = 1
        // multiplied by 5 because there are 5 five bars
        // labels will be centered as long as the equation is satisfied
        data.setBarWidth(barWidth);
        // so that the entire chart is shown when scrolled from right to left
        xAxis.setAxisMaximum(label.length - 1.1f);
        chart.setData(data);
        chart.setScaleEnabled(false);
        chart.setVisibleXRangeMaximum(2f);
        chart.groupBars(1f, groupSpace, barSpace);
        chart.invalidate();
        Button Profile = findViewById(R.id.Btnprofile);
        Button DidEat = findViewById(R.id.Btndideat);
        Button WillEat = findViewById(R.id.Btnwilleat);
        ////////////////////////////////////////////////////
        DidEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DidEatActivity.class);
                Log.d("sunjae", "succecc");
                startActivity(intent);
                Log.d("sunjae", "succecc");
            }
        });
        WillEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, WillEatAcitvity.class);
                startActivity(intent2);
            }
        });
        Profile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        Button Detail = findViewById(R.id.Btndetail);
        Detail.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });

    }

    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }


    }
}