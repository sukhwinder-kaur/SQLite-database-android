package com.registerlogindemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.registerlogindemo.R;
import com.registerlogindemo.data.UserData;

public class RegisterActivity extends BaseActivity {

    private EditText usernameET, emailET, passwordET, confirmPasswordET;
    private Button registerBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //------------------------------set action bar and action bar title-------------//
        setToolbar(getString(R.string.register));
        initUI();
    }

    private void initUI() {
        usernameET = (EditText) findViewById(R.id.usernameET);
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPasswordET);

        registerBT = (Button) findViewById(R.id.registerBT);

        registerBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.registerBT:
                setValiadtion();
                break;
            default:
                break;
        }
    }

    private void setValiadtion() {
        //----------------get the value from edittext-----------------//

        String userName = usernameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String confirmPassword = confirmPasswordET.getText().toString().trim();

        // --------------------- check validations--------------------///
        if (userName.isEmpty()) {
            showToast(getString(R.string.please_enter_user_name));
        } else if (email.isEmpty()) {
            showToast(getString(R.string.please_enter_email));
        } else if (!email.contains("@") || !email.contains(".")) {
            showToast(getString(R.string.please_enter_valid_email));
        } else if (password.isEmpty()) {
            showToast(getString(R.string.please_enter_password));
        } else if (confirmPassword.isEmpty()) {
            showToast(getString(R.string.please_enter_confirm_password));
        } else if (!confirmPassword.equals(password)) {
            showToast(getString(R.string.password_does_not_match));
        } else {
            saveTodataBase(userName, email, password);
            startProgressDialog();
        }
    }

    private void saveTodataBase(String userName, String email, String password) {
        UserData userData = new UserData();
        userData.name = userName;
        userData.email = email;
        userData.password = password;
        if (!db.checkUser(email)) {
            db.addUser(userData);
            showToast(getString(R.string.user_registered_successfully));
            stopProgressDialog();
            finish();
        } else {
            stopProgressDialog();
            showToast(getString(R.string.user_already_exist));

        }

    }
}
