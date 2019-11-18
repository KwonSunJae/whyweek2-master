package com.algomukja.FactoringUserInformation;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.algomukja.DideatListview.Food;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
    private int act=0;

    private int tan;
    private int pro;
    private int nat;
    private int fat;

    public int getTan() {
        return mUserSettingsw.getInt("TANS",0);
    }

    public void setTan(int tan) {
        this.tan = tan;
        medit.putInt("TANS",tan);
        medit.commit();
    }

    public int getPro() {
        return mUserSettingsw.getInt("PROT",0);
    }

    public void setPro(int pro) {
        this.pro = pro;
        medit.putInt("PROT",pro);
        medit.commit();
    }

    public int getNat() {
        return mUserSettingsw.getInt("NAT",0);
    }

    public void setNat(int nat) {
        this.nat = nat;
        medit.putInt("NAT",nat);
        medit.commit();
    }

    public int getFat() {
        return mUserSettingsw.getInt("FAT",0);
    }

    public void setFat(int fat) {
        this.fat = fat;
        medit.putInt("FAT",fat);
        medit.commit();
    }

    private Food person;
    private int fnumber=0;

    public int getfnumber() {
        return mUserSettingsw.getInt("fNUM",0);
    }

    public void setfnumber(int faet) {
        this.fnumber = faet;
        medit.putInt("fNUM",faet);
        medit.commit();
    }

    public void addFood( Food f){
        String key = getfnumber()+"food";
        setfnumber(getfnumber()+1);
        String s = "";
        s+= f.getfName()+"'";
        s+= f.getUrl()+"'";
        setTan(getTan()+f.getTansu());
        setPro(getPro()+f.getProtein());
        setFat(getFat()+f.getFat());
        setNat(getNat()+f.getNat());
        setJul(getJul()+f.getJul());

        s+= f.getTansu()+"'";
        s+= f.getProtein()+"'";
        s+= f.getFat()+"'";
        s+= f.getNat()+"'";
        s+=f.getJul();
        medit.putString(key,s);
        medit.commit();


    }


    public Food getPerson(){

            String f = mUserSettingsw.getString("PERSON","!");
            if(!f.equals("!")){
                Food temp = new Food(1,1,1,1,"",111);
                String[] t = f.split("'");
                temp.setFat((int) Float.parseFloat(t[4]));
                temp.setNat(Integer.parseInt(t[5]));
                temp.setfName(t[0]);
                temp.setUrl(Integer.parseInt(t[1]));
                temp.setProtein(Integer.parseInt(t[3]));
                temp.setTansu(Integer.parseInt(t[2]));
                temp.setJul(Integer.parseInt(t[6]));
                return temp;
            }
            return null;

    }
    public void DeleteFood(int i){
        ArrayList<Food> temp = new ArrayList<>();
        temp = getFood();
        setJul(getJul()-temp.get(i).getJul());
        temp.remove(i);
        setfnumber(0);
        for(int j=0; j<temp.size();j++){
            addFood(temp.get(j));
        }


    }

    public  int getFnumber(){
        return getfnumber();
    }


    public ArrayList<Food> getFood(){
        ArrayList<Food> result= new ArrayList<>();
        if(this.getFnumber()!=0){
            for(int i=0; i<getFnumber();i++){
                String f = mUserSettingsw.getString((i)+"food","!");
                Log.d("ddddtlqkf",f);
                if(!f.equals("!")){
                    Food temp = new Food(1,1,1,1,"",111);
                    String[] t = f.split("'");
                    temp.setFat((int) Float.parseFloat(t[4]));
                    temp.setNat(Integer.parseInt(t[5]));
                    temp.setfName(t[0]);
                    temp.setUrl(Integer.parseInt(t[1]));
                    temp.setProtein(Integer.parseInt(t[3]));
                    temp.setTansu(Integer.parseInt(t[2]));
                    temp.setJul(Integer.parseInt(t[6]));
                    result.add(List_Copy(temp));
                    Log.d("ddddtlqkf",temp.toString());
                }

            }
            return result;
        }
        return null;
    }

    private Food List_Copy(Food temp) {
        Food te= new Food(temp.getTansu(),temp.getProtein(),temp.getFat(),temp.getNat(),temp.getfName(),temp.getUrl());
        te.setJul(temp.getJul());
        return te;
    }


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
        Log.d("JULL CHECK",getJul()+"");
    }

    public double getMotiveW() {
        return mUserSettingsw.getFloat("MOTIVEW",(float)0.0);
    }

    public void setMotiveW(double motiveW) {
        this.motiveW = motiveW;
        medit.putFloat("MOTIVEW",(float)motiveW);medit.commit();
    }
}
