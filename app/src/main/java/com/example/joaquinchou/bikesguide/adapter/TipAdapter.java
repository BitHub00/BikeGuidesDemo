package com.example.joaquinchou.bikesguide.adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.example.joaquinchou.bikesguide.PoiSearchActivity;
import com.example.joaquinchou.bikesguide.R;
import com.example.joaquinchou.bikesguide.utils.Constants;

import java.util.List;



public class TipAdapter extends RecyclerView.Adapter<TipAdapter.ViewHolder> {

    private List<Tip> tipList;
    private PoiSearchActivity activity;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tipName;
        TextView tipAddress;
        View tipView;

        public ViewHolder(View view){
            super(view);
            tipView=view;
            tipName=(TextView)view.findViewById(R.id.name);
            tipAddress=(TextView)view.findViewById(R.id.address);
        }
    }

    public TipAdapter(List<Tip> tipList, PoiSearchActivity activity){
        this.tipList=tipList;
        this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_tip_poi, parent, false);
        final ViewHolder holder=new ViewHolder(view);
        holder.tipView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Tip tip=tipList.get(position);
                if(tip.getPoiID()!=null&&tip.getPoint()!=null){
                    Intent intent=new Intent();
                    intent.putExtra("resultType", Constants.RESULT_TIP);
                    intent.putExtra("result", tip);
                    activity.setResult(AppCompatActivity.RESULT_OK, intent);
                    activity.finish();
                } else {
                    activity.setQuery(tip.getName());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tip tip=tipList.get(position);
        if(tip.getPoiID()==null&&tip.getPoint()==null){
            holder.tipName.setText(tip.getName()+tip.getDistrict());
            holder.tipAddress.setVisibility(View.GONE);
        } else {
            holder.tipName.setText(tip.getName());
            holder.tipAddress.setText(tip.getAddress());
        }
    }

    @Override
    public int getItemCount() {
        return tipList.size();
    }
}
