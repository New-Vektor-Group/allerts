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
        startActivity(i);
    }



        //получаем значение выбранное в спинере



}
