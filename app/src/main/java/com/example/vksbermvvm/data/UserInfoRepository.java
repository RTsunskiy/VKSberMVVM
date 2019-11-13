package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.model.ProfileObject;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;


import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoRepository implements IProfileRepository {
    private static final String BASE_URL = "https://api.vk.com/method/";
    private Retrofit mRetrofit;
    private final JSONPlaceHolderApi mProfileApi;


    public UserInfoRepository() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProfileApi = mRetrofit.create(JSONPlaceHolderApi.class);
    }


    @NonNull
    @Override
    public Profile loadProfileInfo() throws IOException {
        Response<ProfileObject> response = mProfileApi.getUserInfo(CurrentUser.getAccessToken(), "5.103").execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить информацию о пользователе");
        }
        ProfileObject profileObjectInfo = response.body();
        return new Profile(profileObjectInfo.getFirstName(), profileObjectInfo.getLastName());
    }
}
