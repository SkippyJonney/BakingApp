package com.example.jonathan.bakingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathan.bakingapp.Data.SingleRecipe;
import com.example.jonathan.bakingapp.Data.Step;
import com.example.jonathan.bakingapp.R;

import java.util.ArrayList;

public class RecipeStepAdapter extends ArrayAdapter<Step> {
    private final Context context;
    private ArrayList<Step> steps;


    public RecipeStepAdapter(Context context, ArrayList<Step> steps) {
        super(context, R.layout.cardview_recipe_step, steps);
        this.context = context;
        this.steps = steps;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cardview_recipe_step, parent, false);
        TextView title = rowView.findViewById(R.id.step_id_tv);
        TextView description = rowView.findViewById(R.id.step_description_tv);
        ImageView thumbnail = rowView.findViewById(R.id.step_imgIcon_iv);

        title.setText(steps.get(position).getId());
        description.setText(steps.get(position).getShortDescription());

        return rowView;
    }
}
