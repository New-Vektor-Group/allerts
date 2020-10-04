package com.nvg.allert;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.nvg.allert.itemsAdapter.Adapter;
import com.nvg.allert.itemsAdapter.ModelClass;
import com.nvg.allert.resourseForMap.Event;
import com.nvg.allert.resourseForMap.HttpHelper;
import com.nvg.allert.resourseForMap.MapsFun;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlertsView extends Activity {

    RecyclerView recyclerView;
    List<Event> eventList = new ArrayList<>();
    List<ModelClass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_alerts_activity);
        init();
    }

    void init()
    {
        findAllMarkers();
        recyclerView=findViewById(R.id.RecView);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        modelClassList = new ArrayList<>();        // здесь утсанавливаем значения которые хотим отобразить по примеру
        ModelClass mdCL;
        for(Event i : eventList){
            //сюда пихать присваивание modelClassList.add(new ModelClass(R.drawable.shapka_s_logo_norm, "Страна", "Landslides probability: "));
            if(i.hazard.equals("earthquakes")) {
                mdCL = new ModelClass(R.drawable.mini8treaska, "Страна : " + i.country, i.hazard + " probability: " + i.prob_trig, i.latLng , (Button)findViewById(R.id.button));
                modelClassList.add(mdCL);
            }
            else {
                mdCL = new ModelClass(R.drawable.mini8opolzeni, "Страна : " + i.country, i.hazard + " probability: " + i.prob_trig, i.latLng , (Button)findViewById(R.id.button));
                modelClassList.add(mdCL);
            }
            //доделать выбор картинок в зависимости от угрозы
        }

        Adapter adapter = new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    public void findAllMarkers(){
        String url = getResponse("https://api.nvg-group.com/alert.php?alerts");
        try {
            eventList.clear();
            JSONObject a = new JSONObject(url);
            JSONArray events = a.getJSONArray("dots");
            for (int i = 0 ; i < events.length() ; i++){
                JSONObject tmp = events.getJSONObject(i);
                eventList.add(new Event(tmp.getDouble("la") , tmp.getDouble("lo"),
                        tmp.getString("hazard") , tmp.getString("type") ,
                        tmp.getString("size") , tmp.getString("trigger") ,
                        tmp.getString("injuries") , tmp.getString("fatalities") ,
                        tmp.getString("prob_trig") , tmp.getString("country")));
            }
        }catch (JSONException e){
            System.out.println(e);
            System.out.println("Response parsing error");
            return;
        }
    }
    //http request
    private String getResponse(String endpoint) {
        HttpHelper helper = new HttpHelper();
        String result = "";
        try {
            result = helper.execute(endpoint).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void OnClickBut(View view)
    {
        Intent i = new Intent(AlertsView.this, MapsActivity.class);
        i.putExtra("getAll" , true);
        startActivity(i);
    }

    public void onClickChoseRegion(View view)
    {
        Intent intent = new Intent(AlertsView.this, ChoseRegion.class);
        startActivity(intent);
    }
}
