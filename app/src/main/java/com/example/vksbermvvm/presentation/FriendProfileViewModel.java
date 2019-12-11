package com.example.vksbermvvm.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.SingleLiveEvent;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;

import java.util.List;
import java.util.concurrent.Executor;

public class FriendProfileViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<List<AlbumPhoto>> mAlbumPhoto = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    FriendProfileViewModel(@NonNull Executor executor,
                         @NonNull ProfileInfoInteractor profileInfoInteractor,
                         @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }


    void loadAlbumPhoto(String userId) {
        mExecutor.execute(() -> {
            try {
                List<AlbumPhoto> albumPhoto = mProfileInfoInteractor.loadAlbumPhotos(userId);
                mAlbumPhoto.postValue(albumPhoto);
            } catch (LoadAlbumPhotosException e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_album));
            }
        });
    }

    @NonNull
    LiveData<List<AlbumPhoto>> getAlbum() {
        return mAlbumPhoto;
    }

    @NonNull
    LiveData<String> getErrors() {
        return mErrors;
    }

}