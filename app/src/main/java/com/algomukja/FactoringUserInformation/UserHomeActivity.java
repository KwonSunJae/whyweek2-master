package com.algomukja.FactoringUserInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.algomukja.R;

public class UserHomeActivity extends AppCompatActivity {
    private EditText userID;
    private EditText userPW;
    private Button btnLogin;
    private Button btnRegist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        init();
    }
    private void init(){
        userID= findViewById(R.id.ETuserID);
        userPW = findViewById(R.id.ETuserPW);
        btnLogin = findViewById(R.id.BtnLogin);
        btnRegist = findViewById(R.id.BtnRegist);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSettingsw us = new UserSettingsw(UserHomeActivity.this);

                if(userID.getText().toString().equals(us.getUserID())==true&& userPW.getText().toString().equals(us.getUserPW())==true){
                    finish();
                }

            }
        });
        btnRegist.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserHomeActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });

    }

}
