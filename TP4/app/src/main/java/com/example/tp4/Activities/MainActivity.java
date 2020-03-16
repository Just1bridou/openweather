package com.example.tp4.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.tp4.Models.City;
import com.example.tp4.Models.GsonRequest;
import com.example.tp4.Models.Jour;
import com.example.tp4.Models.Main;
import com.example.tp4.Models.Prevision;
import com.example.tp4.Models.Previsions;
import com.example.tp4.Models.SnapHelperOneByOne;
import com.example.tp4.Models.VolleyHelper;
import com.example.tp4.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.tp4.Adapters.RecyclerViewAdapters;
import com.example.tp4.Interfaces.RecyclerViewClickListener;
import com.example.tp4.Listners.RecyclerTouchListener;

public class MainActivity extends AppCompatActivity {

    private Previsions dataPrevisions;
    private RecyclerView recyclerView;
    private ArrayList<Jour> jours = new ArrayList<Jour>();
    private int indexDay = 0;

    private String url = "https://api.openweathermap.org/data/2.5/forecast?q=Annecy&units=metric&appid=7cb0ed109477b227b0264ae60ef0d266";
    private TextView temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiGet();


    }

    private void setDatas() {

        ArrayList<Prevision> lPre = new ArrayList<Prevision>();

        lPre.add(new Prevision(new Main(1, 1, 1, 1, 1, 1, 1, 1, 1), "28 juin"));
        dataPrevisions = (new Previsions(lPre, new City("1","Annecy","France",1L,1L)));



    }

    private void apiGet() {


        GsonRequest gsonRequest = new GsonRequest<>(url, Previsions.class, null, new Response.Listener<Previsions>() {
            @Override
            public void onResponse(Previsions previsions) {

                dataPrevisions = previsions;

                String actualDay = "";

                for(Prevision prev : dataPrevisions.getList()) {

                    ArrayList<String> mois = new ArrayList<String>();
                    mois.add("janvier");
                    mois.add("fevrier");
                    mois.add("mars");
                    mois.add("avril");
                    mois.add("mai");
                    mois.add("juin");
                    mois.add("juillet");
                    mois.add("aout");
                    mois.add("septembre");
                    mois.add("octobre");
                    mois.add("novembre");
                    mois.add("decembre");

                    String dtStart = prev.getDt_txt();
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                    try {
                        Date date = format.parse(dtStart);
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);

                        String dateOfDay = cal.get(Calendar.DATE)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR);

                        if(actualDay=="") { // vide -> Premier jour
                            actualDay = dateOfDay;

                            String jour = cal.get(Calendar.DATE)+" "+mois.get(cal.get(Calendar.MONTH))+" "+cal.get(Calendar.YEAR);

                            jours.add(new Jour(jour,dataPrevisions.getCity()));
                        }

                        if(actualDay.equals(dateOfDay)) { // jour = ancien jour

                            if(cal.get(Calendar.HOUR_OF_DAY) == 9 ||  cal.get(Calendar.HOUR_OF_DAY) == 15 || cal.get(Calendar.HOUR_OF_DAY) == 18) {
                                jours.get(indexDay).addPrevision(prev);
                            }
                        }

                        else { // jour suivant

                            indexDay++;

                            actualDay = dateOfDay;
                            String jour = cal.get(Calendar.DATE)+" "+mois.get(cal.get(Calendar.MONTH))+" "+cal.get(Calendar.YEAR);

                            jours.add(new Jour(jour,dataPrevisions.getCity()));

                            if(cal.get(Calendar.HOUR_OF_DAY) == 9 || cal.get(Calendar.HOUR_OF_DAY) == 15 || cal.get(Calendar.HOUR_OF_DAY) == 18) {
                                jours.get(indexDay).addPrevision(prev);
                            }
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                }

                recycler();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("MainActivity", volleyError.getMessage());
                Toast.makeText(MainActivity.this, volleyError.getMessage(),Toast.LENGTH_LONG+10).show();
            }
        });

        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);

    }

    private void recycler() {

        recyclerView = findViewById(R.id.rvOrders);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setFocusable(false);

        //RecyclerViewAdapters adapter = new RecyclerViewAdapters(dataPrevisions);
        RecyclerViewAdapters adapter = new RecyclerViewAdapters(jours); // recycler de jours

        recyclerView.setAdapter(adapter);

       /* recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener() {

            @Override
            public void onClick(View view, int position) {

                String temp = String.valueOf(dataPrevisions.getList().get(position).getMain().getTemp());
                String date = dataPrevisions.getList().get(position).getDt_txt();
                String city = dataPrevisions.getCity().getName();

                Toast.makeText(MainActivity.this, temp + " " + date + " " + city, Toast.LENGTH_SHORT).show();
            }
        }));
*/
        LinearSnapHelper linearSnapHelper = new SnapHelperOneByOne();
        linearSnapHelper.attachToRecyclerView(recyclerView);


    }
}
