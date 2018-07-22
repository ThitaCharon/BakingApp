//package com.example.thita.bakingapp.Adapter;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.thita.bakingapp.R;
//
//import java.util.ArrayList;
//
//public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.ViewHolder> {
//
//    // TODO Old version
//    private Context mContext;
//    private ArrayList<String> mData;
//    protected ItemListener mSelecteListener;
//
//    public OverviewAdapter(Context mContext, ArrayList<String> mData, ItemListener mSelecteListener) {
//        this.mContext = mContext;
//        this.mData = mData;
//        this.mSelecteListener = mSelecteListener;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_overview,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
//        holder.shortDescription.setText(mData.get(position).toString());
//        holder.position = position;
//    }
//
//    @Override
//    public int getItemCount() {
//        if (!mData.isEmpty()){
//            return mData.size();
//        }
//        return 0;
//    }
//
//    public interface ItemListener{
//        void onSelected(int position);
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private TextView shortDescription;
//        private int position;
//
//        public ViewHolder(View itemView){
//            super(itemView);
//            itemView.setOnClickListener(this);
//            shortDescription = itemView.findViewById(R.id.tv_overview_shortDescription);
//        }
//
//        @Override
//        public void onClick(View v) {
//            mSelecteListener.onSelected(position);
//        }
//    }
//}
