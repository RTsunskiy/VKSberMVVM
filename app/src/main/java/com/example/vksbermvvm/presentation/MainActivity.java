package com.example.vksbermvvm.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.consts.ApiConsatants;
import com.example.vksbermvvm.data.CurrentUser;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuth();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // Пользователь успешно авторизовался
                checkAuth();

            }

            @Override
            public void onError(VKError error) {
                // Произошла ошибка авторизации (например, пользователь запретил авторизацию)
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void startSignIn() {

        VKSdk.login(this, ApiConsatants.DEFAULT_LOGIN_SCOPE);
    }

    public void signedIn() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.root_layout, CurrentUserProfileFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }



    public void checkAuth() {
        if (!CurrentUser.isAuthorized()) {
           startSignIn();
        } else {
            signedIn();
        }
    }

}
