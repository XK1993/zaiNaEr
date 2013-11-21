package com.example.zainaer;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    
    private  final  static String  DATABASE_NAME= "My_db";
    private  final  static  int    DATABASE_VERSION = 1;
    
            public DBHelper(Context context, String name, CursorFactory factory,int version) {
            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String  SQL = ("create table  linker(name  text not null, number  Long not null, address  text  not  null)");
        db.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }

   
}
