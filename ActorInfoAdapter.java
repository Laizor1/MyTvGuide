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

class ActorInfoAdapter extends RecyclerView.Adapter<ActorInfoAdapter.ViewHolder> {
    List<Shows> showList;
    Context context;

    public ActorInfoAdapter(List<Shows> showList,Context context) {
        this.showList = showList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_actors_row,parent, false );
        return new ActorInfoAdapter.ViewHolder(view,context,showList);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.showName.setText(showList.get(position).getShowName());
        Picasso.with(holder.showImg.getContext()).load(showList.get(position).getImgSrc()).into(holder.showImg);
        Picasso.with(holder.showImg.getContext()).setLoggingEnabled(true);
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showName;
        ImageView showImg;
        List<Shows> showsList;
        Context context;

        public ViewHolder(View itemView,Context context,List<Shows> showList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.showsList=showList;
            this.context=context;
            showName=itemView.findViewById(R.id.horizontal_name);
            showImg=itemView.findViewById(R.id.horizontal_image);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Intent intent=new Intent(context,ShowCardViewInfo.class);
            intent.putExtra("name",showList.get(position).getShowName());
            this.context.startActivity(intent);
        }
    }
}
