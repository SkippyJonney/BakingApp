package com.example.jonathan.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jonathan.bakingapp.Adapters.RecipeAdapter;
import com.example.jonathan.bakingapp.R;
import com.example.jonathan.bakingapp.Utility.BakingWidget;
import com.example.jonathan.bakingapp.Utility.PantryIO;
import com.example.jonathan.bakingapp.Utility.SimpleIdlingResource;

import javax.annotation.Nullable;


public class MainActivity extends AppCompatActivity {

    // Recycler View
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecipeAdapter.ClickListener listener;
    // Bundle Args
    private static final String RECIPE_KEY = "A01";
    private PantryIO pantryIO;
    // Will be null in production
    @Nullable
    private SimpleIdlingResource mIdlingResource;
    @VisibleForTesting
    public IdlingResource getIdlingResource() {
        if(mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list);

        if (savedInstanceState != null) {
            // recover anything?
        }

        // get idling resource
        getIdlingResource();

        // Setup Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.bakingList_rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // specify adapter
        RecipeAdapter.ClickListener listener = new RecipeAdapter.ClickListener() {
            @Override
            public void onListItemClick(int clickedIndex) {
                updateWidget(pantryIO.getRecipe(clickedIndex).getIngredientListing());
                sendIntent(clickedIndex);
            }
        };
        mAdapter = new RecipeAdapter(listener, this);

        // Moved recipe request to onStart
        mIdlingResource.setIdleState(false);

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Request Recipes
        pantryIO = new PantryIO(this, getString(R.string.bakingDataURL), mAdapter, mRecyclerView, mIdlingResource);

    }

    public void sendIntent(int recipeIndex) {
        Intent stepsIntent = new Intent(this, RecipeOneUpActivity.class);
        stepsIntent.putExtra(RECIPE_KEY, pantryIO.getRecipe(recipeIndex));
        startActivity(stepsIntent);
    }


    public void updateWidget(String ingredients) {
        // Send ingredient string to widget.
        Intent widgetIntent = new Intent(this, BakingWidget.class);
        widgetIntent.setAction("UPDATE");
        widgetIntent.putExtra("update", ingredients);
        sendBroadcast(widgetIntent);
        Log.d("<><><><><>", "broadcasting");
    }




}
