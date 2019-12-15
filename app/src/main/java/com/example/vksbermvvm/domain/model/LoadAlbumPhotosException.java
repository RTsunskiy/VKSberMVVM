package com.example.vksbermvvm.domain.model;

/**
 * Класс, реализующий собственное исключение при ошибке загрузки альбомов пользователей
 *
 * @author Цунский Роман on 2019-12-15
 */
public class LoadAlbumPhotosException extends Exception {

    public LoadAlbumPhotosException(String message, Throwable cause) {
        super(message, cause);
    }
}
