package com.example.vksbermvvm.presentation.utils;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.Profile;

/**
 * Интерфейс для реализации метода нажатия на друга в списке друзей
 */
public interface OnFriendClickListener {
    void onItemClick(@NonNull Profile profile);
}
