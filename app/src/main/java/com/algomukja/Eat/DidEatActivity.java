package com.algomukja.Eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.algomukja.DideatListview.Adddideat;
import com.algomukja.DideatListview.Dideat;
import com.algomukja.DideatListview.DideatAdapter;
import com.algomukja.DideatListview.Food;
import com.algomukja.FactoringUserInformation.UserSettingsw;
import com.algomukja.MainActivity;
import com.algomukja.R;

import java.util.ArrayList;

public class DidEatActivity extends AppCompatActivity {
    private ListView listView;
    private DideatAdapter da;
    private Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_did_eat);
        Log.d("sunjae","succecc");
        init();
        add = findViewById(R.id.BTNadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DidEatActivity.this, Adddideat.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        listView= findViewById(R.id.LVDidEat);

        UserSettingsw us = new UserSettingsw(this);
        ArrayList<Food> dd = us.getFood();
        da  = new DideatAdapter(getApplicationContext(),dd);

        if(us.getFood()!=null){
            for(Food d : us.getFood()){
                Log.d("dsfsdtlqkf",d.getfName());
            }
            setListViewHeight(da,listView);
            listView.setAdapter(da);
        }

    }
    public void setListViewHeight(DideatAdapter adpater, ListView listView) {
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int size = 0; size < listView.getCount(); size++) {
            View listItem = adpater.getView(size, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listView.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onDestroy();

    }





}
