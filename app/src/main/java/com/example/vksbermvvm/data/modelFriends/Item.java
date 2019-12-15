package com.example.vksbermvvm.data.modelFriends;

import com.google.gson.annotations.SerializedName;

/**
 * Класс, в котором содержится подробная информация о друге пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */

public class Item {

    @SerializedName("id")
    public Integer id;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("is_closed")
    public Boolean isClosed;
    @SerializedName("can_access_closed")
    public Boolean canAccessClosed;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("domain")
    public String domain;
    @SerializedName("bdate")
    public String bdate;
    @SerializedName("country")
    public Country country;
    @SerializedName("photo_200_orig")
    public String photo_200_orig;
    @SerializedName("online")
    public Integer online;
    @SerializedName("track_code")
    public String trackCode;
    @SerializedName("city")
    public City city;

}