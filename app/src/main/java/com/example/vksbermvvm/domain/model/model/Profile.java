package com.example.vksbermvvm.domain.model.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


import java.util.Objects;

/**
 * POJO объект, в котором хранится информация о профиле пользователя
 */

public class Profile implements Parcelable {
    /**имя владельца профиля*/
    private final String mFirstName;
    /**фамилия владельца профиля*/
    private final String mLastName;
    /**дата рождения владельца профиля*/
    private final String mDate;
    /**город, в котором проживает владелец профиля*/
    private final String mCity;
    /**страна, в которой проживает владелец профиля*/
    private final String mCountry;
    /**id профиля*/
    private final int mId;


    private final String mProfileImage;


    public Profile(@NonNull String firstName,
                   @NonNull String lastName,
                   @NonNull String bDate,
                   @NonNull String city,
                   @NonNull String country,
                   @NonNull String profileImage,
                   @NonNull int id) {
        mFirstName = firstName;
        mLastName = lastName;
        mDate = bDate;
        mCity = city;
        mCountry = country;
        mProfileImage = profileImage;
        mId = id;
    }

    protected Profile(Parcel in) {
        mFirstName = in.readString();
        mLastName = in.readString();
        mDate = in.readString();
        mCity = in.readString();
        mCountry = in.readString();
        mId = in.readInt();
        mProfileImage = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel in) {
            return new Profile(in);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };

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

    public int getmId() {
        return mId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mFirstName);
        dest.writeString(mDate);
        dest.writeString(mLastName);
        dest.writeString(mCity);
        dest.writeString(mCountry);
        dest.writeInt(mId);
    }
}


