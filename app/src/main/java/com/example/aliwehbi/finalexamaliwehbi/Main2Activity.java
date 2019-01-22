package com.example.aliwehbi.finalexamaliwehbi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ListView lv1;
    private dbHelper db;

    private Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lv1 = (ListView)findViewById(R.id.listview2);
        bt2 = (Button)findViewById(R.id.button2);
        ArrayList<Restaurant> ArrRest = new ArrayList<>();
        db = new dbHelper(this);
        final String ResName = getIntent().getStringExtra("ResName");
        final String City = getIntent().getStringExtra("City");
        try{
            for(int i=0;i<db.getRestaurantInfo(ResName,City).size();i++){
                ArrRest.add(db.getRestaurantInfo(ResName,City).get(i));
            }
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        try{
            final ArrayAdapter<Restaurant> AddRes = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, ArrRest);
            lv1.setAdapter(AddRes);
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main2Activity.this,Main3Activity.class);
                i.putExtra("City", City);
                startActivity(i);
            }
        });
    }
}
