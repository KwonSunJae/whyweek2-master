package com.algomukja.WilleatListView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.algomukja.DideatListview.Dideat;
import com.algomukja.DideatListview.Food;
import com.algomukja.R;
import java.util.ArrayList;
import java.util.List;

public class WilleatAdapter extends BaseAdapter {
    private List<Food>Foods;
    private Context ct;
    private int count=0;
    private TextView TVTansu;
    private TextView TVProtein ;
    private TextView TVFat ;
    private TextView TVNat;
    private TextView fName;
    private ArrayList<Food> ALfood;

    public WilleatAdapter(Context context, List<Food> foodList) {
        this.ct = context;
        this.Foods = foodList;
    }
    @Override
    public int getCount() {
        return Foods.size();
    }

    @Override
    public Object getItem(int i) {
        return Foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(ct, R.layout.willeat_custom,null);
        TVFat = v.findViewById(R.id.wTVTFat);
        TVNat = v.findViewById(R.id.wTVNat);
        TVProtein = v.findViewById(R.id.wTVProtein);
        TVTansu = v.findViewById(R.id.wTVTansu);
        fName = v.findViewById(R.id.TVfName);
        TVFat.setText("지방:"+Integer.toString(Foods.get(i).getFat()));
        TVTansu.setText("탄수화물:"+Integer.toString(Foods.get(i).getTansu()));
        TVProtein.setText("단백질:"+Integer.toString(Foods.get(i).getProtein()));
        fName.setText(Foods.get(i).getfName());
        TVNat.setText("나트륨:"+Integer.toString(Foods.get(i).getNat()));




        return v;
    }
}
