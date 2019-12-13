package com.example.vksbermvvm.presentation.viewModelFactories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.vksbermvvm.data.UserInfoRepository;
import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.presentation.utils.ResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.GroupsListViewModel;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Фабрика вьюмоделей групп, на которые подписан пользователь
 */
public class GroupsListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mApplicationContext;

    public GroupsListViewModelFactory(@NonNull Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (GroupsListViewModel.class.equals(modelClass)) {
            IProfileRepository profileRepository = new UserInfoRepository();
            ProfileInfoInteractor interactor = new ProfileInfoInteractor(profileRepository);
            Executor executor = Executors.newSingleThreadExecutor();
            ResourceWrapper resourceWrapper = new ResourceWrapper(mApplicationContext.getResources());
            return (T) new GroupsListViewModel(
                    executor,
                    interactor,
                    resourceWrapper);
        } else {
            return super.create(modelClass);
        }
    }
}