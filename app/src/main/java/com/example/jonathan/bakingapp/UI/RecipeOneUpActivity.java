package com.example.jonathan.bakingapp.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.DummyData;
import com.example.jonathan.bakingapp.R;

public class RecipeOneUpActivity extends FragmentActivity {

    private ListView stepsListView;
    private int selectedIndex;
    public TextView ingredientView;

    // Using Fragments show Steps in a list view

    public RecipeOneUpActivity(){

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe_steps);

        //Initialize Ingredient View
        ingredientView = findViewById(R.id.ingredients_tv);

        //if(savedInstanceState == null) {
            Intent intent = getIntent();
            selectedIndex = intent.getIntExtra("index",0);

            // Create Fragments
            FragmentManager fragmentManager = getSupportFragmentManager();
            Recipe_DetailFragment stepsFragment = new Recipe_DetailFragment();
            stepsFragment.setListIndex(selectedIndex);
            stepsFragment.setIngredientView(ingredientView);
            fragmentManager.beginTransaction()
                    .add(R.id.fragment, stepsFragment)
                    .commit();
        //}

    }
}
