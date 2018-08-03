package com.example.jonathan.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;


public class SingleRecipe implements Parcelable {

    private int ID;
    private String Name;
    private String ImgUrl;
    private String[] Ingredients;
    private String[] Steps;
    private int Servings;

    private SingleRecipe(Parcel in) {
        ID = in.readInt();
        Name = in.readString();
        ImgUrl = in.readString();
        Ingredients = in.createStringArray();
        Steps = in.createStringArray();
        Servings = in.readInt();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(ID);
        out.writeString(Name);
        out.writeString(ImgUrl);
        out.writeStringArray(Ingredients);
        out.writeStringArray(Steps);
        out.writeInt(Servings);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelable Creator, generates from parcel using provided data.
    public static final Parcelable.Creator<SingleRecipe> CREATOR = new Parcelable.Creator<SingleRecipe>() {
        @Override
        public SingleRecipe createFromParcel(Parcel parcel) {
            return new SingleRecipe(parcel);
        }

        @Override
        public SingleRecipe[] newArray(int size) {
            return new SingleRecipe[0];
        }
    };
































    // Mutator && Accessors
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String[] getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String[] ingredients) {
        Ingredients = ingredients;
    }

    public String[] getSteps() {
        return Steps;
    }

    public void setSteps(String[] steps) {
        Steps = steps;
    }

    public int getServings() {
        return Servings;
    }

    public void setServings(int servings) {
        Servings = servings;
    }
}
