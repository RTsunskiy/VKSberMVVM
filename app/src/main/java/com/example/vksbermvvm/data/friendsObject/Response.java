package com.example.vksbermvvm.data.friendsObject;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<Item> items = null;

}
