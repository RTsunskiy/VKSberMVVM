package com.example.vksbermvvm.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.data.CurrentUser;
import com.squareup.picasso.Picasso;


public class CurrentUserProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView mFirstName;
    private TextView mLastName;
    private TextView mBDate;
    private TextView mCity;
    private TextView mCountry;
    private ProfileUserViewModel mViewModel;
    private View mLoadingView;
    private RecyclerView mRecyclerView;
    private AlbumPhotoAdapter mAlbumPhotoAdapter;

    public static CurrentUserProfileFragment newInstance() {
        Bundle args = new Bundle();
        CurrentUserProfileFragment fragment = new CurrentUserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_user_profile, container, false);
        initView(view);
        setupMvvm();
        return view;

    }

    private void setupMvvm() {
        mViewModel = ViewModelProviders.of(this, new ProfileViewModelFactory(getActivity()))
                .get(ProfileUserViewModel.class);
        mViewModel.getErrors().observe(this, error ->
                Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show());


        mViewModel.getProfile().observe(this, profile -> {
            mFirstName.setText(profile.getmFirstName());
            mLastName.setText(profile.getmLastName());
            mBDate.setText(profile.getmDate());
            mCity.setText(profile.getmCity());
            mCountry.setText(profile.getmCountry());
            Picasso.with(getActivity().getApplicationContext())
                    .load(profile.getmProfileImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.vk_gray_transparent_shape)
                    .into(profileImage);

        });
        mViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mViewModel.loadProfile();
        mViewModel.loadAlbumPhoto(CurrentUser.getId());
        mViewModel.getAlbum().observe(this, albumPhotos -> {
            mAlbumPhotoAdapter = new AlbumPhotoAdapter(getActivity(), albumPhotos);
            mRecyclerView.setAdapter(mAlbumPhotoAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            mRecyclerView.setLayoutManager(layoutManager);
        });
    }

    private void initView(View view) {
        profileImage = view.findViewById(R.id.profile_image);
        mFirstName = view.findViewById(R.id.first_name_tv);
        mLastName = view.findViewById(R.id.last_name_tv);
        mLoadingView = view.findViewById(R.id.loading_view);
        mBDate = view.findViewById(R.id.user_bDate);
        mCity = view.findViewById(R.id.user_city);
        mCountry = view.findViewById(R.id.user_country);
        mRecyclerView = view.findViewById(R.id.album_recycler);
    }



}
