package com.example.vksbermvvm.presentation.utils;

import androidx.annotation.StringRes;

/**
 * Обёртка над ресурсами приложения, нужна для того чтобы
 * вью модель и доменный слой не зависели от классов из Android SDK.
 *
 * @author Цунский Роман on 2019-12-15
 */
public interface IResourceWrapper {

    /**
     * Получить строку
     *
     * @param resId идентификатор строки
     */
    String getString(@StringRes int resId);

    /**
     * Получить форматированную строку
     *
     * @param resId      идентификатор строки
     * @param formatArgs аргументы форматирования
     */
    String getString(@StringRes int resId, Object... formatArgs);
}