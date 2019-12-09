package com.example.vksbermvvm.data;

import com.example.vksbermvvm.data.model.ResponseExample;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("users.get")
     Call<ResponseExample> getUserInfo(@Query("access_token") String token,
                                       @Query("fields") String fields,
                                       @Query("v") String v);
}
