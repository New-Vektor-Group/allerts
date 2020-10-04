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
        String body2 = modelClassListInfo.get(position).getHazard();
        String tv1 = modelClassListInfo.get(position).getLatLng();
        String tv2 = modelClassListInfo.get(position).getType();
        String tv3 = modelClassListInfo.get(position).getSize();
        String textView9 = modelClassListInfo.get(position).getTrigger();
        String textView10 = modelClassListInfo.get(position).getCountry();
        String textView11 = modelClassListInfo.get(position).getFatalities();
        viewholder.setData(resource,title,body,body2,tv1,  tv2, tv3, textView9, textView10, textView11);
    }

    @Override
    public int getItemCount() {
        return modelClassListInfo.size();
    }





    class Viewholder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView title;
        private TextView body;
        private TextView body2;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView textView9;
        private TextView textView10;
        private TextView textView11;



        public Viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.Body);
            body2 = itemView.findViewById(R.id.body2);
            tv1 = itemView.findViewById(R.id.tv1);
            tv2 = itemView.findViewById(R.id.tv2);
            tv3 = itemView.findViewById(R.id.tv3);
            textView9 = itemView.findViewById(R.id.textView9);
            textView10 = itemView.findViewById(R.id.textView10);
            textView11 = itemView.findViewById(R.id.textView11);

        }

        private void setData(int imageResource, String titleText, String bodyText, String body2, String tv1, String tv2, String tv3, String textView9, String textView10, String textView11){
            imageView.setImageResource(imageResource);
            title.setText(titleText);
            body.setText(bodyText);
            this.body2.setText(body2);
            this.tv1.setText(tv1);
            this.tv2.setText(tv2);
            this.tv3.setText(tv3);
            this.textView9.setText(textView9);
            this.textView10.setText(textView10);
            this.textView11.setText(textView11);

        }
    }
}
