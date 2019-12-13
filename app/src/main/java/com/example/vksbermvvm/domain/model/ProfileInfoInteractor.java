package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.List;


public class ProfileInfoInteractor {
    private final IProfileRepository mProfileRepository;

    public ProfileInfoInteractor(@NonNull IProfileRepository profileRepository) {
        mProfileRepository = profileRepository;
    }


    public Profile loadProfileInfo() throws LoadProfileException {
        try {
            return mProfileRepository.loadProfileInfo();
        } catch (IOException e) {
            throw new LoadProfileException("Не удалось загрузить профиль пользователя", e);
        }
    }

    public List<Profile> loadFriendsList() throws LoadFriendsException {
        try {
            return mProfileRepository.loadFriendsList();
        } catch (IOException e) {
            throw new LoadFriendsException("Не удалось загрузить список друзей", e);
        }
    }

    public List<AlbumPhoto> loadAlbumPhotos(String userId) throws LoadAlbumPhotosException {
        try {
            return mProfileRepository.loadAlbumPhotos(userId);
        } catch (IOException e) {
            throw new LoadAlbumPhotosException("Не удалось загрузить альбом пользователя", e);
        }
    }

    public List<Group> loadGroups() throws LoadGroupsExceprtion {
        try {
            return mProfileRepository.loadGroups();
        } catch (IOException e) {
            throw new LoadGroupsExceprtion("Не удалось загрузить список групп", e);
        }
    }
}
