package com.nvg.allert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ChoseRegion extends Activity{

    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_region_activity);

        init();


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Запись - " + adapterView.getAdapter().getItem(pos).toString() + "\nПозиция - " + pos,
//                        Toast.LENGTH_SHORT).show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),
                     "Some error, sorry!",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void setRegionSpiner()
    {
        
    }
    void init()
    {
        dropdown=findViewById(R.id.countrySpiner);
    }


    public void onClickNextMap(View view){
        Intent i = new Intent(ChoseRegion.this, MapsActivity.class);
        startActivity(i);
    }



        //получаем значение выбранное в спинере



}
