package com.example.jonathan.bakingapp.Data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;


public class SingleRecipe implements Parcelable {

    private int ID;
    private String Name;
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private ArrayList<Step> steps = new ArrayList<Step>();
    private int Servings;
    private String ImgUrl;

    public String getName() {
        return Name;
    }
    public String getImgUrl() { return ImgUrl;}
    public String getServings() { return Integer.toString(Servings); }

    // Ingredient Interface
    public int getIngredientLength() { return ingredients.size(); }
    public String getIngredient(int index) {
        return ingredients.get(index).getListing();
    }
    public Step getStep(int position) {
        // Get Maximum Size of Array and trim to there
        int MaxPosition = steps.size() - 1;
        if(position > MaxPosition) {
            position = MaxPosition;
            return steps.get(position);
        }
        else {
            return steps.get(position);
        }
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }

    // Constructor without Ingredients and Steps -- add through accessors
    public SingleRecipe(int ID, String name, int servings, String imgUrl) {
        this.ID = ID;
        Name = name;
        Servings = servings;
        ImgUrl = imgUrl;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    public void addStep(Step step) {
        steps.add(step);
    }

    private SingleRecipe(Parcel in) {
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
        steps = in.createTypedArrayList(Step.CREATOR);
        //in.readTypedList(ingredients, Ingredient.CREATOR);
        //in.readTypedList(steps, Step.CREATOR);
        ID = in.readInt();
        Name = in.readString();
        ImgUrl = in.readString();
        Servings = in.readInt();
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeTypedList(ingredients);
        out.writeTypedList(steps);
        out.writeInt(ID);
        out.writeString(Name);
        out.writeString(ImgUrl);
        out.writeInt(Servings);
    }

    // Parcelable Boilerplate
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

}
