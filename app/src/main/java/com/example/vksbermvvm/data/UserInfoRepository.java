package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelAlbumPhotos.Size;
import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelFriends.Item;
import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelGroups.ItemGroup;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Класс, в котором осуществляются все запросы к серверу ВКонтакте.
 */
public class UserInfoRepository implements IProfileRepository {
    /**
     * Список констант с параметрами для осуществления запросов к серверу ВКонтакте
     */
    private static final String BASE_URL = "https://api.vk.com/method/";
    private final String VK_API_VERSION = "5.103";
    private final String PROFILE_FIELDS = "bdate,home_town,country,photo_400_orig";
    private final String FRIENDS_FIELDS = "city,domain,nickname,bdate,city,country,photo_200_orig";
    private final String ALBUM_PHOTOS_SIZES = "1";
    private final String ALBUM_PHOTOS_HIDDEN = "1";
    private final String ALBUM_PHOTOS_COUNT = "200";
    private final String FRIENDS_ORDER = "hints";
    private final int GROUP_EXTENDED = 1;

    private Retrofit mRetrofit;
    private final JSONPlaceHolderApi mProfileApi;
    private final List<Profile> friendsList = new ArrayList<>();
    private final List<AlbumPhoto> photosList = new ArrayList<>();
    private final List<Group> groupList = new ArrayList<>();


    /**
     * Метод по созданию билдера библиотеки Retrofit
     */
    public UserInfoRepository() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mProfileApi = mRetrofit.create(JSONPlaceHolderApi.class);
    }


    /**
     * Метод по загрузке информации о профиле пользователя
     * @return возвращает объект pojo класса Profile
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
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
                profileObjectInfo.response.get(0).photo50,
                profileObjectInfo.response.get(0).id);
    }

    /**
     * Метод по загрузке списка друзей пользователя и детальной информации о профилях друзей
     * @return возвращает коллекцию объектов pojo Profile (информация о профилях друзей пользователя)
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<Profile> loadFriendsList() throws IOException {
        Response<Friends> response = mProfileApi.getFriends(CurrentUser.getAccessToken(),
                FRIENDS_ORDER,
                FRIENDS_FIELDS,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить список друзей");
        }

        Friends friends = response.body();
        friendsList.clear();
        String friendCity;
        String friendCountry;
        for (Item responseFriends : friends.response.items) {
            if (responseFriends.city != null) {
                friendCity = responseFriends.city.title;
            } else {
                friendCity = "Город отсутствует";
            }
            if (responseFriends.country != null) {
                friendCountry = responseFriends.country.title;
            } else {
                friendCountry = "Страна отсутствует";
            }
            friendsList.add(new Profile(responseFriends.firstName,
                    responseFriends.lastName,
                    responseFriends.bdate,
                    friendCity,
                    friendCountry,
                    responseFriends.photo_200_orig,
                    responseFriends.id));
        }
        return friendsList;
    }

    /**
     * Метод по загрузке списка фотографий профиля
     * @param userId Id пользователя, фотографии которого необходимо получить
     * @return возвращает коллекцию объектов pojo AlbumPhoto
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<AlbumPhoto> loadAlbumPhotos(String userId) throws IOException {
        Response<AlbumPhotos> response = mProfileApi.getAlbumPhotos(CurrentUser.getAccessToken(),
                userId,
                ALBUM_PHOTOS_SIZES,
                ALBUM_PHOTOS_HIDDEN,
                ALBUM_PHOTOS_COUNT,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить альбом пользователя");
        }
        AlbumPhotos photoObjectInfo = response.body();
        photosList.clear();
        for (com.example.vksbermvvm.data.modelAlbumPhotos.Item responseAlbum : photoObjectInfo.response.items) {
            String sizeType = "";
            String url = "";
            for (Size size : responseAlbum.sizes) {
                if (size.type.equals("z")) {
                    sizeType = size.type;
                    url = size.url;
                }
                else {
                    sizeType = responseAlbum.sizes.get(0).type;
                    url = responseAlbum.sizes.get(0).url;
                }
            }
            photosList.add(new AlbumPhoto(responseAlbum.id,
                    responseAlbum.albumId,
                    sizeType,
                    url));
        }
        return photosList;
    }

    /**
     * Метод для получения списка групп, на которые подписан пользователь
     * @return возвращает коллекцию pojo объектов Group
     * @throws IOException исключение, выбрасываемое, если не получен ответ от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<Group> loadGroups() throws IOException {
        Response<Groups> response = mProfileApi.getGroups(CurrentUser.getAccessToken(),
                GROUP_EXTENDED,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить список групп");
        }
        Groups groupObjectInfo = response.body();
        groupList.clear();

        for (ItemGroup responseGroups : groupObjectInfo.response.items) {

            groupList.add(new Group(responseGroups.id,
                    responseGroups.name,
                    responseGroups.screenName,
                    responseGroups.isClosed,
                    responseGroups.photo200));
        }
        return groupList;
    }
}
