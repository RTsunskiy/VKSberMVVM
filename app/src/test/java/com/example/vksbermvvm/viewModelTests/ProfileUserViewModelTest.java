package com.example.vksbermvvm.viewModelTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.testUtils.SynchronousExecutor;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.LoadProfileException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.ProfileUserViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Юнит тесты интеррактора {@link ProfileUserViewModel}
 *
 * @author Цунский Роман on 2019-12-15
 */
public class ProfileUserViewModelTest {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private ProfileUserViewModel mViewModel;
    private ProfileInfoInteractor mProfileInfoInteractor;
    private IResourceWrapper mResourceWrapper;
    private Profile mProfile;
    private List<AlbumPhoto> mAlbumPhotoList;
    private AlbumPhoto mAlbumPhoto;

    @Before
    public void setUp() {
        mAlbumPhotoList = new ArrayList<>(Arrays.asList(mock(AlbumPhoto.class), mock(AlbumPhoto.class)));
        mProfileInfoInteractor = mock(ProfileInfoInteractor.class);
        mResourceWrapper = mock(IResourceWrapper.class);
        mViewModel = new ProfileUserViewModel(
                new SynchronousExecutor(),
                mProfileInfoInteractor,
                mResourceWrapper
        );

        mProfile = new Profile("Vasya",
                "Ivanov",
                "12.12.2000",
                "Toronto",
                "Canada",
                "url",
                12345);

    }

    @Test
    public void testLoadProfileInfo_happyCase() throws LoadProfileException {
        // arrange
        when(mProfileInfoInteractor.loadProfileInfo(Mockito.anyString())).thenReturn(mProfile);
        Profile profile = mProfile;
        // act
        mViewModel.loadProfile(Mockito.anyString());
        // assert
        assertThat(mViewModel.getProfile().getValue(), is(profile));
        assertThat(mViewModel.isLoading().getValue(), is(false));
    }

    @Test
    public void testLoadProfileInfo_interactorThrowsException() throws LoadProfileException {
        // arrange
        when(mProfileInfoInteractor.loadProfileInfo(Mockito.anyString())).thenThrow(new LoadProfileException("message", new Throwable()));
        String errorLoadingProfileInfo = "errorLoadingPofileInfo";
        when(mResourceWrapper.getString(R.string.error_loading_profile)).thenReturn(errorLoadingProfileInfo);

        // act
        mViewModel.loadProfile(Mockito.anyString());

        // assert
        assertThat(mViewModel.getErrors().getValue(), is(errorLoadingProfileInfo));
        assertThat(mViewModel.isLoading().getValue(), is(false));
    }

    @Test
    public void testLoadAlbumPhoto_happyCase() throws LoadAlbumPhotosException {
        // arrange
        when(mProfileInfoInteractor.loadAlbumPhotos(Mockito.anyString(), Mockito.anyString())).thenReturn(mAlbumPhotoList);
        List<AlbumPhoto> expectedAlbumPhoto = new ArrayList<>(mAlbumPhotoList);
        // act
        mViewModel.loadAlbumPhoto(Mockito.anyString(), Mockito.anyString());
        // assert
        assertThat(mViewModel.getAlbum().getValue(), is(expectedAlbumPhoto));
    }

    @Test
    public void testLoadAlbumPhoto_interactorThrowsException() throws LoadAlbumPhotosException {
        // arrange
        when(mProfileInfoInteractor.loadAlbumPhotos(Mockito.anyString(), Mockito.anyString())).thenThrow(new LoadAlbumPhotosException("message", new Throwable()));
        String errorLoadingAlbumPhoto = "errorLoadingAlbumPhoto";
        when(mResourceWrapper.getString(R.string.error_loading_album)).thenReturn(errorLoadingAlbumPhoto);

        // act
        mViewModel.loadAlbumPhoto(Mockito.anyString(), Mockito.anyString());

        // assert
        assertThat(mViewModel.getErrors().getValue(), is(errorLoadingAlbumPhoto));
        assertThat(mViewModel.isLoading().getValue(), is(false));
    }
}
