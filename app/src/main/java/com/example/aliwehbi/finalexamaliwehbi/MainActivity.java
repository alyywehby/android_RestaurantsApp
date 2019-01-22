package com.example.aliwehbi.finalexamaliwehbi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner sp1, sp2;
    private dbHelper db;
    private ArrayAdapter<String> AddName;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp1 = (Spinner)findViewById(R.id.spinner);
        sp2 = (Spinner)findViewById(R.id.spinner2);
        btn = (Button)findViewById(R.id.button);

        //fill the names spinner
            db = new dbHelper(this);
            final ArrayList<String> ArrRes = new ArrayList<>();
        for(int i =0;i<db.getRestaurants().size();i++){
            ArrRes.add(db.getRestaurants().get(i).getName());
        }
            AddName = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ArrRes);
            sp1.setAdapter(AddName);

        //fill the Locations spinner
        final String cityURL ="http://app-1515400395.000webhostapp.com/getAllLocations.php";
        final ArrayList<String> ArrCity = new ArrayList<>();
        final ArrayAdapter<String> AddCity = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,ArrCity);
        sp2.setAdapter(AddCity);
        final RequestQueue queue= Volley.newRequestQueue(this);
        JsonArrayRequest jr = new JsonArrayRequest(cityURL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0;i<response.length();i++){
                        JSONObject jo = response.getJSONObject(i);
                        AddCity.add(jo.getString("City"));
                        AddCity.notifyDataSetChanged();
                    }
                }
                catch (Exception ex){
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("ResName", sp1.getSelectedItem().toString());
                i.putExtra("City", sp2.getSelectedItem().toString());
                startActivity(i);
            }
        });
    }
}
