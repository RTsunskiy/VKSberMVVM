package com.example.vksbermvvm.data.modelGroups;

import com.google.gson.annotations.SerializedName;

/**
 * Базовый класс групп пользователя, полученный в результате запроса к серверу ВКонтакте
 */

public class Groups {

    @SerializedName("response")
    public GroupResponse response;

}
