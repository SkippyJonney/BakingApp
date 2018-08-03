package com.example.jonathan.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jonathan.bakingapp.Adapters.RecipeAdapter;
import com.example.jonathan.bakingapp.Data.DummyData;
import com.example.jonathan.bakingapp.R;

import java.util.ArrayList;

public class RecipeMainActivity  extends AppCompatActivity {

    String selectedRecipe;
    boolean isTablet;
    private RecipeAdapter.ClickListener listener;


    // Recycler View
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list);

        // Check for tablet
        isTablet = getResources().getBoolean(R.bool.isTablet);

        if (savedInstanceState != null) {
            selectedRecipe = savedInstanceState.getString("Title");
        }

        // Setup Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.bakingList_rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify adapter
        RecipeAdapter.ClickListener listener = new RecipeAdapter.ClickListener() {
            @Override
            public void onListItemClick(int clickedIndex) {
                Log.d("<><><><><>", "Clicked " + clickedIndex + " on a " + isTablet);
                // TODO Implement Intent to receive recipe detail
                //  CASE 1: Phone -- Launch recipe_steps
                //  CASE 2: Tablet -- Launch recipe_master_detail
                sendIntent(clickedIndex);
            }
        };
        mAdapter = new RecipeAdapter(listener, this);
        // *********************************
        // FOR DEBUG //
        DummyData testData = new DummyData();
        mAdapter.setRecipeList(testData.getData());
        // *********************************
        mRecyclerView.setAdapter(mAdapter);
    }

    public void sendIntent(int recipeIndex) {
        Intent stepsIntent = new Intent(this, RecipeOneUpActivity.class);
        stepsIntent.putExtra("index", recipeIndex);
        startActivity(stepsIntent);
    }


}
