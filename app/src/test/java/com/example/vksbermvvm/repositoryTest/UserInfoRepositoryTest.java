package com.example.vksbermvvm.repositoryTest;

import com.example.vksbermvvm.data.JSONPlaceHolderApi;
import com.example.vksbermvvm.data.UserInfoRepository;
import com.example.vksbermvvm.data.modelAlbumPhotos.AlbumPhotos;
import com.example.vksbermvvm.data.modelFriends.Friends;
import com.example.vksbermvvm.data.modelGroups.Groups;
import com.example.vksbermvvm.data.modelProfile.ResponseExample;
import com.example.vksbermvvm.domain.model.model.Profile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Юнит тесты репозитория {@link UserInfoRepository}
 *
 * @author Цунский Роман on 2019-12-15
 */
public class UserInfoRepositoryTest {

    private UserInfoRepository mUserInfoRepository;
    private JSONPlaceHolderApi mJSONPlaceHolderApi;
    private Profile mProfile;

    @Before
    public void setUp() throws Exception {
        mJSONPlaceHolderApi = mock(JSONPlaceHolderApi.class);
        mProfile = mock(Profile.class);
        mUserInfoRepository = new UserInfoRepository(mJSONPlaceHolderApi);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadProfileInfo_happyCase() throws IOException {
        //  arrange
        ResponseExample responseExample = mock(ResponseExample.class);
        Response<ResponseExample> response = Response.success(responseExample);
        Call<ResponseExample> call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        when(mJSONPlaceHolderApi.getUserInfo(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(call);
    }



    @Test
    @SuppressWarnings("unchecked")
    public void testLoadFriendsListInfo_happyCase() throws IOException {
//  arrange
        Friends friends = mock(Friends.class);
        Response<Friends> response = Response.success(friends);
        Call<Friends> call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        when(mJSONPlaceHolderApi.getFriends(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn(call);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadAlbumPhotoInfo_happyCase() throws IOException {
//  arrange
        AlbumPhotos albumPhotos = mock(AlbumPhotos.class);
        Response<AlbumPhotos> response = Response.success(albumPhotos);
        Call<AlbumPhotos> call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        when(mJSONPlaceHolderApi.getAlbumPhotos(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(call);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testLoadGroupsInfo_happyCase() throws IOException {
//  arrange
        Groups groups = mock(Groups.class);
        Response<Groups> response = Response.success(groups);
        Call<Groups> call = mock(Call.class);
        when(call.execute()).thenReturn(response);
        when(mJSONPlaceHolderApi.getGroups(Mockito.anyString(), Mockito.anyInt(),
                Mockito.anyString())).thenReturn(call);
    }


}
