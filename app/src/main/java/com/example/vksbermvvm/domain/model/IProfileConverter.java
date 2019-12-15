package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

/**
 * Конвертер из одной произвольной сущности в другую
 *
 * @author Цунский Роман on 2019-12-15
 */
public interface IProfileConverter <From, To> {

    /**
     * Выполняет конвертацию
     */
    @NonNull
    To convert(@NonNull From from);
}
