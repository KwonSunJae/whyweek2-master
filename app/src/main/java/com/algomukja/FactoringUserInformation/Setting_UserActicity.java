package com.algomukja.FactoringUserInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.algomukja.R;

public class Setting_UserActicity extends AppCompatActivity {
    private RadioGroup userSex;
    private RadioGroup userAct;

    private TextView userAge;
    private TextView userWeight;
    private TextView userMotiveW;
    private TextView userHeight;
    private int currentApiVersion;

    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

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
        setContentView(R.layout.activity_setting__user_acticity);
        init();
    }


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


    private void init(){
        final int[] Radio = {1,1};
        userAge = findViewById(R.id.TVage);
        userWeight = findViewById(R.id.TVWeight);
        userMotiveW = findViewById(R.id.TVMotiveW);
        userSex = findViewById(R.id.RGSex);
        userAct = findViewById(R.id.RGAct);

        userSex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.RbtnMale){
                    Radio[0] =1;
                }
                else if(i == R.id.Rbtnfemale){
                    Radio[0] = 2;
                }
            }
        });
        userAct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.BtnLess){
                    Radio[1]=1;
                }
                else if(i==R.id.BtnMid){
                    Radio[1]=2;
                }
                else{
                    Radio[1]=3;
                }
            }
        });
        userHeight = findViewById(R.id.TVHeight);
        Button BtnFinish = findViewById(R.id.BtnFinish);
        BtnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int userage = Integer.parseInt(userAge.getText().toString());
                double w = Double.parseDouble(userWeight.getText().toString());
                double mw = Double.parseDouble(userMotiveW.getText().toString());
                double h = Double.parseDouble(userHeight.getText().toString());

                UserSettingsw us = UserSettingsw.getmInstance(Setting_UserActicity.this);
                us.setAge(userage);
                us.setHeight(h);
                us.setAct(Radio[1]);
                us.setSex(Radio[0]);
                us.setMotiveW(mw);
                us.setWeight(w);

                finish();
            }
        });



    }

}
