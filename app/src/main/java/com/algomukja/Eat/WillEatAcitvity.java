package com.algomukja.Eat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.algomukja.DideatListview.Food;
import com.algomukja.R;
import com.algomukja.WilleatListView.WilleatAdapter;

import java.util.ArrayList;
import java.util.List;

public class WillEatAcitvity extends AppCompatActivity {
    private ListView listVIew;
    private WilleatAdapter wa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_will_eat_acitvity);
        init();
    }

    public void init(){
        listVIew = findViewById(R.id.LVwillEat);
        List<Food> lf = new ArrayList<Food>();


        for(int i=0; i<10; i++){
            lf.add(new Food(200,200,300,400,"된장찌개",null));
        }
         wa = new WilleatAdapter(getApplicationContext(),lf);
        listVIew.setAdapter(wa);

    }

}
