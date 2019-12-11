package com.example.vksbermvvm.presentation;

import androidx.annotation.NonNull;

import com.example.vksbermvvm.domain.model.model.Profile;

public interface OnFriendClickListener {
    void onItemClick(@NonNull Profile profile);
}
