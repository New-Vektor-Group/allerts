package com.nvg.allert;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MenuControl extends Activity {

    ImageView imgAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        this.imgAlert = (ImageView)this.findViewById(R.id.imageView);           // подключаем картинку
        imgAlert.setImageResource(R.drawable.imgbin_warning_sign_scalable_graphics_alert_s_000a22vvemw5d3pxss8cuzq8y_300x212);  // находим её по имени в draweble
    }

    public void onClickSelectMap(View view)     //переходим на новую активити
    {
        Intent i = new Intent(MenuControl.this, MapsActivity.class);
        startActivity(i);
    }
}
