package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelProfile.ResponseExample;
import com.example.vksbermvvm.domain.model.IProfileConverter;
import com.example.vksbermvvm.domain.model.model.Profile;

/**
 * Конвертирует профиль пользователя из data сущностей в domain
 *
 * @author Цунский Роман on 2019-12-15
 */
public class ProfileConverter implements IProfileConverter<ResponseExample, Profile> {

    @NonNull
    @Override
    public Profile convert(@NonNull ResponseExample responseExample) {
        return new Profile(responseExample.response.get(0).firstName,
                responseExample.response.get(0).lastName,
                responseExample.response.get(0).bdate,
                responseExample.response.get(0).homeTown,
                responseExample.response.get(0).country.title,
                responseExample.response.get(0).photo50,
                responseExample.response.get(0).id);
    }
}