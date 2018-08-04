package com.example.jonathan.bakingapp.UI;

import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Adapters.RecipeStepAdapter;
import com.example.jonathan.bakingapp.Data.DummyData;
import com.example.jonathan.bakingapp.Data.Ingredient;
import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.Data.Step;
import com.example.jonathan.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class Recipe_DetailFragment extends Fragment{

    private int mSelectedIndex;
    private String mRecipeName;
    private SingleRecipe currentRecipe;
    private TextView ingredientView;

    public void setIngredientView(TextView view) {
        ingredientView = view;
    }


    //Logging Tag
    private static final String TAG = "Recipe Fragment";

    // Mandatory for fragment manager
    public Recipe_DetailFragment() { }

    // Inflate layout file and set resources
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //Retain this fragment
        setRetainInstance(true);

        if(savedInstanceState != null) {
            mSelectedIndex = savedInstanceState.getInt("index");
        }

        // Resume State
        if(savedInstanceState != null) {
            mSelectedIndex = savedInstanceState.getInt("index");
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.steps_lv);

        // TODO 40 Get step name and details, show video icon if available
        // *********************************
        // FOR DEBUG //
        List<String> arrayList = new ArrayList<String>();
            arrayList.add("Step 1: Measure");
            arrayList.add("Step 2: Mix");
            arrayList.add("Step 3: Bake");
            arrayList.add("Step 4: Enjoy");
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(listView.getContext(),R.layout.cardview_recipe_step,arrayList );
        currentRecipe = new SingleRecipe(0,"Cookies",20,"");
        currentRecipe.addIngredient(new Ingredient("12","C","Sugar"));
        currentRecipe.addIngredient(new Ingredient("3", "Gal", "Syrup"));
        currentRecipe.addStep(new Step(1,"Mix","Add sugar to mix","",""));
        currentRecipe.addStep(new Step(1,"Mix","Add sugar to mix","",""));
        currentRecipe.addStep(new Step(1,"Mix","Add sugar to mix","",""));
        currentRecipe.addStep(new Step(1,"Mix","Add sugar to mix","",""));
        // *********************************

        // Fill Ingredient List
        //TextView ingredientTV = (TextView) rootView.findViewById(R.id.ingredients_tv);
        StringBuilder ingredientTxT = new StringBuilder("");
        for( int i = 0; i < currentRecipe.getIngredientLength(); i++) {
            ingredientTxT.append(currentRecipe.getIngredient(i));
            ingredientTxT.append(System.getProperty("line.separator"));
        }
        ingredientView.setText(ingredientTxT.toString());

        // Fill Adapter
        RecipeStepAdapter mAdapter = new RecipeStepAdapter(listView.getContext(), currentRecipe.getSteps());
        listView.setAdapter(mAdapter);


        // Setup Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item text from ListView
                String selectedItem = (String) parent.getItemAtPosition(position);

                // Display the selected item text on TextView
                Log.d("<><><><><>","Your favorite : " + selectedItem);

                // TODO 42 Launch intent to show details view with video and details // next button
            }
        });


        // Fill Parent Params
        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        rootView.setLayoutParams(params);
        return rootView;
    }

    public void setListIndex(int index) {
        mSelectedIndex = index;
    }

    // Save Fragment State
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt("index", mSelectedIndex);

    }



}
