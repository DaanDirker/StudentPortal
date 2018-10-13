package com.example.daan.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class PortalObject implements Parcelable {

    private String title;
    private String url;

    public PortalObject(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    protected PortalObject(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Creator<PortalObject> CREATOR = new Creator<PortalObject>() {
        @Override
        public PortalObject createFromParcel(Parcel source) {
            return new PortalObject(source);
        }

        @Override
        public PortalObject[] newArray(int size) {
            return new PortalObject[size];
        }
    };
}
