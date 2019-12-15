package com.example.vksbermvvm.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.data.CurrentUser;
import com.example.vksbermvvm.presentation.adapters.GroupsListAdapter;
import com.example.vksbermvvm.presentation.viewModelFactories.GroupsListViewModelFactory;
import com.example.vksbermvvm.presentation.viewModels.GroupsListViewModel;

/**
 * Фрагмент, реализующий логику отображения списка групп, на которые полдписан пользователь
 *
 * @author Цунский Роман on 2019-12-15
 */
public class GroupsListFragment extends Fragment {

    private RecyclerView mGroupsRecycler;
    private GroupsListViewModel mGroupsListViewModel;
    private View mLoadingView;
    private GroupsListAdapter mGroupsListAdapter;


    public static GroupsListFragment newInstance() {
        Bundle args = new Bundle();
        GroupsListFragment fragment = new GroupsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        mGroupsRecycler = view.findViewById(R.id.groups_recycler);
        mLoadingView = view.findViewById(R.id.loading_view);
        setupMvvm();
        return view;

    }

    private void setupMvvm() {
        mGroupsListViewModel = ViewModelProviders.of(this, new GroupsListViewModelFactory(getActivity()))
                .get(GroupsListViewModel.class);
        mGroupsListViewModel.getErrors().observe(this, error ->
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show());
        mGroupsListViewModel.getGroupList().observe(this, groupsList -> {
            mGroupsListAdapter = new GroupsListAdapter(getActivity(), groupsList);
            mGroupsRecycler.setAdapter(mGroupsListAdapter);
            mGroupsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        });
        mGroupsListViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mGroupsListViewModel.loadGroupList(CurrentUser.getAccessToken());
    }
}
