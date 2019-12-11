package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.AlbumPhoto;
import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;
import java.util.List;


public interface IProfileRepository {
    @NonNull
    Profile loadProfileInfo() throws IOException;

    @NonNull
    List<Profile> loadFriendsList() throws IOException;

    @NonNull
    List<AlbumPhoto> loadAlbumPhotos(String userId) throws IOException;
}
