package com.abc.proquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.abc.proquest.Handler.DbHandler;
import com.abc.proquest.Model.userModel;

import static com.abc.proquest.R.id.userType;

public class MainActivity extends AppCompatActivity {

    userModel userModel;
    DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userModel = new userModel();
        dbHandler = new DbHandler(this);


        Button loginButton= (Button) findViewById(R.id.loginButton);

        final TextView loginResult = (TextView) findViewById(R.id.loginResult);

        final EditText userName_EditText = (EditText) findViewById(R.id.userName_EditText);
        final EditText password_EditText = (EditText) findViewById(R.id.password_EditText);
        final RadioGroup userType = (RadioGroup) findViewById(R.id.userType);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton userTypeRadioButton;
                int selectedId = userType.getCheckedRadioButtonId();
                userTypeRadioButton = (RadioButton) findViewById(selectedId);

                userModel.setUserName(userName_EditText.getText().toString());
                userModel.setPassword(password_EditText.getText().toString());
                userModel.setUserType(userTypeRadioButton.getText().toString());
                long getID = dbHandler.validateUser(userModel);

                if (getID> 0)
                {
                    loginResult.setText("Login Success");
                }
                else
                {
                    loginResult.setText("Username, Password or User Type Mismatch");

                }

            }
        });
    }

    public void registerClick(View v) {
        final Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
