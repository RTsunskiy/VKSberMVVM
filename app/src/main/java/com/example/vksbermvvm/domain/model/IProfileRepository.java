package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.List;


/**
 * Интерфейс с методами по получению информации о профилях
 */
public interface IProfileRepository {
    /**
     * Метод для получения информации о профиле
     * @return возвращает pojo обЪект профиля
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    Profile loadProfileInfo() throws IOException;

    /**
     * Метод для получения списка друзей пользователя
     * @return возвращает коллекцию Pojo объектов профилей друзей
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    List<Profile> loadFriendsList() throws IOException;

    /**
     * Метод для получения фотографий из альбомов пользователей
     * @param userId id пользователя, фотографии которого необходимо получить
     * @return возвращает коллекцию pojo объектов фотографий
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    List<AlbumPhoto> loadAlbumPhotos(String userId) throws IOException;

    /**
     * Метод для получения списка групп пользователя
     * @return возвращает коллекцию pojo объектов групп
     * @throws IOException исключение, выбрасываемое, если от сервера пришла ошибка
     */
    @NonNull
    List<Group> loadGroups () throws IOException;
}
