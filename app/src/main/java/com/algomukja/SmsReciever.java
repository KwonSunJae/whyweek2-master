package com.algomukja;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.algomukja.DideatListview.Food;
import com.algomukja.Eat.DataRequest;
import com.algomukja.Eat.DidEatActivity;
import com.algomukja.Eat.WillEatAcitvity;
import com.algomukja.FactoringUserInformation.UserSettingsw;
import com.algomukja.WilleatListView.WilleatAdapter;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReciever extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsReceiver";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private UserSettingsw us;


    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals(SMS_RECEIVED)) { Log.d(TAG, "onReceiver() 호출"); // Bundle을 이용해서 메세지 내용을 가져
            Bundle bundle = intent.getExtras(); SmsMessage[] messages = parseSmsMessage(bundle); // 메세지가 있을 경우 내용을 로그로 출력해 봄
            Log.d(TAG, messages[0].getMessageBody());
        if (messages.length > 0 && messages[0].getMessageBody().contains("알고먹자")) { // 메세지의 내용을 가져옴
            Log.d(TAG, "onReceiver() 호출");
             String sender = messages[0].getOriginatingAddress();
             String contents = messages[0].getMessageBody().toString();
             Date receivedDate = new Date(messages[0].getTimestampMillis()); // 로그를 찍어보는 과정이므로 생략해도 됨
             contents = contents.replaceAll("(\r\n|\r|\n|\n\r)", " ");
            String [] t = contents.split(" ");
            String d=t[4];
            us  = new UserSettingsw(context);

            Log.d(TAG, "receivedDate : " + d); // 액티비티로 메세지의 내용을 전달해줌
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try{
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray success = jsonResponse.getJSONArray("response");
                        int count=0;
                        while (count<success.length()){
                            JSONObject object = success.getJSONObject(count);
                            Food te = new Food(object.getInt("Tansu"),object.getInt("Prot"),object.getInt("Fat"),object.getInt("Na"),object.getString("Name"),object.getInt("resourceID"));
                            te.setJul(object.getInt("kcal"));
                            Log.d("dxdx",object.toString());
                            us.addFood(te);

                            count++;

                        }

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            };
            SmsRequest loginRequest = new SmsRequest(d,responseListener);
            RequestQueue queue = Volley.newRequestQueue(context);
            queue.add(loginRequest);

            sendToActivity(context, sender, contents, receivedDate); } }
        

    }
    private void sendToActivity(Context context, String sender, String contents, Date receivedDate){ 
        Intent intent = new Intent(context, MainActivity.class);
         MainActivity ma = (MainActivity)MainActivity.activity;
         ma.finish();
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP); 
         intent.putExtra("sender", sender); intent.putExtra("contents", contents);
         intent.putExtra("flag",0);
         intent.putExtra("receivedDate", format.format(receivedDate));
         context.startActivity(intent);
         } 


         private SmsMessage[] parseSmsMessage(Bundle bundle){ 
         Object[] objs = (Object[]) bundle.get("pdus");
         SmsMessage[] messages = new SmsMessage[objs.length]; 
         for(int i=0; i<objs.length; i++) {
             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
         { String format = bundle.getString("format"); 
         messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format); 
         } 
         else { messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]); } } return messages; }

        
}
