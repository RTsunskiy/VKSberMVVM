package com.example.vksbermvvm.data.modelFriends;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс, в котором содержится общая информация о друзьях пользователя, полученная в результате запроса к серверу ВКонтакте
 *
 * @author Цунский Роман on 2019-12-15
 */

public class ResponseFriends {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<Item> items = null;

}
