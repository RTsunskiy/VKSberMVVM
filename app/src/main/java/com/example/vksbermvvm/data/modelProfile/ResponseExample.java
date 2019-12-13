package com.example.vksbermvvm.data.modelProfile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс, содержащий коллекцию элементов с информацией о профиле пользователя
 */

public class ResponseExample {

    @SerializedName("response")
    public List<Response> response = null;

}
