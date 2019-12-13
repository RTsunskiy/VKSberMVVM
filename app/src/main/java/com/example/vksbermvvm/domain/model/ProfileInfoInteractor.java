package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.List;


/**
 * Класс, реализующий логику приложения по загрузке и обработке данных с сервера
 */
public class ProfileInfoInteractor {
    private final IProfileRepository mProfileRepository;

    /**
     * Конструктор класса
     * @param profileRepository в качестве параметра необходимо передавать класс-наследник
     *                          интерфейса IProfileRepository
     */
    public ProfileInfoInteractor(@NonNull IProfileRepository profileRepository) {
        mProfileRepository = profileRepository;
    }


    /**
     * Метод для получения информации о профиле пользователя
     * @return возвращает объект класса Profile
     * @throws LoadProfileException собственное исключение, выбрасываемое,
     * если не удалось получить данные о профиле с сервера
     */
    public Profile loadProfileInfo() throws LoadProfileException {
        try {
            return mProfileRepository.loadProfileInfo();
        } catch (IOException e) {
            throw new LoadProfileException("Не удалось загрузить профиль пользователя", e);
        }
    }

    /**
     * Метод для получения списка друзей пользователя
     * @return возвращает коллекцию профилей друзей пользователя
     * @throws LoadFriendsException собственное исключение, выбрасываемое,
     * если не удалось получить список друзей пользователя
     */
    public List<Profile> loadFriendsList() throws LoadFriendsException {
        try {
            return mProfileRepository.loadFriendsList();
        } catch (IOException e) {
            throw new LoadFriendsException("Не удалось загрузить список друзей", e);
        }
    }

    /**
     * Метод для получения фотографий из альбомов пользователей
     * @param userId параметр, необходимый для указания id пользователя, фотографии которого необходимо получить
     * @return возвращает коллекцию фотографий пользователя
     * @throws LoadAlbumPhotosException собственное исключение, выбрасываемое,
     * если не удалось получить список фотографий
     */
    public List<AlbumPhoto> loadAlbumPhotos(String userId) throws LoadAlbumPhotosException {
        try {
            return mProfileRepository.loadAlbumPhotos(userId);
        } catch (IOException e) {
            throw new LoadAlbumPhotosException("Не удалось загрузить альбом пользователя", e);
        }
    }

    /**
     * Метод для получения списка групп, на которые подписан пользователь
     * @return возвращает список групп, на которые подписан пользователь
     * @throws LoadGroupsExceprtion собственное исключение, выбрасываемое,
     * если не удалось получить список групп, на которые подписан пользователь
     */
    public List<Group> loadGroups() throws LoadGroupsExceprtion {
        try {
            return mProfileRepository.loadGroups();
        } catch (IOException e) {
            throw new LoadGroupsExceprtion("Не удалось загрузить список групп", e);
        }
    }
}
