package com.example.vksbermvvm.data.modelProfile;

import com.google.gson.annotations.SerializedName;

public class Response {

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
    @SerializedName("bdate")
    public String bdate;
    @SerializedName("country")
    public Country country;
    @SerializedName("photo_400_orig")
    public String photo50;
    @SerializedName("home_town")
    public String homeTown;

}
