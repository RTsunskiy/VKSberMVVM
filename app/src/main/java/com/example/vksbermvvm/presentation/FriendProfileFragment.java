package com.example.vksbermvvm.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.data.CurrentUser;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.squareup.picasso.Picasso;

public class FriendProfileFragment extends Fragment {

    private FriendProfileViewModel mViewModel;
    private AlbumPhotoAdapter mAlbumPhotoAdapter;
    private RecyclerView mRecyclerView;

    private static final String ARG_FRIEND_PROFILE = "ARG_FRIEND_PROFILE";

    public static FriendProfileFragment newInstance(@NonNull Profile profile) {
        FriendProfileFragment fragment = new FriendProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_FRIEND_PROFILE, profile);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(this, new FriendProfileViewModelFactory(getActivity()))
                .get(FriendProfileViewModel.class);
        Profile profile = getProfileFromArgs();
        ((TextView) view.findViewById(R.id.first_name_tv)).setText(profile.getmFirstName());
        ((TextView) view.findViewById(R.id.last_name_tv)).setText(profile.getmLastName());
        ((TextView) view.findViewById(R.id.user_bDate)).setText(profile.getmDate());
        ((TextView) view.findViewById(R.id.user_city)).setText(profile.getmCity());
        ((TextView) view.findViewById(R.id.user_country)).setText(profile.getmCountry());
        mRecyclerView = view.findViewById(R.id.album_recycler);
            Picasso.with(getActivity().getApplicationContext())
                    .load(profile.getmProfileImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.vk_gray_transparent_shape)
                    .into((ImageView) view.findViewById(R.id.profile_image));

        mViewModel.loadAlbumPhoto(String.valueOf(profile.getmId()));
        mViewModel.getAlbum().observe(this, albumPhotos -> {
            mAlbumPhotoAdapter = new AlbumPhotoAdapter(getActivity(), albumPhotos);
            mRecyclerView.setAdapter(mAlbumPhotoAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            mRecyclerView.setLayoutManager(layoutManager);
        });
    }

    @NonNull
    private Profile getProfileFromArgs() {
        Bundle arguments = getArguments();
        if (arguments == null) {
            throw new IllegalStateException("Arguments must be set");
        }
        Profile profile = arguments.getParcelable(ARG_FRIEND_PROFILE);
        if (profile == null) {
            throw new IllegalStateException("Profile must be set");
        }
        return profile;
    }
}
