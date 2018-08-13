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

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class RecipeOneUpActivity extends FragmentActivity implements Fragment_StepsFragment.setDetailFragment, Fragment_DetailFragment.incrementDetailFragment, Fragment_DetailFragment.returnToSteps {

    private SingleRecipe mSingleRecipe;
    public TextView ingredientView;
    public FragmentManager mFragmentManager;
    private boolean isTablet;

    // Bundle Args
    private static final String RECIPE_KEY = "A01";
    public static final String STEP_TAG = "step";
    public static final String DETAIL_TAG = "detail";

    Fragment_StepsFragment stepsFragment;
    Fragment_DetailFragment detailFragment;
    int FRAGMENT_STEP_FRAME;
    int FRAGMENT_DETAIL_FRAME;
    public int CURRENT_STEP = 0;

    public RecipeOneUpActivity(){
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);

        isTablet = getResources().getBoolean(R.bool.isTablet);
        Log.d("<><><><>", "is tablet: " + Boolean.toString(isTablet));

        //Initialize Views
        ingredientView = findViewById(R.id.ingredients_tv);
        if(isTablet && getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            FRAGMENT_STEP_FRAME = R.id.fragment;
            //FRAGMENT_DETAIL_FRAME = R.id.fragment_alt;
            FRAGMENT_DETAIL_FRAME = (R.id.fragment_alt == 0) ? R.id.fragment : R.id.fragment_alt;
        } else {
            FRAGMENT_DETAIL_FRAME = R.id.fragment;
            FRAGMENT_STEP_FRAME = R.id.fragment;
        }

        // Get Recipe from Intent
        Intent intent = getIntent();
        mSingleRecipe = intent.getParcelableExtra(RECIPE_KEY);

        if(savedInstanceState == null) {
            Bundle args = new Bundle();
            args.putParcelable(RECIPE_KEY, mSingleRecipe);

            // Create Fragments
            mFragmentManager = getSupportFragmentManager();
            stepsFragment = new Fragment_StepsFragment();
            stepsFragment.setArguments(args);
            mFragmentManager.beginTransaction()
                    .add(FRAGMENT_STEP_FRAME, stepsFragment, STEP_TAG)
                    .commit();
        } else {
            stepsFragment = (Fragment_StepsFragment) getSupportFragmentManager()
                    .findFragmentByTag(STEP_TAG);
            detailFragment = (Fragment_DetailFragment) getSupportFragmentManager()
                    .findFragmentByTag(DETAIL_TAG);
        }


    }

    @Override
    public void setDetailFragment(int step) {
        // Save Current Step
        CURRENT_STEP = step;
        // Update Detail Fragment
        Log.d("<><><><><>", "Updating fragment");
        Fragment_DetailFragment detailFragment = new Fragment_DetailFragment();
        // Create argument bundle
        Bundle args = new Bundle();
        args.putParcelable(RECIPE_KEY, mSingleRecipe.getStep(step));
        detailFragment.setArguments(args);
        mFragmentManager.beginTransaction()
                .replace(FRAGMENT_DETAIL_FRAME, detailFragment, DETAIL_TAG)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void incrementDetailFragment() {
        setDetailFragment(CURRENT_STEP + 1);
    }

    @Override
    public void returnToSteps() {
        mFragmentManager.beginTransaction()
                .replace(FRAGMENT_STEP_FRAME, stepsFragment, STEP_TAG)
                .addToBackStack(null)
                .commit();
    }


}
