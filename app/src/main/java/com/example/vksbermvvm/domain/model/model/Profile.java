package com.example.vksbermvvm.domain.model.model;

import androidx.annotation.NonNull;


import java.util.Objects;

public class Profile {
    private final String mFirstName;
    private final String mLastName;
    private final String mDate;
    private final String mCity;
    private final String mCountry;


    private final String mProfileImage;


    public Profile(@NonNull String firstName,
                   @NonNull String lastName,
                   @NonNull String bDate,
                   @NonNull String city,
                   @NonNull String country,
                   @NonNull String profileImage) {
        mFirstName = firstName;
        mLastName = lastName;
        mDate = bDate;
        mCity = city;
        mCountry = country;
        mProfileImage = profileImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Profile currency = (Profile) o;
        return mLastName == currency.mLastName &&
                mFirstName.equals(currency.mFirstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mFirstName, mLastName);
    }

    @NonNull
    public String getmFirstName() {
        return mFirstName;
    }

    @NonNull
    public String getmLastName() {
        return mLastName;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmProfileImage() {
        return mProfileImage;
    }

    public String getmCountry() {
        return mCountry;
    }
}


