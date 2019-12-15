package com.example.vksbermvvm.data.modelProfile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Класс, содержащий коллекцию элементов с информацией о профиле пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */

public class ResponseExample {

    @SerializedName("response")
    public List<Response> response = null;

}
