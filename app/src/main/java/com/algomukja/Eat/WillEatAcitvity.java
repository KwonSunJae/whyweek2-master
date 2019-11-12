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
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray success = jsonResponse.getJSONArray("response");
                    int count=0;
                    while (count<success.length()){
                        JSONObject object = success.getJSONObject(count);
                        if(!object.getString("resourceID").equals(""))
                            lf.add(new Food(object.getInt("Tansu"),object.getInt("Prot"),object.getInt("Fat"),object.getInt("Na"),object.getString("Name"),arrImg[object.getInt("resourceID")-1]));
                        else
                            lf.add(new Food(object.getInt("Tansu"),object.getInt("Prot"),object.getInt("Fat"),object.getInt("Na"),object.getString("Name"),111));

                        Log.d("dxdx",object.toString());
                        count++;

                    }
                    wa = new WilleatAdapter(getApplicationContext(),lf);
                    listVIew.setAdapter(wa);
                    listVIew.notifyAll();
                    listVIew.invalidate();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        DataRequest loginRequest = new DataRequest(us.getTan(),us.getPro(),us.getFat(),us.getNat(),responseListener);
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
