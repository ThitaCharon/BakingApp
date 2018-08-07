package com.example.thita.bakingapp.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thita.bakingapp.Model.Recipe;
import com.example.thita.bakingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuRVAdapter extends RecyclerView.Adapter<MenuRVAdapter.ViewHolder> {

    protected static ItemClickedListener mItemListener;
    public List<Recipe> mRecipelist;
    private Context mContext;

    public MenuRVAdapter(Context context,List<Recipe> recipeList) {
        mContext = context;
        mRecipelist = recipeList;
    }

    /*
    public MenuRVAdapter(Context context, ItemClickedListener listener, List<Recipe> recipeList) {
        mContext = context;
        mItemListener = listener;
        mRecipelist = recipeList;
    }
*/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mRecipelist.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (mRecipelist != null){
            return mRecipelist.size();
        }
        return 0;
    }

    public interface ItemClickedListener {
        void menuItemClick(Recipe recipe);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView imageView;
        private TextView menuTitle;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.menu_image);
            menuTitle = itemView.findViewById(R.id.menu_tv);
        }

        @Override
        public void onClick(View v) {
            MenuRVAdapter.mItemListener.menuItemClick(mRecipelist.get(position));
        }

        public void bindData(Recipe recipe, int position) {
            this.position = position;
            String linkImage = recipe.getImage();
            int id = recipe.getId();
            if(!linkImage.isEmpty()) {
                Picasso.get().load(linkImage).into(imageView);
            }else{
                if (id == 1 ){
                    Picasso.get().load(R.drawable.ic_nutella_pie).into(imageView);
//                holder.image.setImageResource(R.drawable.ic_nutella_pie);
                }else if (id == 2){
                    Picasso.get().load(R.drawable.ic_brownies).into(imageView);
//                .image.setImageResource(R.drawable.ic_brownies);
                }else if (id == 3){
                    Picasso.get().load(R.drawable.ic_yollow_cake).into(imageView);
//                myViewHolder.image.setImageResource(R.drawable.ic_yollow_cake);
                }else if (id == 4){
                    Picasso.get().load(R.drawable.ic_chesscake).into(imageView);
//                myViewHolder.image.setImageResource(R.drawable.ic_chesscake);
                }
            }
            menuTitle.setText(recipe.getName());
        }
    }
}
