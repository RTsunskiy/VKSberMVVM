package com.example.vksbermvvm.presentation.utils;

import androidx.annotation.NonNull;

/**
 * Интерфейс для реализации метода нажатия на фотографию из коллекции в профиле пользователя
 */
public interface OnAlbumPhotoClickListener {
    void onItemClick(@NonNull int position);
}
