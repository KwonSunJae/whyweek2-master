package com.algomukja.Eat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.algomukja.DideatListview.Dideat;
import com.algomukja.DideatListview.DideatAdapter;
import com.algomukja.DideatListview.Food;
import com.algomukja.R;

import java.util.ArrayList;

public class DidEatActivity extends AppCompatActivity {
    private ListView listView;
    private DideatAdapter da;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_eat);
        Log.d("sunjae","succecc");
        init();
    }
    private void init(){
        listView= findViewById(R.id.LVDidEat);
        ArrayList<Dideat> d = new ArrayList<Dideat>();
        for(int i=0; i<10; i++){
            d.add(new Dideat("2019/10/23","",new ArrayList<Food>()));
        }

       //da  = new DideatAdapter(getApplicationContext(),d);
        //listView.setAdapter(da);
    }
}
