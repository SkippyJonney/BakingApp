package com.example.jonathan.bakingapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecyclerViewHolder>  {


    // List of Recipes, need to change to a Custom Class
    private ArrayList<String> recipeList;
    private Context mContext;
    private ClickListener clickListener;


    // Interface for Handling Clicks
    public interface ClickListener {
        void onListItemClick(String clickedIndex);
    }

    public RecipeAdapter(ClickListener listener) {
        clickListener = listener;
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
        holder.rvText.setText(recipeList.get(position));
        String imgUrl = recipeList.get(position);

        // Load Image using Glide Library
        if(!imgUrl.equals("")) {
            Uri newUri = Uri.parse(imgUrl).buildUpon().build();

            Picasso.with(mContext).load(newUri).into(holder.rvImage);
        }
    }

    // View to Populate Recycler View With
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView rvText;
        ImageView rvImage;

        RecyclerViewHolder(View view) {
            super(view);

            rvText = view.findViewById(R.id.cardview_title);
            rvImage = view.findViewById(R.id.cardview_image);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            clickListener.onListItemClick(recipeList.get(clickedPosition));
        }

    }
}
