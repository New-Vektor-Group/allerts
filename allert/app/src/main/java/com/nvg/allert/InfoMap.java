package com.nvg.allert;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvg.allert.InfoMaps.AdapterInfo;
import com.nvg.allert.InfoMaps.ModelClassInfo;
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

public class InfoMap extends Activity {
    RecyclerView recyclerViewInfo;
    List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_map_activity);
        init();
    }

    void init()
    {
        findAllAndCreateMarkers();
        recyclerViewInfo=findViewById(R.id.infoRec);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewInfo.setLayoutManager(layoutManager);

        List<ModelClassInfo> modelClassListInfo = new ArrayList<>();        // здесь утсанавливаем значения которые хотим отобразить по примеру
        //modelClassListInfo.add(new ModelClassInfo(R.drawable.logo_full, "Страна", "Landslides probability: " , " latLNg",
                //" hazard"));



        AdapterInfo adapter = new AdapterInfo(modelClassListInfo);
        recyclerViewInfo.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
    public void findAllAndCreateMarkers(){
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
}
