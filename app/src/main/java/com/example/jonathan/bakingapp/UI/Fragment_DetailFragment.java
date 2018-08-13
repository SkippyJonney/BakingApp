package com.example.jonathan.bakingapp.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.Step;
import com.example.jonathan.bakingapp.R;
import com.example.jonathan.bakingapp.Utility.mExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class Fragment_DetailFragment extends Fragment {

    // Bundle Args
    private static final String RECIPE_KEY = "A01";
    public static final String TAG = "detail";

    private Step mStep;
    public TextView mStepDescription;
    public mExoPlayer exoPlayer;
    private incrementDetailFragment mIncrementDetailFragment;
    private returnToSteps mReturnToSteps;

    // Mandatory FragmentManager Constructor
    public Fragment_DetailFragment() {}

    // Get Bundle
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            mStep = getArguments().getParcelable(RECIPE_KEY);
        }
    }

    // Inflate Layout
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Retain fragment
        setRetainInstance(true);

        // Resume State
        if(savedInstanceState != null) {
            mStep = savedInstanceState.getParcelable(RECIPE_KEY);
        }

        // Build view
        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        mStepDescription = rootView.findViewById(R.id.F_stepDescription);
        Log.d("<><><><><>", mStep.getDescription());
        mStepDescription.setText("Hello");

        // Set views
        mStepDescription.setText(mStep.getDescription());
        //Picasso.with(rootView.getContext()).load(mStep.getThumbnailURL()).into(mStepImage);

        // Initialize ExoPlayer
        PlayerView playerView = rootView.findViewById(R.id.F_stepExoPlayer);
        Uri uri = Uri.parse(mStep.getVideoUrl());
        Log.d("<><><><><>", uri.toString());
        exoPlayer = new mExoPlayer(this.getContext(), playerView, uri);

        // Implement FAB if exists
        if(!getResources().getBoolean(R.bool.isTablet)) {
            // Setup Fab
            FloatingActionButton nextFab = (FloatingActionButton) rootView.findViewById(R.id.fabNext);
            nextFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Next Step
                    IncrementStep();
                }
            });

            FloatingActionButton menuFab = (FloatingActionButton) rootView.findViewById(R.id.fabMenu);
            menuFab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // Menu Now
                    callReturnToSteps();
                }
            });
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putParcelable(RECIPE_KEY, mStep);
    }

    public interface incrementDetailFragment {
        public void incrementDetailFragment();
    }
    public interface returnToSteps {
        public void returnToSteps();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIncrementDetailFragment = (incrementDetailFragment) context;
        mReturnToSteps = (returnToSteps) context;
    }
    public void IncrementStep() {
        mIncrementDetailFragment.incrementDetailFragment();
    }
    public void callReturnToSteps() {
        mReturnToSteps.returnToSteps();
    }


}
