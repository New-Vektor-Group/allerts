package com.nvg.allert;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuControl extends Activity {

    ImageView imgAlert;
    TextView tvAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        init();
    }
    void init(){
        this.imgAlert = (ImageView)this.findViewById(R.id.imageView);           // подключаем картинку
        imgAlert.setImageResource(R.drawable.logo_full);  // находим её по имени в draweble
        tvAlerts = findViewById(R.id.txtViewInfoAlerts);
        tvAlerts.setText("Allert is an application designed to determine the likelihood of natural disasters. The data in the application is updated every day, " +
                "after which the neural network processes the received data and makes a prediction for tomorrow.\n \n" +
                "Found " + getNumAlertsFromAI() + " alerts for tomorrow.");
    }

    public void onClickSelectAlert(View view)     //переходим на новую активити
    {
        Intent i = new Intent(MenuControl.this, AlertsView.class);
        startActivity(i);
    }
    public void onClickSelectChoseRegion(View view)     //переходим на новую активити
    {
        Intent in = new Intent(MenuControl.this, ChoseRegion.class);
        startActivity(in);
    }

    public int getNumAlertsFromAI(){
        //получаем количетсво алертов на сегодняшний или завтрашний день
        return 0;
    }
}
