package com.registerlogindemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.registerlogindemo.R;

public class LoginActivity extends BaseActivity {

    private EditText emailET, passwordET;
    private Button loginBT;
    private TextView forgotPassTV, registerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setToolbar(getString(R.string.login));
        //--------initialize User Interface---------------//
        initUI();
    }

    private void initUI() {
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);

        loginBT = (Button) findViewById(R.id.loginBT);

        forgotPassTV = (TextView) findViewById(R.id.forgotPassTV);
        registerTV = (TextView) findViewById(R.id.registerTV);

        //-------------------click listener registered-------------------//
        registerTV.setOnClickListener(this);
        forgotPassTV.setOnClickListener(this);
        loginBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.loginBT:
                SetValidatin();

                break;
            case R.id.forgotPassTV:
                Intent intent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.registerTV:
                //-----------call next activity-----------//

                Intent intentR = new Intent(this, RegisterActivity.class);
                startActivity(intentR);
                break;
            default:
                break;
        }
    }

    private void SetValidatin() {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        if (email.isEmpty()) {
            showToast(getString(R.string.please_enter_email));
        } else if (password.isEmpty()) {
            showToast(getString(R.string.please_enter_password));
        } else {
            login(email, password);
        }
    }

    private void login(String email, String password) {
        if (!db.checkLoginUser(email, password)) {
            showToast(getString(R.string.user_not_exist));
        } else {
            store.saveString("email", email);
            Intent intentMain = new Intent(this, MainActivity.class);
            startActivity(intentMain);
        }
    }
}
