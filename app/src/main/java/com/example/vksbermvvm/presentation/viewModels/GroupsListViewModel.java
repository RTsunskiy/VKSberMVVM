package com.example.vksbermvvm.presentation.viewModels;

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

/**
 * Вью модель списка групп пользователя
 */
public class GroupsListViewModel extends ViewModel {
    private final Executor mExecutor;
    private final ProfileInfoInteractor mProfileInfoInteractor;
    private final MutableLiveData<List<Group>> mGroupList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mIsLoading = new MutableLiveData<>();
    private final SingleLiveEvent<String> mErrors = new SingleLiveEvent<>();
    private final IResourceWrapper mResourceWrapper;

    public GroupsListViewModel(@NonNull Executor executor,
                               @NonNull ProfileInfoInteractor profileInfoInteractor,
                               @NonNull IResourceWrapper resourceWrapper) {
        mProfileInfoInteractor = profileInfoInteractor;
        mExecutor = executor;
        mResourceWrapper = resourceWrapper;
    }

    /**
     * Возвращает список групп, на которые подписан пользователь
     */
    public void loadGroupList(String currentUserToken) {
        mIsLoading.setValue(true);
        mExecutor.execute(() -> {
            try {
                List<Group> groupList = mProfileInfoInteractor.loadGroups(currentUserToken);
                mGroupList.postValue(groupList);
            } catch (LoadGroupsExceprtion e) {
                mErrors.postValue(mResourceWrapper.getString(R.string.error_loading_friends));
            }
            mIsLoading.postValue(false);
        });
    }

    /**
     * LiveData со списком групп, на которые подписан пользователь
     *
     * @return возвращает список групп
     */
    @NonNull
    public LiveData<List<Group>> getGroupList() {
        return mGroupList;
    }

    /**
     * Завершена ли загрузка
     *
     * @return возвращает LiveTada
     */
    @NonNull
    public LiveData<Boolean> isLoading() {
        return mIsLoading;
    }

    /**
     * Были ли ошиьки при загрузке
     *
     * @return возвращает LiveData
     */
    @NonNull
    public LiveData<String> getErrors() {
        return mErrors;
    }

}
