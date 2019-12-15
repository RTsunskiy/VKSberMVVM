package com.example.vksbermvvm.presentation.fragments;

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
import androidx.viewpager2.widget.ViewPager2;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.data.CurrentUser;
import com.example.vksbermvvm.presentation.utils.OnAlbumPhotoClickListener;
import com.example.vksbermvvm.presentation.adapters.AlbumPhotoAdapter;
import com.example.vksbermvvm.presentation.adapters.ViewPagerAdapter;
import com.example.vksbermvvm.presentation.viewModelFactories.ProfileViewModelFactory;
import com.example.vksbermvvm.presentation.viewModels.ProfileUserViewModel;
import com.squareup.picasso.Picasso;

/**
 * Фрагмент, реализующий логику отображения профиля текущего пользователя
 *
 * @author Цунский Роман on 2019-12-15
 */
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
    private ViewPager2 mViewPager2;
    private OnAlbumPhotoClickListener mOnPhotoClickListener;

    public static CurrentUserProfileFragment newInstance() {
        Bundle args = new Bundle();
        CurrentUserProfileFragment fragment = new CurrentUserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**метод для автоматического сохранения состояния фрагмента при уничтожении Activity*/
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_user_profile, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        setupMvvm();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * Метод для получения данны от сервера. В данном методе осуществляется подписка на LiveData и вызов методов
     * для получения данных от сервера.
     */
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
                    .placeholder(R.drawable.ic_iconfinder_user)
                    .error(R.drawable.vk_gray_transparent_shape)
                    .into(profileImage);

        });

        mViewModel.isLoading().observe(this, isLoading -> mLoadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE));
        mViewModel.loadProfile(CurrentUser.getAccessToken());
        mViewModel.loadAlbumPhoto(CurrentUser.getId(), CurrentUser.getAccessToken());
        mViewModel.getAlbum().observe(this, albumPhotos -> {
            mAlbumPhotoAdapter = new AlbumPhotoAdapter(getActivity(), albumPhotos);

            mRecyclerView.setAdapter(mAlbumPhotoAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false);
            mRecyclerView.setLayoutManager(layoutManager);

            /**
             * Обработка нажатия на фотографию в альбоме пользователя
             */
            mOnPhotoClickListener = position -> {
                mViewPager2.setAdapter(new ViewPagerAdapter(getActivity(), albumPhotos));
                mViewPager2.setUserInputEnabled(true);
                mViewPager2.setVisibility(View.VISIBLE);
                mViewPager2.postDelayed(() -> mViewPager2.setCurrentItem(position), 1);
            };
            mAlbumPhotoAdapter.setClickListener(mOnPhotoClickListener);
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
        mViewPager2 = view.findViewById(R.id.viewPager2_album_photos);
    }


}
