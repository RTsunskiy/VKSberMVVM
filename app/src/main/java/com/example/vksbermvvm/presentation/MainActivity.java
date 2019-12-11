package com.example.vksbermvvm.presentation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.consts.ApiConsatants;
import com.example.vksbermvvm.data.CurrentUser;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

public class MainActivity extends AppCompatActivity{


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkAuth();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                menuItem -> {
                    switch (menuItem.getItemId()) {
                        case R.id.action_my_profile:
                            menuItem.setChecked(true);
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.root_layout, CurrentUserProfileFragment.newInstance())
                                    .addToBackStack(null)
                                    .commit();
                            break;
                        case R.id.action_my_friends:
                            menuItem.setChecked(true);
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.root_layout, FriendsListFragment.newInstance())
                                .addToBackStack(null)
                                .commit();
                            break;
                        case R.id.action_my_groups:
                            break;
                    }
                    return false;
                });
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
