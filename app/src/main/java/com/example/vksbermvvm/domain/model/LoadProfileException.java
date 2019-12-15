package com.example.vksbermvvm.domain.model;

/**
 * Класс унаследованный, реализующий собственное исключение при ошибке загрузки профиля пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */
public class LoadProfileException extends Exception {

    public LoadProfileException(String message, Throwable cause) {
        super(message, cause);
    }
}

