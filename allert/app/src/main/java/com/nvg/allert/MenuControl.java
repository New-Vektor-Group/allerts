package com.nvg.allert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuControl extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    public void onClickSelectMap(View view)
    {
        Intent i = new Intent(MenuControl.this, MapsActivity.class);
        startActivity(i);
    }
}
