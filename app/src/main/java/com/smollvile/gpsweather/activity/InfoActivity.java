package com.smollvile.gpsweather.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.smollvile.gpsweather.R;

public class InfoActivity extends AppCompatActivity {

    //ключи для значений
    private static final String strCountry = "Country";
    private static final String strCor = "Cor";
    private static final String strCity = "City";
    private static final String strStreet = "Street";
    private static final String strHouse = "House";
    private static final String strTemp = "Temp";
    private static final String strHumidity = "Humidity";
    private static final String strDescription = "Description";
    private static final String strWindSpeed = "WindSpeed";

    TextView country, cor, city, street, house;
    TextView temp, humidity, description, windSpeed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_item_story_layout);


        country = findViewById(R.id.infoCountry);
        cor = findViewById(R.id.infoCor);
        city = findViewById(R.id.infoCity);
        street = findViewById(R.id.infoStreet);
        house = findViewById(R.id.infoHouse);

        temp = findViewById(R.id.infoTemp);
        humidity = findViewById(R.id.infoHumidity);
        description = findViewById(R.id.infoDescription);
        windSpeed = findViewById(R.id.infoWindSpeed);

        //по ключам достаем значения из интента
        country.setText(getIntent().getStringExtra(strCountry));
        cor.setText(getIntent().getStringExtra(strCor));
        city.setText(getIntent().getStringExtra(strCity));
        street.setText(getIntent().getStringExtra(strStreet));
        house.setText(getIntent().getStringExtra(strHouse));
        temp.setText(getIntent().getStringExtra(strTemp));
        humidity.setText(getIntent().getStringExtra(strHumidity));
        description.setText(getIntent().getStringExtra(strDescription));
        windSpeed.setText(getIntent().getStringExtra(strWindSpeed));
    }
}
