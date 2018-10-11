package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    List<PersonalList> myList;
    Context context;

    public ListAdapter(List<PersonalList> myList,Context context)
    {
        this.myList=myList;
        this.context=context;
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_row,parent, false );
        return new ListAdapter.ViewHolder(view,context,myList);
    }

    @Override
          public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position)
    {
        holder.showName.setText(myList.get(position).getShowName());
       // holder.showDateAdded.setText("Added on:"+myList.get(position).getDateAdded().toString());
        holder.showDateAdded.setText(myList.get(position).getShowDesc());
        String genres=" ";
        List<String> genreList;
        StringBuilder sb= new StringBuilder();
        final AppDatabase db = Room.databaseBuilder(holder.showGenre.getContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        genreList=db.sgJoinDao().getGenresByName(myList.get(position).getShowName());
        try {
            for (String genre : genreList) {
                sb.append(genre);
                sb.append(", ");
            }
            genres = sb.toString();
            genres = genres.substring(0, genres.length() - 2);
        } catch (Exception e) {}

        holder.isWatched.setChecked(myList.get(position).isWatched());
        holder.isWatched.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                db.personalListDao().updateWatched(isChecked, myList.get(position).getShowName());
            }
        });
        holder.showGenre.setText(genres);
        Picasso.with(holder.img.getContext()).load(myList.get(position).getImgSrc()).into(holder.img);
        Picasso.with(holder.img.getContext()).setLoggingEnabled(true);
        holder.remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(holder.remove.getContext());
                builder1.setMessage("Do you want to remove "+myList.get(position).getShowName()+" from your collection?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.personalListDao().deleteByName(myList.get(position).getShowName());
                                myList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, myList.size());
                                notifyDataSetChanged();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                }
        });
    }


    @Override
    public int getItemCount()
    {
        return myList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView showName;
        public ImageView img;
        public TextView showGenre;
        public TextView showDateAdded;
        Button remove;
        List<PersonalList> myList;
        Context context;
        ToggleButton isWatched;

        public ViewHolder(View itemView,Context context,List<PersonalList> myList)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            this.myList = myList;
            this.context=context;
            showName=itemView.findViewById(R.id.txtShow);
            showGenre=itemView.findViewById(R.id.txtGenre);
            showDateAdded=itemView.findViewById(R.id.my_list_date);
            img=itemView.findViewById(R.id.my_list_image);
            remove=itemView.findViewById(R.id.my_list_button);
            isWatched=itemView.findViewById(R.id.my_list_toggle);
        }
        @Override
        public void onClick(View view) {
            int position=getAdapterPosition();
            PersonalList plist=this.myList.get(position);
            Intent intent=new Intent(context,ShowCardViewInfo.class);
            intent.putExtra("name",plist.getShowName());
            this.context.startActivity(intent);
        }
    }
}
