package com.algomukja.Eat;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DataRequest extends StringRequest {
    final static private String URL= "http://tlgur5869.cafe24.com/test32.php";
    private Map<String, String> parameters;
    public DataRequest(int tansu, int prot, int fat, int nat, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        Log.d("wwwww","getin1");
        parameters.put("tans", Integer.toString(tansu));
        parameters.put("prot", Integer.toString(prot));
        parameters.put("fat", Integer.toString(fat));
        parameters.put("nat", Integer.toString(nat));

    }

    @Override
    public Map<String, String>getParams(){
        Log.d("wwwww","getin2");

        return parameters;
    }


}
