package com.example.vksbermvvm.domain.model;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.Profile;

import java.io.IOException;


public interface IProfileRepository {
    @NonNull
    Profile loadProfileInfo() throws IOException;
}
