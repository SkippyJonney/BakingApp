package com.example.jonathan.bakingapp.UI;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Adapters.RecipeStepAdapter;
import com.example.jonathan.bakingapp.Data.Ingredient;
import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.Data.Step;
import com.example.jonathan.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_StepsFragment extends Fragment{

    private int mSelectedIndex;
    private SingleRecipe mSingleRecipe;
    public TextView ingredientView;
    private ListView mListView;
    private RecipeStepAdapter mStepAdapter;
    private View rootView;
    private setDetailFragment mSetDetailFragment;
    // Bundle Args
    private static final String RECIPE_KEY = "A01";

    // Mandatory for fragment manager
    public Fragment_StepsFragment() { }

    // Get Recipe Bundle
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mSingleRecipe = getArguments().getParcelable(RECIPE_KEY);
        }
    }

    // Inflate layout file and set resources
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //Retain this fragment
        setRetainInstance(true);

        // Resume State
        if(savedInstanceState != null) {
            mSelectedIndex = savedInstanceState.getInt("index");
            mSingleRecipe = savedInstanceState.getParcelable(RECIPE_KEY);
        }

        rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);
        mListView = (ListView) rootView.findViewById(R.id.steps_lv);
        ingredientView = rootView.findViewById(R.id.ingredients_tv);

        ingredientView.setText("Hello");
        Log.d("<><><><>", Integer.toString(mSingleRecipe.getIngredientLength()));
        // Fill Adapter
        //RecipeStepAdapter mAdapter = new RecipeStepAdapter(listView.getContext(), mSingleRecipe.getSteps());
        //listView.setAdapter(mAdapter);

        StringBuilder ingredientTxT = new StringBuilder("");
        Log.d("<><><><>", Integer.toString(mSingleRecipe.getIngredientLength()));
        for(int i = 0; i < mSingleRecipe.getIngredientLength(); i++) {
            ingredientTxT.append(mSingleRecipe.getIngredient(i));
            ingredientTxT.append(System.getProperty("line.separator"));
        }
        ingredientView.setText(ingredientTxT.toString());

        //ingredientView.setText("Hello");
        // Fill Adapter
        mStepAdapter = new RecipeStepAdapter(mListView.getContext(), mSingleRecipe.getSteps());
        mListView.setAdapter(mStepAdapter);



        // Setup Click Listener
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    // Save Fragment State
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putInt("index", mSelectedIndex);
        currentState.putParcelable(RECIPE_KEY, mSingleRecipe);
    }

    public interface setDetailFragment {
        public void setDetailFragment(Step step);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mSetDetailFragment = (setDetailFragment) context;
    }

    public void SetDetailFragment(Step step) {
        mSetDetailFragment.setDetailFragment(step);
    }
}
