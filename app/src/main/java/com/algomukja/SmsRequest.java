package com.algomukja;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SmsRequest extends StringRequest {
    final static private String URL= "http://http://tlgur5869.cafe24.com/YouEat.php";
    private Map<String, String> parameters;
    public SmsRequest(String d, Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        parameters=new HashMap<>();
        parameters.put("tans",d);


    }

    @Override
    public Map<String, String>getParams(){


        return parameters;
    }

}
