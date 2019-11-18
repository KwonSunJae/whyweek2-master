package com.algomukja.Eat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
    private Boolean Flag = false;
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
                Flag=true;
                finish();
                Intent intent = new Intent(DidEatActivity.this, Adddideat.class);
                startActivity(intent);
            }
        });
    }
    private void init(){
        listView= findViewById(R.id.LVDidEat);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(getApplicationContext(),"클릭인식",Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(DidEatActivity.this);
                builder.setTitle("삭제 하시겠습니까?");
                builder.setMessage("위의 항목을 삭제하시겠습니까?");
                builder.setPositiveButton("예",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                UserSettingsw us = new UserSettingsw(DidEatActivity.this);
                                us.DeleteFood(i);
                                Log.d("dddddd", i+"");
                                Toast.makeText(getApplicationContext(), "삭제 하였습니다.", Toast.LENGTH_SHORT).show();
                                Flag=true;
                                finish();
                                Intent intent = new Intent(DidEatActivity.this, DidEatActivity.class);
                                startActivity(intent);


                            }
                        });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                            }
                        });
                builder.show();




            }
        });

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
        if(!Flag){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("flag",1);
            startActivity(intent);

        }

        super.onDestroy();
    }





}
