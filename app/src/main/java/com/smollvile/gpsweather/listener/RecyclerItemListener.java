package com.smollvile.gpsweather.listener;


import android.view.View;

import com.smollvile.gpsweather.dto.ItemDTO;
//наш слушатель на элементы списка
public interface RecyclerItemListener {
    public void onClikItem(View v, ItemDTO item);
}
