package com.example.vksbermvvm.viewModelTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.SynchronousExecutor;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.LoadFriendsException;
import com.example.vksbermvvm.domain.model.LoadProfileException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Profile;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.FriendsListViewModel;

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

public class FriendsListViewModelTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private FriendsListViewModel mViewModel;
    private ProfileInfoInteractor mProfileInfoInteractor;
    private IResourceWrapper mResourceWrapper;
    private List<Profile> mProfileList;


    @Before
    public void setUp() {
        mProfileList = new ArrayList<>(Arrays.asList(mock(Profile.class), mock(Profile.class)));
        mProfileInfoInteractor = mock(ProfileInfoInteractor.class);
        mResourceWrapper = mock(IResourceWrapper.class);
        mViewModel = new FriendsListViewModel(
                new SynchronousExecutor(),
                mProfileInfoInteractor,
                mResourceWrapper
        );
    }

    @Test
    public void testLoadProfileInfo_happyCase() throws LoadFriendsException {
        // arrange
        when(mProfileInfoInteractor.loadFriendsList(Mockito.anyString())).thenReturn(mProfileList);
        List<Profile> expectedFriendList = new ArrayList<>(mProfileList);
        // act
        mViewModel.loadFriendsList(Mockito.anyString());
        // assert
        assertThat(mViewModel.getFriendsList().getValue(), is(expectedFriendList));
        assertThat(mViewModel.isLoading().getValue(), is(false));
    }

    @Test
    public void testLoadAlbumPhoto_interactorThrowsException() throws LoadFriendsException {
        // arrange
        when(mProfileInfoInteractor.loadFriendsList(Mockito.anyString())).thenThrow(new LoadFriendsException("message", new Throwable()));
        String errorLoadingFriends = "errorLoadingAlbumFriends";
        when(mResourceWrapper.getString(R.string.error_loading_friends)).thenReturn(errorLoadingFriends);

        // act
        mViewModel.loadFriendsList(Mockito.anyString());

        // assert
        assertThat(mViewModel.getErrors().getValue(), is(errorLoadingFriends));
    }
}
