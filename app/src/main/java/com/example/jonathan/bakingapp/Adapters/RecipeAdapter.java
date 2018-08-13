package com.example.jonathan.bakingapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder>  {


    // List of Recipes, need to change to a Custom Class
    private ArrayList<SingleRecipe> recipeList;
    //private ArrayList<String> recipeList;
    private Context mContext;
    private ClickListener clickListener;

    public void setRecipeList(ArrayList<SingleRecipe> arrayIn) {
        recipeList = arrayIn;
    }

    // Interface for Handling Clicks
    public interface ClickListener {
        // for string
        //void onListItemClick(String clickedIndex);
        void onListItemClick(int clickedIndex);
    }

    public RecipeAdapter(ClickListener listener, Context context) {
        clickListener = listener;
        mContext = context;
    }


    // Required Count Implementation
    @Override
    public int getItemCount() {
        if( recipeList != null) {
            return recipeList.size();
        }
        return 0;
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_recipe_list, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        // Populate View
        Log.d("<><>TAG<>TAG<>TAG<>", "BINDING VIEW" + recipeList.get(position).getImgUrl());
        holder.rvTitle.setText(recipeList.get(position).getName());
        holder.rvServings.setText("Servings: " + recipeList.get(position).getServings());

        String imgUrl = recipeList.get(position).getImgUrl();

        Log.d("<><><><><>", "Check Null " + imgUrl);
        if(!imgUrl.isEmpty()) {
            Uri newUri = Uri.parse(imgUrl).buildUpon().build();
            Log.d("<><><><><>", "Not Null " + newUri.toString());
            Picasso.with(mContext).load(newUri).into(holder.rvImage);
        }
    }

    // View to Populate Recycler View With
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rvTitle;
        TextView rvServings;
        ImageView rvImage;

        RecyclerViewHolder(View view) {
            super(view);

            rvTitle = view.findViewById(R.id.cardview_title);
            rvImage = view.findViewById(R.id.cardview_image);
            rvServings = view.findViewById(R.id.cardView_servings);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            // Send String
            //clickListener.onListItemClick(recipeList.get(clickedPosition));
            clickListener.onListItemClick(clickedPosition);
        }

    }
}
