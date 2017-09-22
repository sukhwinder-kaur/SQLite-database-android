package com.registerlogindemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.registerlogindemo.R;
import com.registerlogindemo.data.UserData;

public class ForgotPasswordActivity extends BaseActivity {
    EditText emailET, confirmPasswordET, passwordET;
    private Button resetBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        init();
    }

    private void init() {
        emailET = (EditText) findViewById(R.id.emailET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPasswordET);
        resetBT = (Button) findViewById(R.id.resetBT);
        resetBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.resetBT:
                setValidation();
                break;
            default:
                break;
        }
    }

    private void setValidation() {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String confirmPassword = confirmPasswordET.getText().toString().trim();
        if (email.isEmpty()) {
            showToast(getString(R.string.please_enter_email));
        } else if (password.isEmpty()) {
            showToast(getString(R.string.please_enter_password));
        } else if (confirmPassword.isEmpty()) {
            showToast(getString(R.string.please_enter_confirm_password));
        } else if (!confirmPassword.equals(password)) {
            showToast(getString(R.string.password_does_not_match));
        } else {
            forgotPass(email, password);
        }
    }

    private void forgotPass(String email, String password) {
        if (!db.checkUser(email)) {
            showToast("This email " + email + " not exist");
        } else {
            UserData userData = new UserData();
            userData.email = email;
            userData.password = password;
            int i = db.updateUserPassword(userData);
            if (i == 1) {
                showToast("Password reset successfull");
                finish();
            } else {
                showToast("Some error");
            }
        }
    }
}
