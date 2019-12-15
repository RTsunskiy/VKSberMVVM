package com.example.vksbermvvm.data.modelAlbumPhotos;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс, содержашщий общую информацию об альбоме профиля
 *
 * @author Цунский Роман on 2019-12-15
 */

public class Response {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<Item> items = null;
    @SerializedName("more")
    public Integer more;

}
