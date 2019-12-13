package com.example.vksbermvvm.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.presentation.utils.OnAlbumPhotoClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Адаптер RecyclerView, отображающий фотографии из альбомов профилей
 */
public class AlbumPhotoAdapter extends RecyclerView.Adapter<AlbumPhotoAdapter.AlbumHolder> {

    private Context mContext;
    /**Коллекция фотографий пользователя, которую необходимо отобразить*/
    private List<AlbumPhoto> mPhotosList;
    /**Слушатель нажатий на элементы списка*/
    private OnAlbumPhotoClickListener mClickListener;

    public AlbumPhotoAdapter(Context context, List<AlbumPhoto> photosList) {
        mContext = context;
        mPhotosList = new ArrayList<>(photosList);
    }

    public void setClickListener(@Nullable OnAlbumPhotoClickListener clickListener) {
        mClickListener = clickListener;
    }


    @NonNull
    @Override
    public AlbumPhotoAdapter.AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumPhotoAdapter.AlbumHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.item_album_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumPhotoAdapter.AlbumHolder holder, int position) {
        AlbumPhoto albumPhoto = mPhotosList.get(position);
        Picasso.with(mContext.getApplicationContext())
                .load(albumPhoto.getmUrl())
                .placeholder(R.drawable.ic_iconfinder_user)
                .error(R.drawable.vk_gray_transparent_shape)
                .into(holder.albumPhoto);

        /**обработка нажатия на элемент списка и передача позиции элемента, на который нажали*/
        holder.itemView.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPhotosList.size();
    }

    class AlbumHolder extends RecyclerView.ViewHolder {

        ImageView albumPhoto;


        AlbumHolder(@NonNull View itemView) {
            super(itemView);
            albumPhoto = itemView.findViewById(R.id.album_photo_image);
        }

    }


}
