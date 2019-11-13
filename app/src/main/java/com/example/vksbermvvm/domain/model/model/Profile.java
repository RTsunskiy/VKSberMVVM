package com.example.vksbermvvm.domain.model.model;

import androidx.annotation.NonNull;


import java.util.Objects;

public class Profile {
        private final String mFirstName;
        private final String mLastName;


        public Profile(@NonNull String firstName, @NonNull String lastName) {
            mFirstName = firstName;
            mLastName = lastName;
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

    }


