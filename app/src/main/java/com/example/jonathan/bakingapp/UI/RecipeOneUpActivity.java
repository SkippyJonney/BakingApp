package com.example.jonathan.bakingapp.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.Data.Step;
import com.example.jonathan.bakingapp.R;

public class RecipeOneUpActivity extends FragmentActivity implements Fragment_StepsFragment.setDetailFragment {

    private SingleRecipe mSingleRecipe;
    public TextView ingredientView;

    // Bundle Args
    private static final String RECIPE_KEY = "A01";

    // Using Fragments show Steps in a list view

    public RecipeOneUpActivity(){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);

        //Initialize Ingredient View
        ingredientView = findViewById(R.id.ingredients_tv);

        // Get Recipe from Intent
        Intent intent = getIntent();
        mSingleRecipe = intent.getParcelableExtra(RECIPE_KEY);

        // TODO Implement Saving state
        // if(savedInstanceState == null) {
        //   ---- build Frgament
        // else restore fragment

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_KEY, mSingleRecipe);

        // Create Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment_StepsFragment stepsFragment = new Fragment_StepsFragment();
        stepsFragment.setArguments(args);
        fragmentManager.beginTransaction()
                .add(R.id.fragment, stepsFragment)
                .commit();

    }

    @Override
    public void setDetailFragment(Step step) {
        // Update Detail Fragment
        Log.d("<><><><><>", "Updating fragment");
    }
}
