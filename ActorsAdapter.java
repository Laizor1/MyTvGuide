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

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Stefan on 3/14/2018.
 */

class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ViewHolder> {
    List<Actors> actors;
    Context context;

    public ActorsAdapter(List<Actors> actors, Context context) {
        this.actors = actors;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_card_row, parent, false);
        return new ActorsAdapter.ViewHolder(view, context, actors);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.actorName.setText(actors.get(position).getActorName());
        String origin = "Born:" + actors.get(position).getActorBirth();
        holder.actorOrigin.setText(origin);
        Picasso.with(holder.actorImg.getContext()).load(actors.get(position).getActorUrl()).into(holder.actorImg);
        Picasso.with(holder.actorImg.getContext()).setLoggingEnabled(true);
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView actorName;
        public TextView actorOrigin;
        public ImageView actorImg;
        List<Actors> actors;
        Context context;

        public ViewHolder(View itemView, Context context, List<Actors> actors) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.actors = actors;
            this.context = context;
            actorName = itemView.findViewById(R.id.actor_name);
            actorOrigin = itemView.findViewById(R.id.actor_origin);
            actorImg = itemView.findViewById(R.id.actor_image);

        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Actors actor = this.actors.get(position);
            Intent intent = new Intent(context, ActorCardViewInfo.class);
            intent.putExtra("img", actor.getActorUrl());
            intent.putExtra("name", actor.getActorName());
            this.context.startActivity(intent);
        }
    }
}