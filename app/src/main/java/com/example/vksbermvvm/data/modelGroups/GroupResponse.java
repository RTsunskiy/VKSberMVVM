package com.example.vksbermvvm.data.modelGroups;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс, в котором содержится общая информация о группах пользователя, полученная в резултате запроса к серверу ВКонтакте
 *
 * @author Цунский Роман on 2019-12-15
 */

public class GroupResponse {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<ItemGroup> items = null;

}