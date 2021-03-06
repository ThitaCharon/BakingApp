package com.example.thita.bakingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends ArrayAdapter {

    public List<Recipe> recipeList = new ArrayList<>();
    private Context mContext;
    private int mResourceId;

    private static class ViewHolder {
        ImageView image;
        TextView  title;
    }

    public RecipeAdapter(List<Recipe> data, int resourceId , Context context){
        super(context, resourceId, data);
        recipeList = data;
        mContext = context;
        mResourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipetarger = (Recipe) getItem(position);
        ViewHolder myViewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_recipe,parent,false);
            myViewHolder = new ViewHolder();
            myViewHolder.image = (ImageView) convertView.findViewById(R.id.recipe_menu_image);
            myViewHolder.title = (TextView) convertView.findViewById(R.id.recipe_name_menu);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        myViewHolder.image.setImageResource(R.drawable.ic_chesscake);
        final int id = recipetarger.getId();
        if (id == 1 ){
            myViewHolder.image.setImageResource(R.drawable.ic_nutella_pie);
        }else if (id == 2){
            myViewHolder.image.setImageResource(R.drawable.ic_brownies);
        }else if (id == 3){
            myViewHolder.image.setImageResource(R.drawable.ic_yollow_cake);
        }else if (id == 4){
            myViewHolder.image.setImageResource(R.drawable.ic_chesscake);
        }
        myViewHolder.title.setText(recipetarger.getName());
        return convertView;
    }
}



































