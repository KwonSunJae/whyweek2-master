package com.algomukja.DideatListview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.algomukja.Eat.DidEatActivity;
import com.algomukja.FactoringUserInformation.UserSettingsw;
import com.algomukja.MainActivity;
import com.algomukja.R;

public class Adddideat extends AppCompatActivity {
    private EditText fname;
    private EditText fTansu;
    private EditText fProt;
    private EditText ffat;
    private EditText fNat;
    private Button sub;
    private Button close;
    private EditText fKcal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddideat);
        init();
    }
    public  void init(){
        fKcal = findViewById(R.id.ETkCal);

        fname = findViewById(R.id.ETfName);
        fTansu = findViewById(R.id.ETTansu);
        fProt = findViewById(R.id.ETPro);
        ffat = findViewById(R.id.ETFat);
        fNat = findViewById(R.id.ETNa);
        sub = findViewById(R.id.Btnsubmit);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food temp = new Food(Integer.parseInt(fTansu.getText().toString()),Integer.parseInt(fProt.getText().toString()),Integer.parseInt(ffat.getText().toString()),Integer.parseInt(fNat.getText().toString()),fname.getText().toString(),111);
                temp.setJul(Integer.parseInt(fKcal.getText().toString()));
                if(temp.getfName().equals("알리오올리오")){
                    temp.setUrl(2131165275);
                }
                UserSettingsw us = new UserSettingsw(Adddideat.this);
                us.addFood(temp);

                finish();
            }
        });
        close = findViewById(R.id.Btnclose);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, DidEatActivity.class);
        startActivity(intent);
        super.onDestroy();

    }

}
