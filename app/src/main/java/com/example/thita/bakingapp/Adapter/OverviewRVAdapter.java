package com.example.thita.bakingapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thita.bakingapp.R;

import java.util.List;

public class OverviewRVAdapter extends RecyclerView.Adapter<OverviewRVAdapter.ViewHolder> {

    public interface ItemClickedListener {
        void onItemClicked(int pos);
    }

    protected static ItemClickedListener mItemListener;
    public List<String> mOverviewList;
    private Context mContext;


    public OverviewRVAdapter(Context context, ItemClickedListener listener, List<String> overviewList){
        mContext = context;
        mOverviewList = overviewList;
        mItemListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_overview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.retrieveData(mOverviewList.get(position), position);

    }

    @Override
    public int getItemCount() {
        if(mOverviewList != null){
            return mOverviewList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView overViewInfo;
        private int positionItemview;

        public ViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            overViewInfo = itemView.findViewById(R.id.row_overview_tv);
        }

        @Override
        public void onClick(View view) {
            OverviewRVAdapter.mItemListener.onItemClicked(positionItemview);
        }

        public void retrieveData(String stringUpdate, int position) {
            overViewInfo.setText(stringUpdate);
            positionItemview = position;
        }
    }
}
