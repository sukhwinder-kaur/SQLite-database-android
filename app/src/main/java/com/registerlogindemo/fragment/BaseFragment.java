package com.registerlogindemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.registerlogindemo.activity.BaseActivity;




public class BaseFragment extends Fragment implements View.OnClickListener {
    Context mContext;
    BaseActivity baseActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        baseActivity = (BaseActivity) getActivity();
    }

    @Override
    public void onClick(View v) {

    }
    public void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
