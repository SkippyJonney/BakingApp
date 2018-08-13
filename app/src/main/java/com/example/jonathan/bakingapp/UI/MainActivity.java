package com.example.jonathan.bakingapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.jonathan.bakingapp.Adapters.RecipeAdapter;
import com.example.jonathan.bakingapp.Data.DummyData;
import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.R;
import com.example.jonathan.bakingapp.Utility.BakingWidget;
import com.example.jonathan.bakingapp.Utility.PantryIO;


public class MainActivity extends AppCompatActivity {

    // Recycler View
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecipeAdapter.ClickListener listener;
    // Bundle Args
    private static final String RECIPE_KEY = "A01";
    private PantryIO pantryIO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baking_list);

        if (savedInstanceState != null) {
            // recover anything?
        }

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

        // Request Recipes
        pantryIO = new PantryIO(this, getString(R.string.bakingDataURL), mAdapter, mRecyclerView);

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
