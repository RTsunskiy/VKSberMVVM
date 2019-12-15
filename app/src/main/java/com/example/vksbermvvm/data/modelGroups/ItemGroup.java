package com.example.vksbermvvm.data.modelGroups;

import com.google.gson.annotations.SerializedName;

/**
 * Класс, в котором содержится детальная информация о группе, на которую подписан пользователь
 *
 * @author Цунский Роман on 2019-12-15
 */

public class ItemGroup {

    @SerializedName("id")
    public Integer id;
    @SerializedName("name")
    public String name;
    @SerializedName("screen_name")
    public String screenName;
    @SerializedName("is_closed")
    public Integer isClosed;
    @SerializedName("type")
    public String type;
    @SerializedName("is_admin")
    public Integer isAdmin;
    @SerializedName("is_member")
    public Integer isMember;
    @SerializedName("is_advertiser")
    public Integer isAdvertiser;
    @SerializedName("photo_50")
    public String photo50;
    @SerializedName("photo_100")
    public String photo100;
    @SerializedName("photo_200")
    public String photo200;

}
