package com.example.vksbermvvm.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.AlbumPhotoHolder> {

    private List<AlbumPhoto> mAlbumPhotoList;
    private ViewPager2 mViewPager2;
    private Context mContext;



    ViewPagerAdapter(Context context, List<AlbumPhoto> albumPhotos, ViewPager2 viewPager2) {
        mAlbumPhotoList = albumPhotos;
        mViewPager2 = viewPager2;
        mContext = context;
    }



    @Override
    public AlbumPhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumPhotoHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_album_page, parent, false));
    }


    @Override
    public void onBindViewHolder(AlbumPhotoHolder holder, int position) {
            Picasso.with(mContext)
                    .load(mAlbumPhotoList.get(position).getmUrl())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.vk_gray_transparent_shape)
                    .into(holder.mAlbumPhoto);
    }

    @Override
    public int getItemCount() {
        return mAlbumPhotoList.size();
    }


        class AlbumPhotoHolder extends RecyclerView.ViewHolder {
        ImageView mAlbumPhoto;

            AlbumPhotoHolder(@NonNull View itemView) {
            super(itemView);
            mAlbumPhoto = itemView.findViewById(R.id.album_photo);
        }
    }

}
