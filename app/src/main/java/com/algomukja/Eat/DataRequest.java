package com.algomukja.Eat;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DataRequest extends StringRequest {
    final static private String URL= "http://http://tlgur5869.cafe24.com/BigData.php";
    private Map<String, String> parameters;
    public DataRequest(int tansu, int prot, int fat, int nat, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("tans", Integer.toString(tansu));
        parameters.put("prot", Integer.toString(prot));
        parameters.put("fat", Integer.toString(fat));
        parameters.put("nat", Integer.toString(nat));

    }

    @Override
    public Map<String, String>getParams(){


        return parameters;
    }


}
