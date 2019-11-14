package com.example.vksbermvvm.data.model;

import com.google.gson.annotations.SerializedName;

public class ResponseProfile {

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("bdate")
    public String bdate;

    @SerializedName("bdate_visibility")
    public Integer bdateVisibility;

    @SerializedName("city")
    public City city;

    @SerializedName("country")
    public Country country;

    @SerializedName("home_town")
    public String homeTown;

    @SerializedName("phone")
    public String phone;

    @SerializedName("relation")
    public Integer relation;

    @SerializedName("sex")
    public Integer sex;

    @SerializedName("status")
    public String status;
}
