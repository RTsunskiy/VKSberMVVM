package com.example.vksbermvvm.data.modelGroups;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GroupResponse {

    @SerializedName("count")
    public Integer count;
    @SerializedName("items")
    public List<ItemGroup> items = null;

}