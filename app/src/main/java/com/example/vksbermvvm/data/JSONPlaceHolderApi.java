package com.example.vksbermvvm.data;

import com.example.vksbermvvm.data.model.ResponseExample;
import com.example.vksbermvvm.data.model.ResponseProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("account.getProfileInfo")
     Call<ResponseExample> getUserInfo(@Query("access_token") String token, @Query("v") String v);
}
