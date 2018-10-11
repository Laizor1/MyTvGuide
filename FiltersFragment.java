package com.example.stefan.newapp3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.res.TypedArrayUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class FiltersFragment extends Fragment {
    ListView genresListView;
    TextView text;
    Button submit,skip,reset;
    String[] filtersList = {"Genres", "Released By", "Years", "Rating", "Completed"};
    String[] genres = {"Action", "Comedy", "Drama", "Mystery", "Thriller"};
    String[] releasers = {"Netflix", "CW", "FOX", "BBC", "AMC", "History Channel"};
    String[] years = {"90s", "2000s", "2010s"};
    String[] rating = {"Ascending by Rating", "Descending by Rating"};
    String[] completed = {"Completed", "Ongoing"};



    public FiltersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container, false);
        Intent intent = new Intent(getActivity().getApplicationContext(), FilterRecycler.class);
        genresListView = view.findViewById(R.id.list);
        submit=view.findViewById(R.id.submit);
        skip=view.findViewById(R.id.skip);
        reset=view.findViewById(R.id.reset);
        advancedSearch(intent,genres,0);
        intent.putExtra("Genres",false);
        intent.putExtra("Years",false);
        intent.putExtra("Rating",false);
        intent.putExtra("Duration",false);
        intent.putExtra("Completed",false);
        intent.putExtra("Released By",false);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent(getActivity().getApplicationContext(), FilterRecycler.class);
        advancedSearch(intent,genres,0);
    }

    public void advancedSearch(final Intent intent, final String[] list, final int index) {
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list
        );
        genresListView.setAdapter(listAdapter);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    advancedSearch(intent,releasers,index+1);
                }
                if (index == 1) {
                    advancedSearch(intent,years,index+1);
                }
                if (index == 2) {
                    advancedSearch(intent,rating,index+1);
                }
                if (index == 3) {
                    advancedSearch(intent,completed,index+1);
                }
                if (index == 4) {
                    if(!intent.getBooleanExtra("Genres",false)&&!intent.getBooleanExtra("Years",false)&&!intent.getBooleanExtra("Completed",false)&&!intent.getBooleanExtra("Released By",false)&&!intent.getBooleanExtra("Rating",false)){
                        Toast.makeText(submit.getContext(),"Select at least one filter!",Toast.LENGTH_SHORT).show();
                        advancedSearch(intent,genres,0);
                    }else {
                        startActivity(intent);
                    }
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!intent.getBooleanExtra("Genres",false)&&!intent.getBooleanExtra("Years",false)&&!intent.getBooleanExtra("Completed",false)&&!intent.getBooleanExtra("Released By",false)&&!intent.getBooleanExtra("Rating",false)){
                    Toast.makeText(submit.getContext(),"Select at least one filter!",Toast.LENGTH_SHORT).show();
                    advancedSearch(intent,genres,0);
                }else
                {
                    startActivity(intent);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("Genres",false);
                intent.putExtra("Years",false);
                intent.putExtra("Rating",false);
                intent.putExtra("Duration",false);
                intent.putExtra("Completed",false);
                intent.putExtra("Released By",false);
                advancedSearch(intent,genres,0);
            }
        });
        genresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                switch (position) {
                    case 0:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 1:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 2:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 3:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 4:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 5:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;
                    case 6:
                        intent.putExtra(Integer.toString(index), arg0.getItemAtPosition(position).toString());
                        intent.putExtra(filtersList[index],true);
                        break;

                }

                if (index == 0) {
                    advancedSearch(intent,releasers,index+1);
                }
                if (index == 1) {
                    advancedSearch(intent,years,index+1);
                }
                if (index == 2) {
                    advancedSearch(intent,rating,index+1);
                }
                if (index == 3) {
                    advancedSearch(intent,completed,index+1);
                }
                if (index == 4) {
                    startActivity(intent);
                }
                if (index == 5) {
                    startActivity(intent);
                }

            }
        });

    }
}
