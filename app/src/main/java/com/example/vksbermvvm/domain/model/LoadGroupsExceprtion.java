package com.example.vksbermvvm.domain.model;

/**
 * Класс, реализующий собственное исключение при ошибке загрузки групп пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */
public class LoadGroupsExceprtion extends Exception {

    public LoadGroupsExceprtion(String message, Throwable cause) {
        super(message, cause);
    }
}
