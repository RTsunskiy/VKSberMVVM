package com.example.vksbermvvm.interractorTests;

import com.example.vksbermvvm.domain.model.IProfileRepository;
import com.example.vksbermvvm.domain.model.LoadAlbumPhotosException;
import com.example.vksbermvvm.domain.model.LoadFriendsException;
import com.example.vksbermvvm.domain.model.LoadGroupsExceprtion;
import com.example.vksbermvvm.domain.model.LoadProfileException;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.domain.model.model.Profile;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Юнит тесты интеррактора {@link ProfileInfoInteractor}
 *
 * @author Цунский Роман on 2019-12-15
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileInteractorTest {

    private static String TOKEN = "Token";
    private String USER_ID = "UserId";

    @Mock
    private IProfileRepository mProfileRepository;

    private ProfileInfoInteractor mProfileInfoInterractor;

    private List<Profile> mFriendsList;

    private List<AlbumPhoto> mAlbumPhotoList;

    private List<Group> mGroupList;

    @Before
    public void setUp() throws Exception {
        mProfileInfoInterractor = new ProfileInfoInteractor(mProfileRepository);
    }

    @Test
    public void testLoadProfileInfo() throws Exception, LoadProfileException {
        //arrange
        when(mProfileRepository.loadProfileInfo(TOKEN)).thenReturn(createTestProfile());
        Profile actualResult = mProfileInfoInterractor.loadProfileInfo(TOKEN);
        //assert
        assertThat(actualResult, Matchers.is(createTestProfile()));
        verify(mProfileRepository).loadProfileInfo(TOKEN);
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test (expected = LoadProfileException.class)
    public void testLoadProfileInfo_throwsLoadProfileException() throws IOException, LoadProfileException {
        //arrange
        when(mProfileRepository.loadProfileInfo(TOKEN)).thenThrow(new IOException());
        //act
        mProfileInfoInterractor.loadProfileInfo(TOKEN);
        //assert
        verify(mProfileRepository.loadProfileInfo(TOKEN));
        verifyNoMoreInteractions(mProfileRepository);
    }

    private Profile createTestProfile() {
        return new Profile("Vasya",
                "Ivanov",
                "12.12.2000",
                "Toronto",
                "Canada",
                "url",
                12345);
    }

    @Test
    public void testLoadFriendsListInfo() throws Exception, LoadFriendsException {
        //arrange
        when(mProfileRepository.loadFriendsList(TOKEN)).thenReturn(mFriendsList);
        List<Profile> actualResult = mProfileInfoInterractor.loadFriendsList(TOKEN);
        //assert
        assertThat(actualResult, Matchers.is(mFriendsList));
        verify(mProfileRepository).loadFriendsList(TOKEN);
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test (expected = LoadFriendsException.class)
    public void testLoadFriendsList_throwsLoadFriendsException() throws IOException, LoadFriendsException {
        //arrange
        when(mProfileRepository.loadFriendsList(TOKEN)).thenThrow(new IOException());
        //act
        mProfileInfoInterractor.loadFriendsList(TOKEN);
        //assert
        verify(mProfileRepository.loadFriendsList(TOKEN));
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test
    public void testLoadAlbumPhotosInfo() throws Exception, LoadAlbumPhotosException {
        //arrange
        when(mProfileRepository.loadAlbumPhotos(TOKEN, USER_ID)).thenReturn(mAlbumPhotoList);
        List<AlbumPhoto> actualResult = mProfileInfoInterractor.loadAlbumPhotos(TOKEN, USER_ID);
        //assert
        assertThat(actualResult, Matchers.is(mAlbumPhotoList));
        verify(mProfileRepository).loadAlbumPhotos(TOKEN, USER_ID);
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test (expected = LoadAlbumPhotosException.class)
    public void testLoadAlbumPhoto_throwsLoadAlbumPhotoException() throws IOException, LoadAlbumPhotosException {
        //arrange
        when(mProfileRepository.loadAlbumPhotos(TOKEN, USER_ID)).thenThrow(new IOException());
        //act
        mProfileInfoInterractor.loadAlbumPhotos(TOKEN, USER_ID);
        //assert
        verify(mProfileRepository.loadAlbumPhotos(TOKEN, USER_ID));
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test
    public void testLoadGroupListInfo() throws Exception, LoadGroupsExceprtion {
        //arrange
        when(mProfileRepository.loadGroups(TOKEN)).thenReturn(mGroupList);
        List<Group> actualResult = mProfileInfoInterractor.loadGroups(TOKEN);
        //assert
        assertThat(actualResult, Matchers.is(mGroupList));
        verify(mProfileRepository).loadGroups(TOKEN);
        verifyNoMoreInteractions(mProfileRepository);
    }

    @Test (expected = LoadGroupsExceprtion.class)
    public void testLoadGroups_throwsLoadGroupsException() throws IOException, LoadGroupsExceprtion {
        //arrange
        when(mProfileRepository.loadGroups(TOKEN)).thenThrow(new IOException());
        //act
        mProfileInfoInterractor.loadGroups(TOKEN);
        //assert
        verify(mProfileRepository.loadGroups(TOKEN));
        verifyNoMoreInteractions(mProfileRepository);
    }
}
