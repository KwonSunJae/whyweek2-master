package com.algomukja.Eat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.algomukja.DideatListview.Food;
import com.algomukja.FactoringUserInformation.UserSettingsw;
import com.algomukja.MainActivity;
import com.algomukja.R;
import com.algomukja.WilleatListView.WilleatAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private List<Food> lf;
    public void init(){
        listVIew = findViewById(R.id.LVwillEat);
        lf = new ArrayList<Food>();
        final int[] arrImg ={R.drawable.w1,R.drawable.w2,R.drawable.w3,R.drawable.w4,R.drawable.w5,R.drawable.w6,R.drawable.w7,R.drawable.w8,R.drawable.w9,R.drawable.w10};



        UserSettingsw us = new UserSettingsw(this);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Log.d("dxdx",response);
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray success = jsonResponse.getJSONArray("response");
                    int count=0;
                    while (count<success.length()&&count!=10){
                        JSONObject object = success.getJSONObject(count);
                        if(object.getString("resourceID").length()>0)
                            lf.add(new Food(object.getInt("Tansu"),object.getInt("Prot"),object.getInt("Fat"),object.getInt("Na"),object.getString("Name"),arrImg[object.getInt("resourceID")-1]));
                        else
                            lf.add(new Food(object.getInt("Tansu"),object.getInt("Prot"),object.getInt("Fat"),object.getInt("Na"),object.getString("Name"),111));

                        Log.d("dxdx",object.toString());
                        count++;

                    }
                    wa = new WilleatAdapter(getApplicationContext(),lf);
                    listVIew.setAdapter(wa);

                    listVIew.invalidate();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        int tan = 390-us.getTan();
        int pro = 65 - us.getPro();
        int fat = 72 - us.getFat();
        int Nat = 1500 - us.getNat();
        Log.d("d",Integer.toString(tan)+Integer.toString(pro)+Integer.toString(fat)+Integer.toString(Nat));
        DataRequest loginRequest = new DataRequest(tan<0?0:tan,pro<0?0:pro,fat<0?0:fat,Nat<0?0:Nat,responseListener);
        RequestQueue queue = Volley.newRequestQueue(WillEatAcitvity.this);
        queue.add(loginRequest);

    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        super.onDestroy();

    }

}
