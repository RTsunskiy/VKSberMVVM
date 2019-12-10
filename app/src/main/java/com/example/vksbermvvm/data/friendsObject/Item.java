package com.example.vksbermvvm.data.friendsObject;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("city")
    public City city;
    @SerializedName("country")
    public Country country;
    @SerializedName("photo_200_orig")
    public String photo200Orig;
    @SerializedName("online")
    public Integer online;
    @SerializedName("track_code")
    public String trackCode;

}