package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Stefan on 3/14/2018.
 */

class GenreListAdapter extends RecyclerView.Adapter<GenreListAdapter.ViewHolder>{
    List<Shows> genreList;

    public GenreListAdapter(List<Shows> myList)
    {
        this.genreList=myList;
    }
    @Override
    public GenreListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.genre_list_rows,parent, false );
        return new GenreListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GenreListAdapter.ViewHolder holder,int position)
    {

        String years =genreList.get(position).getShowYear()+"-"+genreList.get(position).getShowEnd();

        holder.showName.setText(genreList.get(position).getShowName());
        holder.showYear.setText(years);
        holder.showGenre.setText(genreList.get(position).getShowGenre());
        holder.showDesc.setText(genreList.get(position).getShowDesc());
    }

    @Override
    public int getItemCount()
    {
        return genreList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView showName;
        public TextView showYear;
        public TextView showGenre;
        public TextView showDesc;

        public ViewHolder(View itemView)
        {
            super(itemView);
            showName=itemView.findViewById(R.id.txtShow);
            showYear=itemView.findViewById(R.id.txtYear);
            showGenre=itemView.findViewById(R.id.txtGenre);
            showDesc=itemView.findViewById(R.id.txtDesc);
        }
    }
}
