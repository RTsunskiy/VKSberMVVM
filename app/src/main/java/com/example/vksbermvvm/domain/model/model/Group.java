package com.example.vksbermvvm.domain.model.model;

/**
 * POJO объект, в котором хранится информация о группе, на которую подписан пользователь
 */

public class Group {
    /**
     * Id группы
     */
    private int mId;
    /**
     * название группы
     */
    private String mName;
    /**
     * техническое название группы
     */
    private String mScreenName;
    /**
     * признак, что группа закрыта
     */
    private int mIsClosed;
    /**
     * url фотографии группы
     */
    private String mPhoto;

    public Group(int mId, String mName, String mScreenName, int mIsClosed, String mPhoto) {
        this.mId = mId;
        this.mName = mName;
        this.mScreenName = mScreenName;
        this.mIsClosed = mIsClosed;
        this.mPhoto = mPhoto;
    }

    public int getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmScreenName() {
        return mScreenName;
    }

    public int getmIsClosed() {
        return mIsClosed;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (mId != group.mId) return false;
        if (mIsClosed != group.mIsClosed) return false;
        if (mName != null ? !mName.equals(group.mName) : group.mName != null) return false;
        if (mScreenName != null ? !mScreenName.equals(group.mScreenName) : group.mScreenName != null)
            return false;
        return mPhoto != null ? mPhoto.equals(group.mPhoto) : group.mPhoto == null;
    }

    @Override
    public int hashCode() {
        int result = mId;
        result = 31 * result + (mName != null ? mName.hashCode() : 0);
        result = 31 * result + (mScreenName != null ? mScreenName.hashCode() : 0);
        result = 31 * result + mIsClosed;
        result = 31 * result + (mPhoto != null ? mPhoto.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mScreenName='" + mScreenName + '\'' +
                ", mIsClosed=" + mIsClosed +
                ", mPhoto='" + mPhoto + '\'' +
                '}';
    }
}
