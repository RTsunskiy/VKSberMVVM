package com.example.vksbermvvm.presentation.viewModels;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.LoadFriendsException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.SingleLiveEvent;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Вью модель со списком друзей пользователя
 */
public class FriendsListViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<List<Profile>> mFriendsList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    public FriendsListViewModel(@NonNull Executor executor,
                                @NonNull ProfileInfoInteractor profileInfoInteractor,
                                @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }

    /**
     * Загружает список друзей пользователя
     */
    public void loadFriendsList(String currentUserToken) {
        mIsLoading.setValue(true);
        mExecutor.execute(() -> {
            try {
                List<Profile> friendsListProfile = mProfileInfoInteractor.loadFriendsList(currentUserToken);
                mFriendsList.postValue(friendsListProfile);
            } catch (LoadFriendsException e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_friends));
            }
            mIsLoading.postValue(false);
        });
    }

    /**
     * LiveData со списком профилей друзей
     *
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<List<Profile>> getFriendsList() {
        return mFriendsList;
    }

    /**
     * Завершена ли загрузка
     *
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    /**
     * Есть ли ошибки при загрузке
     *
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<String> getErrors() {
        return mErrors;
    }

}
