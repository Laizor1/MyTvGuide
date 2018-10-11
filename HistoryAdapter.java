package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Stefan on 3/14/2018.
 */

class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    List<Recents> historyList;
    Context context;

    public HistoryAdapter(List<Recents> historyList,Context context)
    {
        this.historyList=historyList;
        this.context=context;
    }
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_card_row,parent, false );
        return new HistoryAdapter.ViewHolder(view,context,historyList);
    }

    @Override
    public void onBindViewHolder(final HistoryAdapter.ViewHolder holder, final int position)
    {
        String genres=" ";
        List<String> genreList;
        StringBuilder sb= new StringBuilder();
            final AppDatabase db = Room.databaseBuilder(holder.name.getContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
            Shows show=db.showsDao().getShowById(historyList.get(position).getShowId());
            holder.name.setText(show.getShowName());
            Picasso.with(holder.image.getContext()).load(show.getImgSrc()).into(holder.image);
            Picasso.with(holder.image.getContext()).setLoggingEnabled(true);

        genreList=db.sgJoinDao().getGenresByName(show.getShowName());
        try {
            for (String genre : genreList) {
                sb.append(genre);
                sb.append(", ");
            }
            genres = sb.toString();
            genres = genres.substring(0, genres.length() - 2);
        } catch (Exception e) {
        }
        holder.genres.setText(genres);
        holder.date.setText("Added on:"+historyList.get(position).getDateAdded().toString());
    }

    @Override
    public int getItemCount()
    {
        return historyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        List<Recents> historyList;
        Context context;
        TextView name;
        TextView genres;
        TextView date;
        ImageView image;


        public ViewHolder(View itemView,Context context,List<Recents> historyList) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.historyList = historyList;
            this.context = context;
            name = itemView.findViewById(R.id.history_name);
            genres = itemView.findViewById(R.id.history_genres);
            date = itemView.findViewById(R.id.history_date);
            image = itemView.findViewById(R.id.history_image);
        }
        @Override
        public void onClick(View view) {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
            int position=getAdapterPosition();
            Shows show=db.showsDao().getShowById(historyList.get(position).getShowId());
            Intent intent=new Intent(this.context,ShowCardViewInfo.class);
            intent.putExtra("name",show.getShowName());
            this.context.startActivity(intent);
        }
    }
}
