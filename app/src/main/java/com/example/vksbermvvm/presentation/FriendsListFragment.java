package com.example.vksbermvvm.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;

public class FriendsListFragment extends Fragment {

    private RecyclerView mFriendsRecycler;
    private FriendsListViewModel mFriendsListViewModel;
    private View mLoadingView;
    private FriendsListAdapter mFriendsListAdapter;
    private OnFriendClickListener mOnFriendClickListener = (profile) ->
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.root_layout, FriendProfileFragment.newInstance(profile))
                    .addToBackStack(FriendProfileFragment.class.getSimpleName())
                    .commit();

    public static FriendsListFragment newInstance() {
        Bundle args = new Bundle();
        FriendsListFragment fragment = new FriendsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends_list, container, false);
        mFriendsRecycler = view.findViewById(R.id.friends_recycler);
        mLoadingView = view.findViewById(R.id.loading_view);
        setupMvvm();
        return view;

    }

    private void setupMvvm() {
        mFriendsListViewModel = ViewModelProviders.of(this, new FriendsListViewModelFactory(getActivity()))
                .get(FriendsListViewModel.class);
        mFriendsListViewModel.getErrors().observe(this, error ->
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show());
        mFriendsListViewModel.getFriendsList().observe(this, friendsList -> {
            mFriendsListAdapter = new FriendsListAdapter(getActivity(), friendsList);
            mFriendsListAdapter.setClickListener(mOnFriendClickListener);
            mFriendsRecycler.setAdapter(mFriendsListAdapter);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
            mFriendsRecycler.addItemDecoration(dividerItemDecoration);
            mFriendsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        });
        mFriendsListViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mFriendsListViewModel.loadFriendsList();
    }
}
