package com.example.vksbermvvm.domain.model;

/**
 * Класс, реализующий собственное исключение при ошибке загрузки альбомов пользователей
 */
public class LoadAlbumPhotosException extends Exception {

    public LoadAlbumPhotosException(String message, Throwable cause) {
        super(message, cause);
    }
}
