package com.example.zainaer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SearchActivity extends Activity {

    Button bnsearch;
    EditText etsearch;
    ListView lv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        
        bnsearch = (Button)findViewById(R.id.bnsearch);
        bnsearch.setOnClickListener(new searchListener());
        
        etsearch = (EditText)findViewById(R.id.etsearch);
    }

//搜索信息安按钮
 class searchListener implements OnClickListener {

    public void onClick(View v) {
        // TODO Auto-generated method stub
        ArrayList<Map<String,Object>> data = new ArrayList<Map<String,Object>>();

    	String Name = etsearch.getText().toString().trim();
    	String[] args = {Name};
        data = Query(args);
        lv = (ListView)findViewById(R.id.listview);
        SimpleAdapter  adapter = new SimpleAdapter(SearchActivity.this,data,R.layout.buju, new String[] {"name","number","address"}
        , new int[] {R.id.text1,R.id.text2,R.id.text3});
        lv.setAdapter(adapter);
       }
    }
 
 //查询函数
 public ArrayList<Map<String, Object>>  Query(String[] args) {
     ArrayList<Map<String, Object>> data = new  ArrayList<Map<String, Object>>();
    DBHelper dbHelper = new DBHelper(SearchActivity.this,"My_db",null,1);
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    String sql="select * from linker where name = ?";
    Cursor cursor = db.rawQuery(sql, args);
    cursor.moveToFirst();
    int cols=cursor.getColumnCount()-1;
    while(cursor.moveToNext()) {
        for(int i=0;i<cols;i++) {
        	HashMap<String, Object>  item = new HashMap<String, Object>();
        	String cols_name =cursor.getColumnName(i);
        	String cols_value =cursor.getString(cursor.getColumnIndex(cols_name));
        	item.put(cols_name,cols_value);
        	data.add(item);
        }
    }
	return data;
  }
 


//消息短提示
public void DisplayToast(String string) {
   // TODO Auto-generated method stub
   Toast.makeText(SearchActivity.this, string, Toast.LENGTH_SHORT).show();
  }
}
