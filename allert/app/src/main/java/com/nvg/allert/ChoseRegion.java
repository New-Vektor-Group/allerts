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

import com.google.android.gms.maps.model.LatLng;

public class ChoseRegion extends Activity{

    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_region_activity);

        init();


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {       //получаем значение выбранное в спинере
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
//                Toast.makeText(getApplicationContext(),
//                        "Запись - " + adapterView.getAdapter().getItem(pos).toString() + "\nПозиция - " + pos,
//                        Toast.LENGTH_SHORT).show();
              if(adapterView.getAdapter().getItem(pos).toString().trim().equals("Moldova")) setRegionMDSpiner();
                if(adapterView.getAdapter().getItem(pos).toString().trim().equals("Ukraine")) setRegionUKSpiner();
                if(adapterView.getAdapter().getItem(pos).toString().trim().equals("Romania")) setRegionROSpiner();
            }
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),
                     "Some error, sorry!",
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void setRegionMDSpiner()
    {
        Spinner spinner = findViewById(R.id.regionSpiner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.RegOfMd,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void setRegionUKSpiner()
    {
        Spinner spinner = findViewById(R.id.regionSpiner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.RegOfUk,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    public void setRegionROSpiner()
    {
        Spinner spinner = findViewById(R.id.regionSpiner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.RegOfRo,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
    void init()
    {
        dropdown=findViewById(R.id.countrySpiner);
    }


    public void onClickNextMap(View view){
        Intent i = new Intent(ChoseRegion.this, MapsActivity.class);
        LatLng tmpRegion = null;
        switch (dropdown.getSelectedItem().toString()){
            case "Moldova":
                tmpRegion = new LatLng(47.025597, 28.830562);
                break;
            case "Ukraine":
                tmpRegion = new LatLng(50.434389, 30.556316);
                break;
            case "Romania":
                tmpRegion = new LatLng(44.437558, 26.090253);
                break;
        }
        if(tmpRegion != null){
            i.putExtra("la",tmpRegion.latitude);
            i.putExtra("lo",tmpRegion.longitude);
        }
        startActivity(i);
    }

    public void onClickGoWA(View view)
    {

        Intent i = new Intent(ChoseRegion.this, MenuControl.class);
        Toast.makeText(getApplicationContext(),"This is work to Wiew Alert!", Toast.LENGTH_SHORT).show();

        startActivity(i);
    }





}
