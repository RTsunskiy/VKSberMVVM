package com.example.vksbermvvm.data.modelProfile;

import com.google.gson.annotations.SerializedName;

/**
 * Класс, в котором содержится информация о стране, в которой проживает пользователь, полученная от сервера ВКонтакте
 */

public class Country {
    @SerializedName("id")
    public Integer id;
    @SerializedName("title")
    public String title;
}
