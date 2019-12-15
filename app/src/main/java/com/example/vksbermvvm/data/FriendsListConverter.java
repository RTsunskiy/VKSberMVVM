package com.example.vksbermvvm.data;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelFriends.Item;
import com.example.vksbermvvm.domain.model.IProfileConverter;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.util.ArrayList;
import java.util.List;

/**
 * Конвертирует список друзей из data сущностей в domain
 *
 * @author Цунский Роман on 2019-12-15
 */
public class FriendsListConverter implements IProfileConverter<Friends, List<Profile>> {

    private final List<Profile> friendsList = new ArrayList<>();

    @NonNull
    @Override
    public List<Profile> convert(@NonNull Friends friends) {
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
}
