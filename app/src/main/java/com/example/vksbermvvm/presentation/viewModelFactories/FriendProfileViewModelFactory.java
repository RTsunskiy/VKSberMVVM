package com.example.vksbermvvm.presentation.viewModelFactories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.vksbermvvm.data.JSONPlaceHolderApi;
import com.example.vksbermvvm.data.UserInfoRepository;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.presentation.utils.ResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.FriendProfileViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Фабрика вьюмоделей профиля друга
 *
 * @author Цунский Роман on 2019-12-15
 */
public class FriendProfileViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplicationContext;
    private final Retrofit mRetrofit;
    private static final String BASE_URL = "https://api.vk.com/method/";
    private final JSONPlaceHolderApi mProfileApi;

    public FriendProfileViewModelFactory(@NonNull Context context) {
        mApplicationContext = context.getApplicationContext();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mProfileApi = mRetrofit.create(JSONPlaceHolderApi.class);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (FriendProfileViewModel.class.equals(modelClass)) {
            IProfileRepository profileRepository = new UserInfoRepository(mProfileApi);
            ProfileInfoInteractor interactor = new ProfileInfoInteractor(profileRepository);
            Executor executor = Executors.newSingleThreadExecutor();
            ResourceWrapper resourceWrapper = new ResourceWrapper(mApplicationContext.getResources());
            return (T) new FriendProfileViewModel(
                    executor,
                    interactor,
                    resourceWrapper);
        } else {
            return super.create(modelClass);
        }
    }

}
