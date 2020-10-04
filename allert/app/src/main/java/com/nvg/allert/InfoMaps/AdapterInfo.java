package com.nvg.allert.InfoMaps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nvg.allert.R;

import java.util.List;

public class AdapterInfo  extends RecyclerView.Adapter<AdapterInfo.Viewholder> {

    private List<ModelClassInfo> modelClassListInfo;

    public AdapterInfo(List<ModelClassInfo> modelClassListInfo){
        this.modelClassListInfo = modelClassListInfo;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.info_map_temp, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int position) {
        int resource = modelClassListInfo.get(position).getImageIcon();
        String title = modelClassListInfo.get(position).getTitle();
        String body = modelClassListInfo.get(position).getBody();
        viewholder.setData(resource,title,body);
    }

    @Override
    public int getItemCount() {
        return modelClassListInfo.size();
    }





    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView body;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.regionTV);
            body = itemView.findViewById(R.id.textViewProb);

        }

        private void setData(int imageResource, String titleText, String bodyText){
            imageView.setImageResource(imageResource);
            title.setText(titleText);
            body.setText(bodyText);
        }
    }
}
