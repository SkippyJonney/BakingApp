package com.example.jonathan.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    String quantity;
    String measure;
    String ingredient;

    public Ingredient(String quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }
    public String getMeasure() {
        return measure;
    }
    public String getIngredient() {
        return ingredient;
    }

    public String getListing() {
        return quantity + " " + measure + " " + ingredient;
    }

    // Parcelable Logic
    @Override
    public int describeContents() {
        return 0;
    }

    // Parcelable Creator, generates from parcel using provided data.
    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[0];
        }
    };

    private Ingredient(Parcel in) {
        quantity = in.readString();
        measure = in.readString();
        ingredient = in.readString();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(quantity);
        out.writeString(measure);
        out.writeString(ingredient);
    }
}
