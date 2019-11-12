package com.algomukja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

import javax.security.auth.Subject;

public class EatDetail extends AppCompatActivity {


    private int currentApiVersion;
    private Button Next;
    private Button Before;
    private int date = 1;
    private int week = 0;
    private TextView TVTansu;
    private TextView TVProt;
    private TextView TVNat;
    private TextView TVFat;
    private TextView TVweek;
    private Button close;
    private ArrayList<ArrayList> MonthTansu;
    private ArrayList<ArrayList> MonthProtein ;
    private ArrayList<ArrayList> MonthFat  ;
    private ArrayList<ArrayList> MonthNat  ;
    private ArrayList<ILineDataSet> lineDataSets;
    private RadioGroup RGchart;

    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat_detail);
        currentApiVersion = android.os.Build.VERSION.SDK_INT;

        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        // This work only for android 4.4+
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT)
        {

            getWindow().getDecorView().setSystemUiVisibility(flags);

            // Code below is to handle presses of Volume up or Volume down.
            // Without this, after pressing volume buttons, the navigation bar will
            // show up and won't hide
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
                    {

                        @Override
                        public void onSystemUiVisibilityChange(int visibility)
                        {
                            if((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                            {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
        Next = findViewById(R.id.BtnAfter);
        Before = findViewById(R.id.BtnBefore);
        TVFat = findViewById(R.id.ChartFat);
        TVNat = findViewById(R.id.ChartNa);
        TVProt = findViewById(R.id.ChartPro);
        TVTansu = findViewById(R.id.ChartTansu);
        TVweek = findViewById(R.id.TVMonth);
        RGchart = findViewById(R.id.RGChart);
        close = findViewById(R.id.goHome);
        close.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        init();
    }

    private ArrayList<Entry> tansu;
    private ArrayList<Entry> prot;
    private ArrayList<Entry> fat;
    private ArrayList<Entry> nat;
    @SuppressLint("NewApi")
    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT && hasFocus)
        {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public void init(){

        final LineChart chart = (LineChart) findViewById(R.id.linchart);
        String[] label = {"", "","","","","",""};

        XAxis xAxis = chart.getXAxis();
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1); // only intervals of 1 day
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12);
        xAxis.setAxisLineColor(Color.WHITE);
        xAxis.setAxisMinimum(1f);
        xAxis.setAxisMaximum(7f);


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(12);
        leftAxis.setAxisLineColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setGranularity(0);
        leftAxis.setLabelCount(4, true);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);




        ArrayList<String> labels = new ArrayList<>();
         MonthTansu = new ArrayList<>();
         MonthProtein = new ArrayList<>();
          MonthFat = new ArrayList<>();
          MonthNat = new ArrayList<>();



         lineDataSets = new ArrayList<>();


        final float[][] d = {
                {330,   63,	59,	1280,	2200},
                {380,	74,	77,	1550,	2550},
                {420,	57,	81,	1410,	2320},
                {320,	61,	65,	1790,	2870},
                {390,	82,	71,	1630,	2650},
                {290,	67,	89,	1350,	2410},
                {340,	71,	81,	1510,	2780},
                {420,	74,	54,	1550,	2550},
                {450,	82,	67,	1790,	2870},
                {310,	67,	74,	1350,	2410},
                {360,	58,	55,	1280,	2200},
                {280,	71,	84,	1410,	2320},
                {330,	66,	72,	1630,	2650},
                {410,	69,	63,	1510,	2780},
                {270,	49,	81,	1790,	2870},
                {340,	67,	71,	1350,	2410},
                {350,	58,	77,	1410,	2320},
                {430,	79,	58,	1630,	2650},
                {280,	81,	67,	1550,	2550},
                {330,	64,	54,	1280,	2200},
                {370,	47,	78,	1510,	2780},
                {227, 110, 57, 2992,0},
                {277 ,45 ,34, 2191,0},
                {243 ,171 ,129, 6561,0},
                {349, 149 ,96, 6260,0},{
                    0,0,0,0,0
        },{
                0,0,0,0,0
        },{
                0,0,0,0,0
        }
        };
        tansu = new ArrayList<>();
        prot = new ArrayList<>();
        fat = new ArrayList<>();
        nat = new ArrayList<>();
        final int color[] = {ContextCompat.getColor(this, R.color.colorAccent),ContextCompat.getColor(this, R.color.chart_color),ContextCompat.getColor(this, R.color.colorPrimary),ContextCompat.getColor(this, R.color.colorPrimaryDark)};
        for(int i=0; i<28;i++){


            tansu.add(new Entry(date,(int)(d[i][0]/390*100)));
            prot.add(new Entry(date,(int)(d[i][1]/65*100)));
            fat.add(new Entry(date,(int)(d[i][2]/72*100)));
            nat.add(new Entry(date,(int)(d[i][3]/1500*100)));
            date++;
            Log.d("test", tansu.toString()+ prot.toString()+ fat.toString());
            if((i+1)%7==0){


                MonthTansu.add(List_Copy(tansu));

                Log.d("d",MonthTansu.get(week).toString());
                MonthFat.add(List_Copy(fat));

                MonthNat.add(List_Copy(nat));

                MonthProtein.add(List_Copy(prot));
                tansu .clear();
                Log.d("d",MonthTansu.get(week).toString());
                prot.clear();
                fat.clear();
                nat.clear();
                date=1;
            }
        }

        Log.d("d",MonthTansu.get(week).toString());


        LineDataSet lineDataSet1 = new LineDataSet(MonthTansu.get(week), "");
        lineDataSet1.setDrawCircles(true);
        lineDataSet1.setColor(color[0]);

        //아래 그림의 빨간색 그래프
        LineDataSet lineDataSet2 = new LineDataSet(MonthProtein.get(week),"");
        lineDataSet2.setDrawCircles(true);
        lineDataSet2.setColor(color[1]);
        LineDataSet lineDataSet3 = new LineDataSet(MonthFat.get(week), "");
        lineDataSet3.setDrawCircles(true);
        lineDataSet3.setColor(color[2]);
        LineDataSet lineDataSet4 = new LineDataSet(MonthNat.get(week), "");
        lineDataSet4.setDrawCircles(true);
        lineDataSet4.setColor(color[3]);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);
        lineDataSets.add(lineDataSet3);
        lineDataSets.add(lineDataSet4);
        /*
        tansu.add(new Entry(date,(int)(d[i][0]/390*100)));
            prot.add(new Entry(date,(int)(d[i][1]/65*100)));
            fat.add(new Entry(date,(int)(d[i][2]/72*100)));
            nat.add(new Entry(date,(int)(d[i][3]/1500*100)));
         */

        TVTansu.setText("탄수화물 : "+(int)(d[week*7+date-1][0]/390*100)+"%");
        TVProt.setText("단백질 : "+(int)(d[week*7+date-1][1]/65*100)+"%");
        TVNat.setText("나트륨 : "+(int)(d[week*7+date-1][3]/1500*100)+"%");
        TVFat.setText("지방 : "+(int)(d[week*7+date-1][2]/72*100)+"%");


        chart.setData(new LineData(lineDataSets));
        Next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(week!=3){

                    week++;
                    TVweek.setText((week+1)+"주차");


                    TVTansu.setText("탄수화물 : "+(int)(d[week*7+date-1][0]/390*100)+"%");
                    TVProt.setText("단백질 : "+(int)(d[week*7+date-1][1]/65*100)+"%");
                    TVNat.setText("나트륨 : "+(int)(d[week*7+date-1][3]/1500*100)+"%");
                    TVFat.setText("지방 : "+(int)(d[week*7+date-1][2]/72*100)+"%");

                    LineDataSet lineDataSet1 = new LineDataSet(MonthTansu.get(week), "");
                    lineDataSet1.setDrawCircles(true);
                    lineDataSet1.setColor(color[0]);

                    //아래 그림의 빨간색 그래프
                    LineDataSet lineDataSet2 = new LineDataSet(MonthProtein.get(week),"");
                    lineDataSet2.setDrawCircles(true);
                    lineDataSet2.setColor(color[1]);
                    LineDataSet lineDataSet3 = new LineDataSet(MonthFat.get(week), "");
                    lineDataSet3.setDrawCircles(true);
                    lineDataSet3.setColor(color[2]);
                    LineDataSet lineDataSet4 = new LineDataSet(MonthNat.get(week), "");
                    lineDataSet4.setDrawCircles(true);
                    lineDataSet4.setColor(color[3]);
                    lineDataSets.clear();
                    lineDataSets.add(lineDataSet1);
                    lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);
                    lineDataSets.add(lineDataSet4);
                    chart.setData(new LineData(lineDataSets));
                    chart.notifyDataSetChanged();
                    chart.callOnClick();
                    chart.invalidate();
                }
            }
        });
        Before.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(week!=0){
                    lineDataSets.clear();
                    week--;
                    TVweek.setText((week+1)+"주차");
                    TVTansu.setText("탄수화물 : "+(int)(d[week*7+date-1][0]/390*100)+"%");
                    TVProt.setText("단백질 : "+(int)(d[week*7+date-1][1]/65*100)+"%");
                    TVNat.setText("나트륨 : "+(int)(d[week*7+date-1][3]/1500*100)+"%");
                    TVFat.setText("지방 : "+(int)(d[week*7+date-1][2]/72*100)+"%");

                    LineDataSet lineDataSet1 = new LineDataSet(MonthTansu.get(week), "");
                    lineDataSet1.setDrawCircles(true);
                    lineDataSet1.setColor(color[0]);

                    //아래 그림의 빨간색 그래프
                    LineDataSet lineDataSet2 = new LineDataSet(MonthProtein.get(week),"");
                    lineDataSet2.setDrawCircles(true);
                    lineDataSet2.setColor(color[1]);
                    LineDataSet lineDataSet3 = new LineDataSet(MonthFat.get(week), "");
                    lineDataSet3.setDrawCircles(true);
                    lineDataSet3.setColor(color[2]);
                    LineDataSet lineDataSet4 = new LineDataSet(MonthNat.get(week), "");
                    lineDataSet4.setDrawCircles(true);
                    lineDataSet4.setColor(color[3]);

                    lineDataSets.add(lineDataSet1);
                    lineDataSets.add(lineDataSet2);
                    lineDataSets.add(lineDataSet3);
                    lineDataSets.add(lineDataSet4);
                    chart.setData(new LineData(lineDataSets));
                    chart.notifyDataSetChanged();
                    chart.callOnClick();
                    chart.invalidate();
                }
            }
        });
        RGchart.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.RSUN){
                    date =1;
                }
                else if( i==R.id.RMon)
                    date=2;
                else if( i==R.id.RTue)
                    date=3;
                else if( i==R.id.RWed)
                    date=4;
                else if( i==R.id.RThu)
                    date=5;
                else if( i==R.id.RFri)
                    date=6;
                else if( i==R.id.RSat)
                    date=7;
                TVTansu.setText("탄수화물 : "+(int)(d[week*7+date-1][0]/390*100)+"%");
                TVProt.setText("단백질 : "+(int)(d[week*7+date-1][1]/65*100)+"%");
                TVNat.setText("나트륨 : "+(int)(d[week*7+date-1][3]/1500*100)+"%");
                TVFat.setText("지방 : "+(int)(d[week*7+date-1][2]/72*100)+"%");

            }
        });




    }
    public ArrayList<Entry> List_Copy(ArrayList<Entry> list){
        ArrayList<Entry> temp = new ArrayList<>();
        for(Entry list_item: list){
            temp.add(new Entry(list_item.getX(),list_item.getY()));
        }
        return temp;
    }




}
