package com.example.vksbermvvm.domain.model;

/**
 * Класс, реализующий собственное исключение при ошибке загрузки друзей пользователя
 */
public class LoadFriendsException extends Exception {

    public LoadFriendsException(String message, Throwable cause) {
        super(message, cause);
    }
}

