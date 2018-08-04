package com.example.jonathan.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {
    int ID;
    String shortDescription;
    String Description;
    String videoUrl;
    String thumbnailURL;

    public Step(int ID, String shortDescription, String description, String videoUrl, String thumbnailURL) {
        this.ID = ID;
        this.shortDescription = shortDescription;
        Description = description;
        this.videoUrl = videoUrl;
        this.thumbnailURL = thumbnailURL;
    }

    public String getId() {
        return Integer.toString(ID);
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return Description;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    // Parcelable Logic

    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelable Creator, generates from parcel using provided data.
    public static final Parcelable.Creator<Step> CREATOR = new Parcelable.Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel parcel) {
            return new Step(parcel);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[0];
        }
    };


    private Step(Parcel in) {
        ID = in.readInt();
        shortDescription = in.readString();
        Description = in.readString();
        videoUrl = in.readString();
        thumbnailURL = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(shortDescription);
        dest.writeString(Description);
        dest.writeString(videoUrl);
        dest.writeString(thumbnailURL);
    }
}
