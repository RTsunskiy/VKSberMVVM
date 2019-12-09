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

import com.example.vksbermvvm.R;
import com.squareup.picasso.Picasso;


public class CurrentUserProfileFragment extends Fragment {

    private ImageView profileImage;
    private TextView firstName;
    private TextView lastName;
    private TextView bDate;
    private TextView city;
    private TextView country;
    private ProfileUserViewModel mViewModel;
    private View mLoadingView;

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
            firstName.setText(profile.getmFirstName());
            lastName.setText(profile.getmLastName());
            bDate.setText(profile.getmDate());
            city.setText(profile.getmCity());
            country.setText(profile.getmCountry());
            Picasso.with(getActivity().getApplicationContext())
                    .load(profile.getmProfileImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.vk_gray_transparent_shape)
                    .into(profileImage);
        });
        mViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mViewModel.loadProfile();
    }

    private void initView(View view) {
        profileImage = view.findViewById(R.id.profile_image);
        firstName = view.findViewById(R.id.first_name_tv);
        lastName = view.findViewById(R.id.last_name_tv);
        mLoadingView = view.findViewById(R.id.loading_view);
        bDate = view.findViewById(R.id.user_bDate);
        city = view.findViewById(R.id.user_city);
        country = view.findViewById(R.id.user_country);
    }



}
