package com.example.vksbermvvm.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.LoadProfileException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.SingleLiveEvent;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;


import java.util.concurrent.Executor;

public class ProfileUserViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<Profile> mProfile = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    ProfileUserViewModel(@NonNull Executor executor,
                         @NonNull ProfileInfoInteractor profileInfoInteractor,
                         @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }

    void loadProfile() {
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

    @NonNull
    LiveData<Profile> getProfile() {
        return mProfile;
    }

    @NonNull
    LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    @NonNull
    LiveData<String> getErrors() {
        return mErrors;
    }

}
