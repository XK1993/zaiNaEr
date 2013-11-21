package com.example.zainaer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TXLActivity extends Activity {
    
    Button bn1,bn2,bn3,bn4;
    EditText na,nu,add;
    ListView lv;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txl);
        
        bn1 = (Button)findViewById(R.id.bn1);
        bn1.setOnClickListener(new insertListener());
        
        bn2 = (Button)findViewById(R.id.bn2);
        bn2.setOnClickListener(new deleteListener());
        
        bn3 = (Button)findViewById(R.id.bn3);
        bn3.setOnClickListener(new updateListener());
        
        bn4 = (Button)findViewById(R.id.bn4);
        bn4.setOnClickListener(new quryListener());
        
        na = (EditText)findViewById(R.id.etname);
        nu = (EditText)findViewById(R.id.etnumber);
        add = (EditText)findViewById(R.id.etaddress);
    }


//插入信息按钮
class insertListener implements OnClickListener {

    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        View edtirView = getLayoutInflater().inflate(
                R.layout.add_dialog_edit1, null);
        //获得三个EditText框里面的内容
        final EditText na = (EditText) edtirView
                .findViewById(R.id.etname);
        final EditText nu = (EditText) edtirView
                .findViewById(R.id.etnumber);
        final EditText add = (EditText) edtirView
                .findViewById(R.id.etaddress);
        new AlertDialog.Builder(TXLActivity.this)
        .setView(edtirView)
        .setPositiveButton("保存",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog,
                            int which) {
                      String Name = na.getText().toString().trim();
                      String Number = nu.getText().toString().trim();
                      String Address = add.getText().toString().trim();
                      ContentValues cv = new ContentValues();
                      cv.put("name", Name);
                      cv.put("number",Number);
                      cv.put("Address",Address);
                      DBHelper dbHelper = new DBHelper(TXLActivity.this, "My_db", null, 1);
                      SQLiteDatabase  db = dbHelper.getWritableDatabase();
                      db.insert("linker", null, cv);
                      updateListView();
                      DisplayToast("添加成功！");
                      }
                }).setNegativeButton("" + "取消", null).create()
        .show();
     }
}


//删除信息按钮
class deleteListener implements OnClickListener {

    public void onClick(View v) {
        // TODO Auto-generated method stub
        DBHelper dbHelper = new DBHelper(TXLActivity.this,"My_db",null,1); 
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("linker", new String[] {"name","number","address"}, null, 
                null, null, null, null, null);
        if(cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            db.delete("linker", "name=?", new String[] {name});
           }
        updateListView();
    }
}

//更新信息安妮
class updateListener implements OnClickListener {

    public void onClick(View v) {
        // TODO Auto-generated method stub
    
    }
    
}

//查询信息安妮
class quryListener implements OnClickListener {

  public void onClick(View v) {
      // TODO Auto-generated method stub
      Intent intent = new  Intent(TXLActivity.this,SearchActivity.class);
      startActivity(intent);
  }
}


//消息短提示
public void DisplayToast(String string) {
    // TODO Auto-generated method stub
    Toast.makeText(TXLActivity.this, string, Toast.LENGTH_SHORT).show();
   }

//更新ListView
public void updateListView() {
   
    ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
    HashMap<String, Object>  item ;
    DBHelper dbHelper = new DBHelper(TXLActivity.this,"My_db",null,1);
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = db.query("linker", new String[] {"name","number","address"}, null, null, null, null, null, null);
    cursor.moveToFirst();
    
    while(cursor.moveToNext()) {
        item = new HashMap<String, Object>();
        item.put("name",cursor.getString(cursor.getColumnIndex("name")));
        item.put("number",cursor.getString(cursor.getColumnIndex("number")));
        item.put("address",cursor.getString(cursor.getColumnIndex("address")));
        data.add(item);
        cursor.moveToNext();
      }
      
    lv = (ListView)findViewById(R.id.listview);
      SimpleAdapter  adapter = new SimpleAdapter(this,data,R.layout.buju, new String[] {"name","number","address"}
      , new int[] {R.id.text1,R.id.text2,R.id.text3});
      lv.setAdapter(adapter);
    }
}