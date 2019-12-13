package com.example.vksbermvvm.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.domain.model.LoadGroupsExceprtion;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.SingleLiveEvent;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;

import java.util.List;
import java.util.concurrent.Executor;

public class GroupsListViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<List<Group>> mGroupList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    GroupsListViewModel(@NonNull Executor executor,
                         @NonNull ProfileInfoInteractor profileInfoInteractor,
                         @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }

    void loadGroupList() {
        mIsLoading.setValue(true);
        mExecutor.execute(() -> {
            try {
                List<Group> groupList = mProfileInfoInteractor.loadGroups();
                mGroupList.postValue(groupList);
            } catch (LoadGroupsExceprtion e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_friends));
            }
            mIsLoading.postValue(false);
        });
    }

    @NonNull
    LiveData<List<Group>> getGroupList() {
        return mGroupList;
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
