package com.example.vksbermvvm.data.modelFriends;

import com.google.gson.annotations.SerializedName;

/**
 * Базовый класс ответа от сервера ВКонтакте на запрос списка друзей
 *
 * @author Цунский Роман on 2019-12-15
 */

public class Friends {

    @SerializedName("response")
    public ResponseFriends response;

}
