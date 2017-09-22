package com.registerlogindemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.registerlogindemo.R;
import com.registerlogindemo.activity.BaseActivity;
import com.registerlogindemo.data.AddImagesData;
import com.registerlogindemo.utils.ImageUtils;

import java.util.ArrayList;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.MyViewHolder> {
    private ArrayList<AddImagesData> horizontalList;
    BaseActivity baseActivity;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView deleteIV, imageIV;

        public MyViewHolder(View view) {
            super(view);
            context = itemView.getContext();
            imageIV = (ImageView) view.findViewById(R.id.imageIV);
            deleteIV = (ImageView) view.findViewById(R.id.deleteIV);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    public ImagesAdapter(ArrayList<AddImagesData> horizontalList, BaseActivity baseActivity) {
        this.horizontalList = horizontalList;
        this.baseActivity = baseActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_images, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.imageIV.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.imageIV.setImageBitmap(ImageUtils.getImage(horizontalList.get(position).images));
        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                baseActivity.db.deleteImage(horizontalList.get(position).id);
                baseActivity.showToast("Image Deleted successfully");
                horizontalList.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}
