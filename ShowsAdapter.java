package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;
import java.util.*;


/**
 * Created by Stefan on 3/5/2018.
 */

class ShowsAdapter extends RecyclerView.Adapter<ShowsAdapter.ViewHolder>{
    List<Shows> shows;
    Context context;

    public ShowsAdapter(List<Shows> shows,Context context)
    {
        this.shows=shows;
        this.context=context;
    }
    @Override
    public ShowsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_card_row,parent, false );
            return new ViewHolder(view,context,shows);
    }

    @Override
    public void onBindViewHolder(final ShowsAdapter.ViewHolder holder, final int position)
    {
        String years=shows.get(position).getShowYear()+"-"+shows.get(position).getShowEnd();
        String rating="IMDB Rating: "+shows.get(position).getImdbRating();
        String genres=" ";
        List<String> genreList;
        StringBuilder sb= new StringBuilder();

        holder.showName.setText(shows.get(position).getShowName());
        holder.showRating.setText(rating);
        holder.showYear.setText(years);
        final AppDatabase db = Room.databaseBuilder(holder.showGenre.getContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        genreList=db.sgJoinDao().getGenresByName(shows.get(position).getShowName());
        try {
            for (String genre : genreList) {
                sb.append(genre);
                sb.append(", ");
            }
            genres = sb.toString();
            genres = genres.substring(0, genres.length() - 2);
        } catch (Exception e) {
        }
        holder.showGenre.setText(genres);
        Picasso.with(holder.showImage.getContext()).load(shows.get(position).getImgSrc()).into(holder.showImage);
        Picasso.with(holder.showImage.getContext()).setLoggingEnabled(true);

        holder.add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(db.personalListDao().showExists(shows.get(position).getShowName())!=0){
                    Toast.makeText(holder.add.getContext(),"Show already added!",Toast.LENGTH_SHORT).show();
                }else
                {
                    List<String> genreList;
                    StringBuilder sb=new StringBuilder();
                    genreList = db.sgJoinDao().getGenresByName(shows.get(position).getShowName());
                    try {
                        for (String genre : genreList) {
                            sb.append(genre);
                            sb.append(", ");
                        }
                        String genres2 = sb.toString();
                        genres2 = genres2.substring(0, genres2.length() - 2);
                        db.personalListDao().insertAll(new PersonalList(shows.get(position).getShowName(),shows.get(position).getShowYear(), genres2, shows.get(position).getShowDesc(),shows.get(position).getImdbRating(),shows.get(position).getImgSrc(),shows.get(position).getCountry(),shows.get(position).getDuration(),shows.get(position).getShowEnd(),shows.get(position).getShowReleaser(),false, Calendar.getInstance().getTime()));

                        Toast.makeText(holder.add.getContext(),"Succesfully added!",Toast.LENGTH_SHORT).show();
                    }catch(Exception e){}
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return shows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView showName;
        public TextView showYear;
        public TextView showGenre;
        public TextView showRating;
        public ImageView showImage;
        public Button add;
        List<Shows> shows ;
        Context context;

        public ViewHolder(View itemView,Context context,List<Shows> shows)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            this.shows=shows;
            this.context=context;
            showName=itemView.findViewById(R.id.txtShow);
            showYear=itemView.findViewById(R.id.txtYear);
            showGenre=itemView.findViewById(R.id.txtGenre);
            showRating=itemView.findViewById(R.id.txtRating);
            showImage=itemView.findViewById(R.id.show_image);
            add=itemView.findViewById(R.id.test_button);
        }

        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            Shows show=this.shows.get(position);
            Intent intent=new Intent(this.context,ShowCardViewInfo.class);
            intent.putExtra("name",show.getShowName());
            this.context.startActivity(intent);
        }
    }
}
