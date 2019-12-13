package com.example.vksbermvvm.data.modelAlbumPhotos;

import com.google.gson.annotations.SerializedName;

/**
 * Класс, содержащий детальную информацию о фотографии в альбоме профиля
 */

public class Size {

    @SerializedName("type")
    public String type;
    @SerializedName("url")
    public String url;
    @SerializedName("width")
    public Integer width;
    @SerializedName("height")
    public Integer height;

}
