package com.temparatureanalysis.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.temparatureanalysis.activities.ActivityDetails;
import com.temparatureanalysis.R;
import com.temparatureanalysis.pojo.Temperature;

import java.util.ArrayList;

/**
 * Created by Techno Blogger on 25/1/17.
 */

public class BasicListAdapter extends RecyclerView.Adapter<BasicListAdapter.BasicListViewHolder> {

    private Context context;
    private ArrayList<Temperature> temperatureArrayList;

    public BasicListAdapter(Context context, ArrayList<Temperature> temperatureArrayList) {

        this.context = context;
        this.temperatureArrayList = temperatureArrayList;
    }

    @Override
    public BasicListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basic_list_row, parent, false);
        BasicListViewHolder basicListViewHolder = new BasicListViewHolder(view);

        return basicListViewHolder;
    }

    @Override
    public void onBindViewHolder(BasicListViewHolder holder, int position) {

        holder.city.setText(temperatureArrayList.get(position).getCityName());
        holder.temparature.setText(temperatureArrayList.get(position).getTemparature());
    }

    @Override
    public int getItemCount() {
        return temperatureArrayList.size();
    }

    public class BasicListViewHolder extends RecyclerView.ViewHolder {

        private TextView city, temparature;

        public BasicListViewHolder(View itemView) {
            super(itemView);

            city = (TextView) itemView.findViewById(R.id.city);
            temparature = (TextView) itemView.findViewById(R.id.temparature);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("click", "called");
                    Intent intent = new Intent(context, ActivityDetails.class);
                    intent.putExtra("city", temperatureArrayList.get(getAdapterPosition()).getCityName());
                    intent.putExtra("temparature", temperatureArrayList.get(getAdapterPosition()).getTemparature());

                    context.startActivity(intent);
                }
            });
        }

    }
}
