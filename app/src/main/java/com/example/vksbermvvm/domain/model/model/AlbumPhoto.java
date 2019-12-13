package com.example.vksbermvvm.domain.model.model;


import androidx.annotation.NonNull;

/**
 * POJO объект, в котором хранится информация о фотографии из альбома пользователя
 */

public class AlbumPhoto {
    /**
     * url фотографии
     */
    private final String mUrl;
    /**
     * тип фотографии (тип влияет на разрешение фотографии)
     */
    private final String mType;
    /**
     * id фотографии
     */
    private int mId;
    /**
     * id альбома, в котором хранится фотография
     */
    private int mAlbumId;

    public AlbumPhoto(@NonNull int mId,
                      @NonNull int mAlbumId,
                      @NonNull String mType,
                      @NonNull String mUrl) {
        this.mId = mId;
        this.mAlbumId = mAlbumId;
        this.mType = mType;
        this.mUrl = mUrl;
    }


    @Override
    public String toString() {
        return "AlbumPhoto{" +
                "mId=" + mId +
                ", mAlbumId=" + mAlbumId +
                ", mType='" + mType + '\'' +
                ", mUrl='" + mUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumPhoto that = (AlbumPhoto) o;

        if (mId != that.mId) return false;
        if (mAlbumId != that.mAlbumId) return false;
        if (mType != null ? !mType.equals(that.mType) : that.mType != null) return false;
        return mUrl != null ? mUrl.equals(that.mUrl) : that.mUrl == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + mAlbumId;
        result = 31 * result + (mType != null ? mType.hashCode() : 0);
        result = 31 * result + (mUrl != null ? mUrl.hashCode() : 0);
        return result;
    }

    public int getmId() {
        return mId;
    }

    public int getmAlbumId() {
        return mAlbumId;
    }

    public String getmType() {
        return mType;
    }

    public String getmUrl() {
        return mUrl;
    }
}
