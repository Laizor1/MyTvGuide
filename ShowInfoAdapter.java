package com.example.stefan.newapp3;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Stefan on 5/18/2018.
 */

class ShowInfoAdapter extends RecyclerView.Adapter<ShowInfoAdapter.ViewHolder> {
    List<Actors> actorList;
    Context context;

    public ShowInfoAdapter(List<Actors> actorList,Context context) {
        this.actorList = actorList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_shows_row,parent, false );
        return new ShowInfoAdapter.ViewHolder(view,context,actorList);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.actorName.setText(actorList.get(position).getActorName());
        Picasso.with(holder.actorImg.getContext()).load(actorList.get(position).getActorUrl()).into(holder.actorImg);
        Picasso.with(holder.actorImg.getContext()).setLoggingEnabled(true);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView actorName;
        ImageView actorImg;
        List<Actors> actorList;
        Context context;

        public ViewHolder(View itemView,Context context,List<Actors> actorList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.actorList=actorList;
            this.context=context;
            actorName=itemView.findViewById(R.id.horizontal_show_name);
            actorImg=itemView.findViewById(R.id.horizontal_show_image);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context,ActorCardViewInfo.class);
            intent.putExtra("img",actorList.get(position).getActorUrl());
            intent.putExtra("name",actorList.get(position).getActorName());
            intent.putExtra("origin",actorList.get(position).getActorBirth());
            intent.putExtra("desc",actorList.get(position).getActorDesc());
            this.context.startActivity(intent);
        }
    }
}
