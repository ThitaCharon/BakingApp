package com.example.thita.bakingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thita.bakingapp.Model.Ingredient;
import com.example.thita.bakingapp.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private List<Ingredient> mIgredientsList;
    private Context mContext;

    public IngredientsAdapter(Context context, List<Ingredient> ingredientList){
        mContext = context;
        mIgredientsList = ingredientList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_ingredient,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Double quantity = (mIgredientsList.get(position).getQuantity());
        holder.quantity.setText(quantity.toString());
        holder.unit.setText(mIgredientsList.get(position).getMeasure());
        holder.ingredients.setText(mIgredientsList.get(position).getIngredient());
    }

    @Override
    public int getItemCount() {
        if(mIgredientsList != null){
            return mIgredientsList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView quantity;
        private TextView unit;
        private TextView ingredients;

       public ViewHolder(View view){
           super(view);
           quantity = view.findViewById(R.id.row_item_ingredient_numeric_tv);
           unit = view.findViewById(R.id.row_item_ingredient_unit_tv);
           ingredients = view.findViewById(R.id.row_item_ingredient_tv);
       }
    }
}
