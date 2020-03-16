package com.example.tp4.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4.Models.Jour;
import com.example.tp4.Models.Main;
import com.example.tp4.Models.Prevision;
import com.example.tp4.Models.Previsions;
import com.example.tp4.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecyclerViewAdapters extends RecyclerView.Adapter<RecyclerViewAdapters.RecyclerViewHolder>{

    private ArrayList<Jour> jours = new ArrayList<Jour>();
    private String day;

    public RecyclerViewAdapters(ArrayList<Jour> dataModeList) {
        this.jours = dataModeList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_list_item, parent, false);
        viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {


        Double temp_moyenne = 0.00;
        int count = 0;
        String test = "";

        for(Prevision heure: jours.get(position).getListPrevisions()) {
            count ++;
            temp_moyenne += heure.getMain().getTemp();
        }

        temp_moyenne /= count;

        holder.text_view_temp.setText( Math.round(temp_moyenne)+"° en moyenne");
        holder.text_view_date.setText( "Le "+jours.get(position).getJour());
        holder.text_view_city.setText( jours.get(position).getCity().getName() );

        if(jours.get(position).getListPrevisions().size()==1) {

            holder.text_view_temp_soir.setText( Math.round(jours.get(position).getListPrevisions().get(0).getMain().getTemp())+"°" );

            holder.layout_matin.setVisibility(View.GONE);
            holder.layout_ap.setVisibility(View.GONE);

        } else if (jours.get(position).getListPrevisions().size()==2) {

            holder.text_view_temp_ap.setText( Math.round(jours.get(position).getListPrevisions().get(0).getMain().getTemp())+"°" );
            holder.text_view_temp_soir.setText( Math.round(jours.get(position).getListPrevisions().get(1).getMain().getTemp())+"°" );

            holder.layout_matin.setVisibility(View.GONE);

        } else {

            holder.text_view_temp_matin.setText( Math.round(jours.get(position).getListPrevisions().get(0).getMain().getTemp())+"°" );
            holder.text_view_temp_ap.setText( Math.round(jours.get(position).getListPrevisions().get(1).getMain().getTemp())+"°" );
            holder.text_view_temp_soir.setText( Math.round(jours.get(position).getListPrevisions().get(2).getMain().getTemp())+"°" );
        }
    }

    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return jours.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView text_view_temp;
        TextView text_view_date;
        TextView text_view_city;

        LinearLayout llItemView;
        LinearLayout layout_soir;
        LinearLayout layout_ap;
        LinearLayout layout_matin;

        TextView text_view_temp_matin;
        TextView text_view_temp_ap;
        TextView text_view_temp_soir;

        TextView text_view_temp_matin_txt;
        TextView text_view_temp_ap_txt;
        TextView text_view_temp_soir_txt;

        public RecyclerViewHolder(@NonNull View itemView) {

            super(itemView);

            llItemView = itemView.findViewById(R.id.linear_layout_item);

            text_view_temp = itemView.findViewById(R.id.text_view_temp);
            text_view_date = itemView.findViewById(R.id.text_view_date);
            text_view_city = itemView.findViewById(R.id.text_view_city);

            text_view_temp_matin = itemView.findViewById(R.id.text_view_temp_matin);
            text_view_temp_ap = itemView.findViewById(R.id.text_view_temp_ap);
            text_view_temp_soir = itemView.findViewById(R.id.text_view_temp_soir);

            text_view_temp_matin_txt = itemView.findViewById(R.id.text_view_matin_txt );
            text_view_temp_ap_txt = itemView.findViewById(R.id.text_view_ap_txt );
            text_view_temp_soir_txt = itemView.findViewById(R.id.text_view_soir_txt );

            layout_matin = itemView.findViewById(R.id.layout_matin);
            layout_ap = itemView.findViewById(R.id.layout_ap);
            layout_soir = itemView.findViewById(R.id.layout_soir);

        }
    }
}
