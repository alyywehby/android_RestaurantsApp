package com.example.aliwehbi.finalexamaliwehbi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity {

    private TextView TVcity, TVlat, TVlong;
    private Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TVcity = (TextView)findViewById(R.id.TVcity);
        TVlat = (TextView)findViewById(R.id.TVlat);
        TVlong = (TextView)findViewById(R.id.TVlong);
        btnClose = (Button)findViewById(R.id.btnClose);

        final String City = getIntent().getStringExtra("City");
        final String cityURL ="http://app-1515400395.000webhostapp.com/getAllLocations.php";
        final RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jr = new JsonArrayRequest(cityURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0;i<response.length();i++){
                        JSONObject jo = response.getJSONObject(i);
                        if(jo.getString("City").equals(City)){
                            Location.city=jo.getString("City");
                            TVcity.setText("City: "+Location.city);
                            Location.latitude = jo.getDouble(" Latitude");
                            TVlat.setText("Latitude: "+Location.latitude);
                            Location.longitude = jo.getDouble(" Longitude ");
                            TVlong.setText("Longitude: "+Location.longitude);
                        }

//                        if(jo.getString("City").equals("Beirut")){
//                            Location.city = jo.getString("City");
//                            Location.latitude = jo.getDouble(" Latitude");
//                            Location.longitude = jo.getDouble(" Longitude ");
//                            break;
//                        }
                    }
                }
                catch (Exception ex){
                    Toast.makeText(Main3Activity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main3Activity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jr);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
