package com.example.vksbermvvm.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Адаптер для ViewPager2, полностью аналогичен адаптеру для RecyclerView
 */
public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.AlbumPhotoHolder> {

    private List<AlbumPhoto> mAlbumPhotoList;
    private Context mContext;


    public ViewPagerAdapter(Context context, List<AlbumPhoto> albumPhotos) {
        mAlbumPhotoList = albumPhotos;
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
                .placeholder(R.drawable.ic_iconfinder_user)
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
