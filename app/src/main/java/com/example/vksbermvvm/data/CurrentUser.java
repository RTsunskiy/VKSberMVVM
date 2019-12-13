package com.example.vksbermvvm.data;


import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

/**
 * Класс, в котором располагаются методы для авторизации пользователя
 */

public class CurrentUser {

    /**
     * Метод, который проверяет имеется ли сейчас токен на устройстве пользователя
     * для осуществления запросов к серверу ВКонакте. Если токена нет,
     * то осуществляется запрос токена посредством VKSdk
     * @return возвращает токен пользователя
     */
    public static String getAccessToken() {
        if (VKAccessToken.currentToken() == null) {
            return null;
        }

        return VKAccessToken.currentToken().accessToken;
    }

    /**
     * Метод, который проверяет имеется ли токен на устройстве пользователя и запрашивает Id пользователя.
     * Если на устройстве пользователя отсутствует токен, то возвращается null.
     * @return возвращает Id пользователя ВКонтакте посредством VKSdk (необходим для осуществления запросов к серверу ВКонтакте)
     */
    public static String getId() {
        if (VKAccessToken.currentToken() != null) {
            return VKAccessToken.currentToken().userId;
        }

        return null;
    }


    /**
     * Метод, который проверяет авторизован ли пользователь в приложении и действующий ли у него токен.
     * @return возвращает true, если на устройстве имеется токен и он действительный, иначе возвращает false,
     * если на устройстве отсутствует токен или он не действителен
     */
    public static boolean isAuthorized() {
        return VKSdk.isLoggedIn()
                && VKAccessToken.currentToken() != null
                && !VKAccessToken.currentToken().isExpired();
    }
}
