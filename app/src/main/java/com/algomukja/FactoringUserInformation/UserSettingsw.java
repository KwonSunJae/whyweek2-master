package com.algomukja.FactoringUserInformation;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSettingsw {
    private final SharedPreferences.Editor medit;

    private String name;
    private String userID;
    private String userPW;
    private int age;
    private String userPHn;
    private double weight;
    private double height;
    private int jul;
    private double motiveW;
    private int sex;
    private int act;


    public String getUserPHn() {
        return mUserSettingsw.getString("USERPHN","01012345678");
    }

    public void setUserPHn(String userPHn) {
        medit.putString("USERPHN",userPHn);
        medit.commit();
    }

    public int getAct() {
        return mUserSettingsw.getInt("USERACT",1);
    }

    public int getSex() {
        return mUserSettingsw.getInt("USERSEX",1);
    }

    public void setSex(int sex) {
        medit.putInt("USERSEX",sex);
        medit.commit();
    }

    public void setAct(int act) {
        medit.putInt("USERACT",act);
        medit.commit();
    }

    public String getUserID() {
        return mUserSettingsw.getString("USERID","um");
    }

    public void setUserID(String userID) {
        this.userID = userID;

        medit.putString("USERID",userID);
        medit.commit();
    }

    public String getUserPW() {
        return mUserSettingsw.getString("USERPW","um");
    }

    public void setUserPW(String userPW) {
        this.userPW = userPW;
        medit.putString("USERPW",userPW);
        medit.commit();
    }

    private static UserSettingsw mInstance;
    private SharedPreferences mUserSettingsw;
    public static UserSettingsw getmInstance(Context context){
        if(mInstance == null){
            mInstance = new UserSettingsw(context);
        }
        return mInstance;
    }
    public UserSettingsw(Context context){
        mUserSettingsw =  context.getSharedPreferences(
                "UserSettings",Context.MODE_PRIVATE
        );
        medit = mUserSettingsw.edit();
    }

    public String getName() {
        return mUserSettingsw.getString("NAME","default");
    }

    public void setName(String name) {
        this.name = name;
        medit.putString("NAME",name);
        medit.commit();
    }

    public int getAge() {
        return mUserSettingsw.getInt("AGE",0);
    }

    public void setAge(int age) {
        this.age = age;
        medit.putInt("AGE",this.age);medit.commit();
    }

    public double getWeight() {
        return mUserSettingsw.getFloat("WEIGHT",(float)0.0);
    }

    public void setWeight(double weight) {
        this.weight = weight;
        medit.putFloat("WEIGHT",(float)weight);medit.commit();
    }

    public double getHeight() {
        return mUserSettingsw.getFloat("HEIGHT",(float)0.0);
    }

    public void setHeight(double height) {
        this.height = height;
        medit.putFloat("HEIGHT",(float)height);medit.commit();
    }

    public int getJul() {
        return mUserSettingsw.getInt("JUL",0);
    }

    public void setJul(int jul) {
        this.jul = jul;
        medit.putInt("JUL",jul);medit.commit();
    }

    public double getMotiveW() {
        return mUserSettingsw.getFloat("MOTIVEW",(float)0.0);
    }

    public void setMotiveW(double motiveW) {
        this.motiveW = motiveW;
        medit.putFloat("MOTIVEW",(float)motiveW);medit.commit();
    }
}
