package com.registerlogindemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.registerlogindemo.R;
import com.registerlogindemo.data.UserData;



public class ProfileFragment extends BaseFragment {
    private View view;
    private TextView nameTV, emailTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        nameTV = (TextView) view.findViewById(R.id.nameTV);
        emailTV = (TextView) view.findViewById(R.id.emailTV);
        UserData userData = baseActivity.db.getContact(baseActivity.store.getString("email"));
        nameTV.setText(userData.name);
        emailTV.setText(userData.email);
    }
}
