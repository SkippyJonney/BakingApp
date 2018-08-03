package com.example.jonathan.bakingapp.UI;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.DummyData;
import com.example.jonathan.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class Recipe_DetailFragment extends Fragment{

    private int mSelectedIndex;
    private String mRecipeName;

    //Logging Tag
    private static final String TAG = "Recipe Fragment";

    // Mandatory for fragment manager
    public Recipe_DetailFragment() { }

    // Inflate layout file and set resources
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        // Resume State
        if(savedInstanceState != null) {
            mSelectedIndex = savedInstanceState.getInt("index");
        }

        View rootView = inflater.inflate(R.layout.fragment_recipe_step, container, false);

        final ListView listView = (ListView) rootView.findViewById(R.id.steps_lv);
        List<String> arrayList = new ArrayList<String>();
            arrayList.add("Step 1: Measure");
            arrayList.add("Step 2: Mix");
            arrayList.add("Step 3: Bake");
            arrayList.add("Step 4: Enjoy");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                listView.getContext(),
                R.layout.cardview_recipe_step,
                arrayList );
        listView.setAdapter(arrayAdapter);

        // *********************************
        // FOR DEBUG //
        DummyData testData = new DummyData();
        String recipeName = testData.getName(mSelectedIndex);
        // *********************************

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
