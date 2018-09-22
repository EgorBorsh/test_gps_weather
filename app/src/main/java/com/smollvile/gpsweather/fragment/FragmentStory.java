package com.smollvile.gpsweather.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smollvile.gpsweather.App;
import com.smollvile.gpsweather.R;
import com.smollvile.gpsweather.activity.InfoActivity;
import com.smollvile.gpsweather.adapter.RecyclerAdapter;
import com.smollvile.gpsweather.dao.DaoSession;
import com.smollvile.gpsweather.dao.Story;
import com.smollvile.gpsweather.dao.StoryDao;
import com.smollvile.gpsweather.dto.ItemDTO;
import com.smollvile.gpsweather.listener.RecyclerItemListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FragmentStory extends Fragment {

    Context context;

    public FragmentStory() {
    }

    @SuppressLint("ValidFragment")
    public FragmentStory(Context context) {
        this.context = context;
    }

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    StoryDao storyDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_story, container, false);

        DaoSession daoSession = ((App) getActivity().getApplication()).getDaoSession();
        storyDao = daoSession.getStoryDao();

        recyclerView = (RecyclerView) result.findViewById(R.id.recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        List<ItemDTO> list = new ArrayList<>();

        List<Story> stories = storyDao.queryBuilder().list();

        for (int i = 0; i < stories.size(); i++) {
            String city = stories.get(i).getCity();
            String coordinates = stories.get(i).getCoordinates();
            String country = stories.get(i).getCountry();
            String street = stories.get(i).getStreet();
            String house = stories.get(i).getHouse();
            String temp = stories.get(i).getTemp();
            String humidity = stories.get(i).getHumidity();
            String description = stories.get(i).getDescription();
            String windSpeed = stories.get(i).getWindSpeed();
            String date = stories.get(i).getDate();
            String time = stories.get(i).getTime();

            ItemDTO itemDTO = new ItemDTO(city, coordinates, country, street, house, temp, humidity,description, windSpeed, date, time);
            list.add(itemDTO);
        }

        //по нажатию на элемент списка создаем интент
        //передаем ключь-значения
        //запускаем активити информации
        adapter = new RecyclerAdapter(context, list, new RecyclerItemListener() {
            @Override
            public void onClikItem(View v, ItemDTO item) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra("Country", item.getCountry());
                intent.putExtra("Cor", item.getCoordinates());
                intent.putExtra("City", item.getCity());
                intent.putExtra("Street", item.getStreet());
                intent.putExtra("House", item.getHouse());
                intent.putExtra("Temp", item.getTemp());
                intent.putExtra("Humidity", item.getHumidity());
                intent.putExtra("Description", item.getDescription());
                intent.putExtra("WindSpeed", item.getWindSpeed());
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        return result;
    }

}
