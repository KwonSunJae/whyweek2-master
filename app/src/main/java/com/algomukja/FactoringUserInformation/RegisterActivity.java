package com.algomukja.FactoringUserInformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.algomukja.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText userID;
    private EditText userPW;
    private EditText PhNum;
    private EditText userName;
    private Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_register);
        init();
    }
    public void init(){
        userID = findViewById(R.id.ETuserID);
        userPW = findViewById(R.id.ETuserPW);
        PhNum = findViewById(R.id.ETfphnum);
        userName = findViewById(R.id.ETName);
        Submit = findViewById(R.id.Btnsubmit);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserSettingsw us = UserSettingsw.getmInstance(RegisterActivity.this);
                us.setName(userName.getText().toString());
                us.setUserID(userID.getText().toString());
                us.setUserPW(userPW.getText().toString());
                us.setUserPHn(PhNum.getText().toString());
                finish();
            }
        });

    }

}
