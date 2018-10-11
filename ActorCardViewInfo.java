package com.example.stefan.newapp3;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class ActorCardViewInfo extends AppCompatActivity implements View.OnClickListener {
    ImageView actorImg;
    TextView actorName,actorOrigin,actorDesc;
    private RecyclerView recycler;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    List<Shows> showList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_card_view_info);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "user-database").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        actorImg=findViewById(R.id.info_img);
        actorName=findViewById(R.id.info_name);
        actorDesc=findViewById(R.id.info_desc);
        actorOrigin=findViewById(R.id.info_born);
        Actors actor=db.actorsDao().getActorByName(getIntent().getStringExtra("name"));

        Picasso.with(getApplicationContext()).load(actor.getActorUrl()).into(actorImg);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        actorName.setText(actor.getActorName());
        actorOrigin.setText("Born: " + actor.getActorBirth());
        actorDesc.setText(actor.getActorDesc());

        actorImg.setOnClickListener(this);
        showList=db.saJoinDao().getShowsByActor(getIntent().getStringExtra("name"));
        recycler=findViewById(R.id.horizontal_recycler);
        recycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recycler.setLayoutManager(layoutManager);
        adapter = new ActorInfoAdapter(showList,this);
        recycler.setAdapter(adapter);



    }

    @Override
    public void onClick(View view) {
        Intent intent=new Intent(this,ImageFullScreen.class);
        intent.putExtra("img",getIntent().getStringExtra("img"));
        this.startActivity(intent);
    }
}
