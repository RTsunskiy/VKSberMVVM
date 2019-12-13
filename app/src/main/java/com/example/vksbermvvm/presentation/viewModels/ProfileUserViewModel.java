package com.example.vksbermvvm.presentation.viewModels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.LoadProfileException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.SingleLiveEvent;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;


import java.util.List;
import java.util.concurrent.Executor;

/**
 * Вью модель профиля пользователя
 */
public class ProfileUserViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<Profile> mProfile = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final MutableLiveData<List<AlbumPhoto>> mAlbumPhoto = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    public ProfileUserViewModel(@NonNull Executor executor,
                                @NonNull ProfileInfoInteractor profileInfoInteractor,
                                @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }

    /**
     * Метод для получения информации о профиле
     */
    public void loadProfile() {
        mIsLoading.setValue(true);
        mExecutor.execute(() -> {
            try {
                Profile profile = mProfileInfoInteractor.loadProfileInfo();
                mProfile.postValue(profile);
            } catch (LoadProfileException e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_profile));
            }
            mIsLoading.postValue(false);
        });
    }


    /**
     * Метод для получения списка фотографий профиля
     * @param userId идентификатор пользователя, фотографии которого необходимо получить
     */
    public void loadAlbumPhoto(String userId) {
        mExecutor.execute(() -> {
            try {
                List<AlbumPhoto> albumPhoto = mProfileInfoInteractor.loadAlbumPhotos(userId);
                mAlbumPhoto.postValue(albumPhoto);
            } catch (LoadAlbumPhotosException e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_album));
            }
            mIsLoading.postValue(false);
        });
    }

    /**
     * LiveData с профилем
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<Profile> getProfile() {
        return mProfile;
    }

    /**
     * LiveData со списоком фотографий пользователя
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<List<AlbumPhoto>> getAlbum() {
        return mAlbumPhoto;
    }

    /**
     * Завершена ли загрузка
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    /**
     * Были ли ошибки при загрузке
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<String> getErrors() {
        return mErrors;
    }

}
