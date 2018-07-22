package com.example.thita.bakingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.thita.bakingapp.R;

import java.util.List;

public class ListOverviewAdpater extends ArrayAdapter {
    public List<String> mOverviewList;
    private Context mContext;
    private int mResourceId;

    public ListOverviewAdpater(List<String> mOverviewList, Context mContext, int mResourceId) {
        super(mContext,mResourceId, mOverviewList);
        this.mOverviewList = mOverviewList;
        this.mContext = mContext;
        this.mResourceId = mResourceId;
    }

    private static class ViewHolder {
        TextView topic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String data = (String) getItem(position);
        ViewHolder myViewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_item_overview,parent,false);
            myViewHolder = new ViewHolder();
            myViewHolder.topic = convertView.findViewById(R.id.row_overview_tv);
            convertView.setTag(myViewHolder);
        }else{
            myViewHolder = (ViewHolder) convertView.getTag();
        }

        myViewHolder.topic.setText(mOverviewList.get(position));
        return convertView;
    }

}
