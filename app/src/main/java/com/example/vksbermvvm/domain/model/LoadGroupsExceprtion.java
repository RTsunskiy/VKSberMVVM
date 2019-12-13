package com.example.vksbermvvm.domain.model;

/**
 * Класс, реализующий собственное исключение при ошибке загрузки групп пользователя
 */
public class LoadGroupsExceprtion extends Exception {

    public LoadGroupsExceprtion(String message, Throwable cause) {
        super(message, cause);
    }
}
