package com.abc.proquest;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.abc.proquest.Handler.DbHandler;
import com.abc.proquest.Model.userModel;

public class RegisterActivity extends AppCompatActivity {

    userModel userModel;
    DbHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userModel = new userModel();
        dbHandler = new DbHandler(this);

        Button registerButton = (Button) findViewById(R.id.registerButton);
        final TextView registerResult = (TextView) findViewById(R.id.registerResult);

        final RadioGroup userType = (RadioGroup) findViewById(R.id.userType);

//        final RadioButton userTypeAdmin = (RadioButton) findViewById(R.id.userTypeAdmin);
//        final RadioButton userTypeUser = (RadioButton) findViewById(R.id.userTypeUser);

        final EditText userName_EditText = (EditText) findViewById(R.id.userName_EditText);
        final EditText password_EditText = (EditText) findViewById(R.id.password_EditText);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton userTypeRadioButton;
                int selectedId = userType.getCheckedRadioButtonId();
                userTypeRadioButton = (RadioButton) findViewById(selectedId);
//                Toast.makeText(RegisterActivity.this, userTypeRadioButton.getText(), Toast.LENGTH_SHORT).show();

                userModel.setUserName(userName_EditText.getText().toString());
                userModel.setPassword(password_EditText.getText().toString());
                userModel.setUserType(userTypeRadioButton.getText().toString());
                long getID = dbHandler.registerUser(userModel);

                if (getID> 0)
                {
                    registerResult.setText("Register Success");
                }
                else
                {
                    registerResult.setText("Username Already Exists");
                }

            }
        });

    }

    public void loginClick(View v)
    {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void newLoginFormClick(View v)
    {
        final Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
