package com.example.vksbermvvm.data;

import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {
    @GET("users.get")
    Call<ResponseExample> getUserInfo(@Query("access_token") String token,
                                      @Query("fields") String fields,
                                      @Query("v") String v);

    @GET("friends.get")
    Call<Friends> getFriends(@Query("access_token") String token,
                             @Query("order") String order,
                             @Query("fields") String fields,
                             @Query("v") String v);

    @GET("photos.getAll")
    Call<AlbumPhotos> getAlbumPhotos(@Query("access_token") String token,
                                     @Query("owner_id") String userId,
                                     @Query("photo_sizes") String photoSizes,
                                     @Query("skip_hidden") String skipHidden,
                                     @Query("count") String photoCount,
                                     @Query("v") String v);

    @GET("groups.get")
    Call<Groups> getGroups (@Query("access_token") String token,
                           @Query("extended") int extended,
                           @Query("v") String v);
}
