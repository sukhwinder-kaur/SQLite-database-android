package com.registerlogindemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.registerlogindemo.R;
import com.registerlogindemo.adapter.ImagesAdapter;
import com.registerlogindemo.data.AddImagesData;

import java.util.ArrayList;


public class ImagesListFragment extends BaseFragment {
    private View view;
    private RecyclerView itemsRV;
    ImagesAdapter imagesAdapter;
    ArrayList<AddImagesData> addImagesDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image_list, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        itemsRV = (RecyclerView) view.findViewById(R.id.itemsRV);
        itemsRV.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(baseActivity);
        itemsRV.setLayoutManager(layoutManager);
        addImagesDatas = baseActivity.db.getAllImages();
        imagesAdapter = new ImagesAdapter(addImagesDatas, baseActivity);
        itemsRV.setAdapter(imagesAdapter);

    }
}
