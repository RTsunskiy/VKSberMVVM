package com.example.vksbermvvm.data;

import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Интерфейс, в котором объявлены методы по работе с API ВКонтакте
 */

public interface JSONPlaceHolderApi {
    /**
     * Метод для получения информации о профиле пользователя с сервера ВКонтакте.
     *
     * @param token  токен устройства, полученный при авторизации или запрошенный повторно, если истек срок его действия
     * @param fields параметр запроса к серверу ВКонтакте, в котором перечислены через запятую дополнительные аттрибуты пользователя, которые необходимо получить
     * @param v      версия API ВКонтакте (константа для всех запросов)
     * @return возвращает базовый класс ответа от сервера
     */
    @GET("users.get")
    Call<ResponseExample> getUserInfo(@Query("access_token") String token,
                                      @Query("fields") String fields,
                                      @Query("v") String v);

    /**
     * Метод для получения информации о друзьях пользователя с сервера ВКонтакте
     *
     * @param token  токен устройства, полученный при авторизации или запрошенный повторно, если истек срок его действия
     * @param order  параметр для сортировки списка друзей
     * @param fields параметр запроса к серверу ВКонтакте, в котором перечислены через запятую дополнительные аттрибуты пользователя, которые необходимо получить
     * @param v      версия API ВКонтакте (константа для всех запросов)
     * @return возвращает базовый класс ответа сервера
     */
    @GET("friends.get")
    Call<Friends> getFriends(@Query("access_token") String token,
                             @Query("order") String order,
                             @Query("fields") String fields,
                             @Query("v") String v);


    /**
     * Метод для получения фотографий из всех альбомов пользователя и фотографий из профилей друзей
     *
     * @param token      токен устройства, полученный при авторизации или запрошенный повторно, если истек срок его действия
     * @param userId     Id пользователя, чьи фотографии необходимо получить
     * @param photoSizes при передаче значения 1 —  возвращается дополнительная информация о размерах и типах фотографий
     * @param skipHidden при передаче значения 1 — не возвращать фотографии, скрытые из блока над стеной пользователя
     * @param photoCount количество фотографий, которые необходимо получить
     * @param v          версия API ВКонтакте (константа для всех запросов)
     * @return возвращает базовый класс ответа сервера
     */
    @GET("photos.getAll")
    Call<AlbumPhotos> getAlbumPhotos(@Query("access_token") String token,
                                     @Query("owner_id") String userId,
                                     @Query("photo_sizes") String photoSizes,
                                     @Query("skip_hidden") String skipHidden,
                                     @Query("count") String photoCount,
                                     @Query("v") String v);

    /**
     * Метод для получения списка групп, на которые подписан пользователь
     *
     * @param token    токен устройства, полученный при авторизации или запрошенный повторно, если истек срок его действия
     * @param extended если указать в качестве этого параметра 1, то будет возвращена полная информация о группах пользователя
     * @param v        версия API ВКонтакте (константа для всех запросов)
     * @return возвращает базовый класс ответа сервера
     */
    @GET("groups.get")
    Call<Groups> getGroups(@Query("access_token") String token,
                           @Query("extended") int extended,
                           @Query("v") String v);
}
