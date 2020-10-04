package com.nvg.allert;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvg.allert.itemsAdapter.Adapter;
import com.nvg.allert.itemsAdapter.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class AlertsView extends Activity {

    RecyclerView recyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alerts_activity);
        init();
    }

    void init()
    {
        recyclerView=findViewById(R.id.RecView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        List<ModelClass> modelClassList = new ArrayList<>();        // здесь утсанавливаем значения которые хотим отобразить по примеру
        modelClassList.add(new ModelClass(R.drawable.shapka_s_logo_norm, "Страна", "Landslides probability: " ));

        for(int i=0; i<10;i++){
            //сюда пихать присваивание modelClassList.add(new ModelClass(R.drawable.shapka_s_logo_norm, "Страна", "Landslides probability: "));
            //доделать выбор картинок в зависимости от угрозы
        }

        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}
