package com.example.vksbermvvm.viewModelTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.SynchronousExecutor;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.FriendProfileViewModel;

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

public class FriendProfileViewModelTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private FriendProfileViewModel mViewModel;
    private ProfileInfoInteractor mProfileInfoInteractor;
    private IResourceWrapper mResourceWrapper;
    private List<AlbumPhoto> mAlbumPhotoList;
    private AlbumPhoto mAlbumPhoto;

    @Before
    public void setUp() {
        mAlbumPhotoList = new ArrayList<>(Arrays.asList(mock(AlbumPhoto.class), mock(AlbumPhoto.class)));
        mProfileInfoInteractor = mock(ProfileInfoInteractor.class);
        mResourceWrapper = mock(IResourceWrapper.class);
        mViewModel = new FriendProfileViewModel(
                new SynchronousExecutor(),
                mProfileInfoInteractor,
                mResourceWrapper
        );
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
    }
}
