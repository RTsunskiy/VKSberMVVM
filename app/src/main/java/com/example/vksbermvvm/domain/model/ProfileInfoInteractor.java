package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;


import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;


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
}
