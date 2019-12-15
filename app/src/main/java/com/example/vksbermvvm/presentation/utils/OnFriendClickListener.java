package com.example.vksbermvvm.presentation.utils;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.Profile;

/**
 * Интерфейс для реализации метода нажатия на друга в списке друзей
 *
 * @author Цунский Роман on 2019-12-15
 */
public interface OnFriendClickListener {
    void onItemClick(@NonNull Profile profile);
}
