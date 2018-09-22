package com.smollvile.gpsweather.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smollvile.gpsweather.App;
import com.smollvile.gpsweather.activity.MainActivity;
import com.smollvile.gpsweather.dao.DaoSession;
import com.smollvile.gpsweather.dao.Story;
import com.smollvile.gpsweather.dao.StoryDao;
import com.smollvile.gpsweather.gps.GPSLocation;
import com.smollvile.gpsweather.R;
import com.smollvile.gpsweather.weather.WeatherOnLocation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FragmentMain extends Fragment {

    Context context;
    TextView country, cor, city, street, house;
    TextView temp, humidity, description, windSpeed;
    Button save;

    GPSLocation location;
    WeatherOnLocation weatherOnLocation;

    StoryDao storyDao;

    public FragmentMain() {
    }

    @SuppressLint("ValidFragment")
    public FragmentMain(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result = inflater.inflate(R.layout.fragment_main, container, false);

        //проверяем есть ли интернет
        //если есть - вызваем методы поиска координат и погоды
        //если нет - выводит alertDialog о том чтобы пользователь включил интерент и выходим из приложения
        if (checkInternet()){
            DaoSession daoSession = ((App)getActivity().getApplication()).getDaoSession();
            storyDao = daoSession.getStoryDao();

            country = result.findViewById(R.id.txtCountry);
            cor = result.findViewById(R.id.txtCor);
            city = result.findViewById(R.id.txtCity);
            street = result.findViewById(R.id.txtStreet);
            house = result.findViewById(R.id.txtHouse);

            temp = result.findViewById(R.id.txtTemp);
            humidity = result.findViewById(R.id.txtHumidity);
            description = result.findViewById(R.id.txtDescription);
            windSpeed = result.findViewById(R.id.txtWindSpeed);

            save = (Button) result.findViewById(R.id.save);

            //инициализируем, а затем вызываем метод определения координат
            location = new GPSLocation(context, country, cor,city, street, house);
            location.providerChoice();

            //следим заполнился ли textView и если да - вызваем метод определения погоды в данной точке
            city.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    if(!city.getText().toString().equals(""))
                    {
                        weatherOnLocation = new WeatherOnLocation(context, temp, humidity, description, windSpeed);
                        weatherOnLocation.updateWeatherData(city.getText().toString());
                    }

                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (country.getText().toString().equals("") && temp.getText().toString().equals(""))
                    {
                        Toast.makeText(context, "Дождитесь значений, пожалуйста.", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Story story = new Story();
                        story.setCity(city.getText().toString());
                        story.setCoordinates(cor.getText().toString());
                        story.setCountry(country.getText().toString());
                        story.setStreet(street.getText().toString());
                        story.setHouse(house.getText().toString());
                        story.setTemp(temp.getText().toString());
                        story.setHumidity(humidity.getText().toString());
                        story.setDescription(description.getText().toString());
                        story.setWindSpeed(windSpeed.getText().toString());

                        Date date = new Date();

                        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                        String strDate = dateFormat.format(date);

                        DateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String strTime = timeFormat.format(date);

                        story.setDate(strDate);
                        story.setTime(strTime);

                        storyDao.insert(story);

                        Toast.makeText(context, R.string.save, Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else{
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(R.string.text_not_internet_title)
                    .setMessage(R.string.text_not_internet)
                    .setCancelable(false)
                    .setNegativeButton(R.string.text_not_internet_btn, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           System.exit(0);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        return result;
    }

    //метод проверки включен ли интернет
    private boolean checkInternet()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else{
            return false;
        }
    }
}
