package com.example.vksbermvvm.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.data.CurrentUser;
import com.example.vksbermvvm.presentation.utils.OnFriendClickListener;
import com.example.vksbermvvm.presentation.adapters.FriendsListAdapter;
import com.example.vksbermvvm.presentation.viewModelFactories.FriendsListViewModelFactory;
import com.example.vksbermvvm.presentation.viewModels.FriendsListViewModel;

/**
 * Фрагмент со списком друзей пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */
public class FriendsListFragment extends Fragment {

    private RecyclerView mFriendsRecycler;
    private FriendsListViewModel mFriendsListViewModel;
    private View mLoadingView;
    private FriendsListAdapter mFriendsListAdapter;
    /**
     * Обработка нажатия на элемент списка. В результате нажатия фрагмент должен заменяться на FriendProfileFragment
     */
    private OnFriendClickListener mOnFriendClickListener = (profile) -> requireActivity()
            .getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.root_layout, FriendProfileFragment.newInstance(profile))
            .addToBackStack(FriendProfileFragment.class.getSimpleName())
            .commit();

    public static FriendsListFragment newInstance() {
        Bundle args = new Bundle();
        FriendsListFragment fragment = new FriendsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
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

    /**
     * В данном методе осуществляется подписка на LiveData и загрузка данных с сервера
     */
    private void setupMvvm() {
        mFriendsListViewModel = ViewModelProviders.of(this, new FriendsListViewModelFactory(getActivity()))
                .get(FriendsListViewModel.class);
        mFriendsListViewModel.getErrors().observe(this, error ->
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show());
        mFriendsListViewModel.getFriendsList().observe(this, friendsList -> {
            mFriendsListAdapter = new FriendsListAdapter(getActivity(), friendsList);
            mFriendsListAdapter.setClickListener(mOnFriendClickListener);
            mFriendsRecycler.setAdapter(mFriendsListAdapter);
            mFriendsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        });
        mFriendsListViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mFriendsListViewModel.loadFriendsList(CurrentUser.getAccessToken());
    }

    /**
     * Переопределенный метод для реализации ToolBar menu. Используется для реализации SearchView.
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_toolbar, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (mFriendsListAdapter != null) {
                    mFriendsListAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
    }

}
