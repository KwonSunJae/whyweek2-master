package com.algomukja.DideatListview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.algomukja.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.algomukja.R.layout.*;

public class DideatAdapter extends BaseAdapter {
    private Context ct;
    private int count=0;
    private List<Food> dideats;
    private TextView TVTansu;
    private TextView TVProtein ;
    private TextView TVFat ;
    private  TextView TVNat;
    private TextView TVfName;

    ArrayList<Food> ALfood;

    public DideatAdapter(Context context, List<Food> foodList) {
        this.ct = context;
        this.dideats = foodList;
    }
    @Override
    public int getCount() {
        return dideats.size();
    }

    @Override
    public Object getItem(int i) {
        return dideats.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(ct,R.layout.dideatlist_custom,null);

        ImageView imgView = v.findViewById(R.id.imgViewFood);
        TVfName = v.findViewById(R.id.TVfName);
        TVNat = v.findViewById(R.id.TVNat);
        TVTansu = v.findViewById(R.id.TVTansu);
        TVProtein = v.findViewById(R.id.TVProtein);
        TVFat = v.findViewById(R.id.TVFat);
        Log.d("dddcount",Integer.toString(this.count++));
        Food f = dideats.get(i);
        if(f.getUrl()!=111){
            imgView.setImageResource(f.getUrl());
        }
        TVfName.setText(f.getfName());
        TVFat.setText("지방 : "+f.getFat());
        TVNat.setText("나트륨 : "+f.getNat());
        TVTansu.setText("탄수화물 : "+f.getTansu());
        TVProtein.setText("단백질 : "+f.getProtein());






        return v;
    }
}
