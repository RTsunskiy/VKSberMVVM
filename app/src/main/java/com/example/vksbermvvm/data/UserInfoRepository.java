package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

/**
 * Класс, в котором осуществляются все запросы к серверу ВКонтакте.
 *
 * @author Цунский Роман on 2019-12-15
 */
public class UserInfoRepository implements IProfileRepository {
    /**
     * Список констант с параметрами для осуществления запросов к серверу ВКонтакте
     */
    private final String VK_API_VERSION = "5.103";
    private final String PROFILE_FIELDS = "bdate,home_town,country,photo_400_orig";
    private final String FRIENDS_FIELDS = "city,domain,nickname,bdate,city,country,photo_200_orig";
    private final String ALBUM_PHOTOS_SIZES = "1";
    private final String ALBUM_PHOTOS_HIDDEN = "1";
    private final String ALBUM_PHOTOS_COUNT = "200";
    private final String FRIENDS_ORDER = "hints";
    private final int GROUP_EXTENDED = 1;
    private final JSONPlaceHolderApi mProfileApi;
    private final ProfileConverter mProfoleConverter;
    private final FriendsListConverter mFriendsListConverter;
    private final AlbumPhotoListConverter mAlbumPhotoListConverter;
    private final GroupListConverter mGroupListConverter;


    /**
     * Конструктор репозитория
     */
    public UserInfoRepository(JSONPlaceHolderApi retrofitApi) {
        mProfileApi = retrofitApi;
        mProfoleConverter = new ProfileConverter();
        mFriendsListConverter = new FriendsListConverter();
        mAlbumPhotoListConverter = new AlbumPhotoListConverter();
        mGroupListConverter = new GroupListConverter();
    }


    /**
     * Метод по загрузке информации о профиле пользователя
     *
     * @return возвращает объект pojo класса Profile
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    @Override
    public Profile loadProfileInfo(String currentUserToken) throws IOException {
        Response<ResponseExample> response = mProfileApi.getUserInfo(currentUserToken,
                PROFILE_FIELDS,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить информацию о пользователе");
        }
        ResponseExample profileObjectInfo = response.body();
        return mProfoleConverter.convert(profileObjectInfo);
    }

    /**
     * Метод по загрузке списка друзей пользователя и детальной информации о профилях друзей
     *
     * @return возвращает коллекцию объектов pojo Profile (информация о профилях друзей пользователя)
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<Profile> loadFriendsList(String currentUserToken) throws IOException {
        Response<Friends> response = mProfileApi.getFriends(currentUserToken,
                FRIENDS_ORDER,
                FRIENDS_FIELDS,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить список друзей");
        }
        Friends friends = response.body();

        return mFriendsListConverter.convert(friends);
    }

    /**
     * Метод по загрузке списка фотографий профиля
     *
     * @param userId Id пользователя, фотографии которого необходимо получить
     * @return возвращает коллекцию объектов pojo AlbumPhoto
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<AlbumPhoto> loadAlbumPhotos(String userId, String currentUserToken) throws IOException {
        Response<AlbumPhotos> response = mProfileApi.getAlbumPhotos(currentUserToken,
                userId,
                ALBUM_PHOTOS_SIZES,
                ALBUM_PHOTOS_HIDDEN,
                ALBUM_PHOTOS_COUNT,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить альбом пользователя");
        }
        AlbumPhotos photoObjectInfo = response.body();

        return mAlbumPhotoListConverter.convert(photoObjectInfo);
    }

    /**
     * Метод для получения списка групп, на которые подписан пользователь
     *
     * @return возвращает коллекцию pojo объектов Group
     * @throws IOException исключение, выбрасываемое, если не получен ответ от сервера пришла ошибка
     */
    @NonNull
    @Override
    public List<Group> loadGroups(String currentUserToken) throws IOException {
        Response<Groups> response = mProfileApi.getGroups(currentUserToken,
                GROUP_EXTENDED,
                VK_API_VERSION).execute();
        if (response.body() == null || response.errorBody() != null) {
            throw new IOException("Не удалось загрузить список групп");
        }
        Groups groupObjectInfo = response.body();

        return mGroupListConverter.convert(groupObjectInfo);
    }
}
