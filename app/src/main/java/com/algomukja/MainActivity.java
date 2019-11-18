package com.algomukja;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.algomukja.DideatListview.Food;
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


    public static Activity activity;
    private int currentApiVersion;
    private BarChart chart;
    private BackPressCloseHandler backPressCloseHandler;

    @Override public void onBackPressed() { super.onBackPressed(); backPressCloseHandler.onBackPressed(); }


    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = MainActivity.this;
        backPressCloseHandler = new BackPressCloseHandler(this);
        Log.d("ksu",R.drawable.alio+" "+R.drawable.ohyes);

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

        int code = 0;

        Intent intent2 = getIntent();
        if(intent2.hasExtra("flag")){
            code = intent2.getExtras().getInt("flag",0);
        }
        if (mSP.getAge() == 0) {
            Intent intent1 = new Intent(this, Setting_UserActicity.class);
            intent1.putExtra("flag",1);
            startActivity(intent1);

        }
        if(code!=1){
            Intent intent = new Intent(this, LoadingSplashActivity.class);
            startActivity(intent);
        }

    }
    public static class LabelFormatter implements IAxisValueFormatter {

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




        chart = findViewById(R.id.barchart);
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

        UserSettingsw us = new UserSettingsw(this);
        Food pe = us.getPerson();
        //// 탄단지나칼 값넣기
//        float[] valOne = {(int)(us.getTan()/390*100)};
//        float[] valTwo = {(int)(us.getPro()/65*100)};
//        float[] valThree = {(int)(us.getFat()/72*100)};
//        float[] valFour = {(int)(us.getNat()/1500*100)};
//        float[] valFive = {(int)(us.getJul()/2600*100)};
        ArrayList<Food>  t = us.getFood();
        int tan=0,dan=0,gi=0,na=0,kcal=0;
        if(t!=null){
            for(int i=0; i<us.getfnumber();i++){
                tan+=t.get(i).getTansu();
                dan+=t.get(i).getProtein();
                gi+=t.get(i).getFat();
                na+=t.get(i).getNat();
                kcal += t.get(i).getJul();
                Log.d("roTlqkftus",Integer.toString(kcal));
            }
        }
        Log.d("roTlqkftus",Integer.toString(kcal));
        float[] valOne = {(int)(tan/390.0*100)};
        float[] valTwo = {(int)(dan/65.0*100)};
        float[] valThree = {(int)(gi/72.0*100)};
        float[] valFour = {(int)(na/1500.0*100)};
        float[] valFive = {(int)(kcal/2600.0*100)};
        final int color[] = {ContextCompat.getColor(this, R.color.chart_color3),ContextCompat.getColor(this, R.color.chart_color4),ContextCompat.getColor(this, R.color.chart_color5),ContextCompat.getColor(this, R.color.colorPrimaryDark),ContextCompat.getColor(this,R.color.chart_color2)};
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

        chart.getAxisRight().setEnabled(false);
        chart.getLegend().setEnabled(false);
        BarDataSet set1 = new BarDataSet(barOne, "barOne");
        int temp =0;
        if(valOne[0]<=80){
            temp=2;
        }
        else if(valOne[0]<=120){
            temp=1;
        }
        set1.setColor(color[temp]);
        BarDataSet set2 = new BarDataSet(barTwo, "barTwo");
        temp =0;
        if(valTwo[0]<=80){
            temp=2;
        }
        else if(valTwo[0]<=120){
            temp=1;
        }
        set2.setColor(color[temp]);
        BarDataSet set3 = new BarDataSet(barThree, "barTwo");
        temp =0;
        if(valThree[0]<=80){
            temp=2;
        }
        else if(valThree[0]<=120){
            temp=1;
        }
        set3.setColor(color[temp]);
        BarDataSet set4 = new BarDataSet(barFour, "barTwo");
       temp =0;
        if(valFour[0]<=80){
            temp=2;
        }
        else if(valFour[0]<=120){
            temp=1;
        }
        set4.setColor(color[temp]);
        BarDataSet set5 = new BarDataSet(barFive, "barTwo");
        temp =0;
        if(valFive[0]<=80){
            temp=2;
        }
        else if(valFive[0]<=120){
            temp=1;
        }
        set5.setColor(color[temp]);

        set1.setHighlightEnabled(false);

        set2.setHighlightEnabled(false);

        set3.setHighlightEnabled(false);

        set4.setHighlightEnabled(false);

        set5.setHighlightEnabled(false);


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
        chart.setHorizontalScrollBarEnabled(false);

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
                finish();
                Intent intent = new Intent(MainActivity.this, DidEatActivity.class);
                Log.d("sunjae", "succecc");
                startActivity(intent);
                Log.d("sunjae", "succecc");
            }
        });
        WillEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                Intent intent = new Intent(MainActivity.this, EatDetail.class);
                startActivity(intent);
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
