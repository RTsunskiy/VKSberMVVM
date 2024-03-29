package com.example.vksbermvvm.data.modelAlbumPhotos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс с данными об одном элементе коллекции фотографии альбома профиля
 *
 * @author Цунский Роман on 2019-12-15
 */

public class Item {

    @SerializedName("id")
    public Integer id;
    @SerializedName("album_id")
    public Integer albumId;
    @SerializedName("owner_id")
    public Integer ownerId;
    @SerializedName("sizes")
    public List<Size> sizes = null;
    @SerializedName("text")
    public String text;
    @SerializedName("date")
    public Integer date;
    @SerializedName("post_id")
    public Integer postId;
    @SerializedName("real_offset")
    public Integer realOffset;

}
