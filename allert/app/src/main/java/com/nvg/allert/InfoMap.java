package com.nvg.allert;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvg.allert.InfoMaps.AdapterInfo;
import com.nvg.allert.InfoMaps.ModelClassInfo;
import com.nvg.allert.itemsAdapter.Adapter;
import com.nvg.allert.itemsAdapter.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class InfoMap extends Activity {
    RecyclerView recyclerViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_map_activity);
        init();
    }

    void init()
    {
        recyclerViewInfo=findViewById(R.id.infoRec);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewInfo.setLayoutManager(layoutManager);

        List<ModelClassInfo> modelClassListInfo = new ArrayList<>();        // здесь утсанавливаем значения которые хотим отобразить по примеру
        modelClassListInfo.add(new ModelClassInfo(R.drawable.shapka_s_logo_norm, "Страна", "Landslides probability: " , " latLNg",
                " hazard"));



        AdapterInfo adapter = new AdapterInfo(modelClassListInfo);
        recyclerViewInfo.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
