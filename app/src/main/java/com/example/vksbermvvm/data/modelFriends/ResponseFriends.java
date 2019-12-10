package com.example.vksbermvvm.data.modelFriends;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseFriends {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<Item> items = null;

}
