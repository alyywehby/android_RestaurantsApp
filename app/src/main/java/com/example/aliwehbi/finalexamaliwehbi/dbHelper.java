package com.example.aliwehbi.finalexamaliwehbi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Restaurantsdb";
    public static final int DATABASE_VERSION = 1;
    public static String RESTAURANT_TABLE_NAME = "restaurant";
    public static String RESTAURANT_COLUMN_PHONE = "phone";
    public static String RESTAURANT_COLUMN_NAME = "name";
    public static String RESTAURANT_COLUMN_EMAIL = "email";
    public static String RESTAURANT_COLUMN_ADDRESS = "address";

    public dbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "Create table "+RESTAURANT_TABLE_NAME + "("+RESTAURANT_COLUMN_PHONE+" char(8) primary key,"+RESTAURANT_COLUMN_NAME+" varchar(50),"+RESTAURANT_COLUMN_EMAIL+" varchar(50),"+RESTAURANT_COLUMN_ADDRESS+" varchar(50))";
        db.execSQL(query1);
        String query2 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('71809966','MACDO','macdo@gmail.com','Beirut')";
        db.execSQL(query2);
        String query3 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('03333333','KFC','KFC@gmail.com','Beirut')";
        db.execSQL(query3);
        String query4 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('71777777','SOUSA','sousa@gmail.com','Mount Lebanon')";
        db.execSQL(query4);
        String query5 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('70000000','MACDO','lawen@gmail.com','Beirut')";
        db.execSQL(query5);
        String query6 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('81888888','KOUSASNA','kousasna@gmail.com','South')";
        db.execSQL(query6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(DATABASE_VERSION<2){
            String query = "DROP TABLE " + RESTAURANT_TABLE_NAME;
            db.execSQL(query);
            String query1 = "Create table "+RESTAURANT_TABLE_NAME + "("+RESTAURANT_COLUMN_PHONE+" char(8) primary key,"+RESTAURANT_COLUMN_NAME+" varchar(50),"+RESTAURANT_COLUMN_EMAIL+" varchar(50),"+RESTAURANT_COLUMN_ADDRESS+" varchar(50))";
            db.execSQL(query1);
            String query2 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('71809966','MACDO','macdo@gmail.com','Beirut')";
            db.execSQL(query2);
            String query3 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('03333333','KFC','KFC@gmail.com','Beirut')";
            db.execSQL(query3);
            String query4 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('71777777','SOUSA','sousa@gmail.com','Mount Lebanon')";
            db.execSQL(query4);
            String query5 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('70000000','MACDO','lawen@gmail.com','Beirut')";
            db.execSQL(query5);
            String query6 = "Insert into "+RESTAURANT_TABLE_NAME+ " VALUES ('81888888','KOUSASNA','kousasna@gmail.com','South')";
            db.execSQL(query6);
        }
    }
    public ArrayList<Restaurant> getRestaurants(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Restaurant> A = new ArrayList<>();
        Cursor res = db.rawQuery("SELECT * FROM "+ RESTAURANT_TABLE_NAME, null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            A.add(new Restaurant(res.getString(0),res.getString(1),res.getString(2),res.getString(3)));
            res.moveToNext();
        }
        db.close();
        return A;
    }

    public ArrayList<Restaurant> getRestaurantInfo(String name, String city){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Restaurant> A = new ArrayList<>();
        Cursor res = db.rawQuery("SELECT * FROM " + RESTAURANT_TABLE_NAME + " WHERE " + RESTAURANT_COLUMN_NAME + " = '" + name + "' and " + RESTAURANT_COLUMN_ADDRESS + " = '" + city + "'", null);
        res.moveToFirst();
        while(!res.isAfterLast()){
            A.add(new Restaurant(res.getString(0), res.getString(1), res.getString(2), res.getString(3)));
            res.moveToNext();
        }
        db.close();
        return A;
    }
}
