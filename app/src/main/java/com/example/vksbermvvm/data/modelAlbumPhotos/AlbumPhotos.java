package com.example.vksbermvvm.data.modelAlbumPhotos;

import com.google.gson.annotations.SerializedName;

/**
 * Базовай класс ответа на запрос списка фотографий из альбомов профилей
 *
 * @author Цунский Роман on 2019-12-15
 */

public class AlbumPhotos {

    @SerializedName("response")
    public Response response;

}
