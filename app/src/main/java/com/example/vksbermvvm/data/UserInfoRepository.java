package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelFriends.Item;
import com.example.vksbermvvm.data.modelFriends.ResponseFriends;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.model.Profile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserInfoRepository implements IProfileRepository {
    private static final String BASE_URL = "https://api.vk.com/method/";
    private final String VK_API_VERSION = "5.103";
    private final String PROFILE_FIELDS = "bdate,home_town,country,photo_400_orig";
    private final String FRIENDS_FIELDS = "city,domain,nickname,bdate,city,country,photo_200_orig";

    private Retrofit mRetrofit;
    private final JSONPlaceHolderApi mProfileApi;

    private final List<Profile> friendsList = new ArrayList<>();


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
        Response<ResponseExample> response = mProfileApi.getUserInfo(CurrentUser.getAccessToken(),
                PROFILE_FIELDS,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить информацию о пользователе");
        }
        ResponseExample profileObjectInfo = response.body();
        return new Profile(profileObjectInfo.response.get(0).firstName,
                profileObjectInfo.response.get(0).lastName,
                profileObjectInfo.response.get(0).bdate,
                profileObjectInfo.response.get(0).homeTown,
                profileObjectInfo.response.get(0).country.title,
                profileObjectInfo.response.get(0).photo50);
    }

    @NonNull
    @Override
    public List<Profile> loadFriendsList() throws IOException {
        Response<Friends> response = mProfileApi.getFriends(CurrentUser.getAccessToken(),
                FRIENDS_FIELDS,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить список друзей");
        }

        Friends friends = response.body();
        friendsList.clear();
        for (Item responseFriends : friends.response.items) {
            friendsList.add(new Profile(responseFriends.firstName,
                    responseFriends.lastName,
                    responseFriends.bdate,
//                    responseFriends.city.title,
//                    responseFriends.country.title,
                    "jkjhkjh",
                    "jkhkjhj",
                    responseFriends.photo200Orig));
        }
        return friendsList;
    }
}
