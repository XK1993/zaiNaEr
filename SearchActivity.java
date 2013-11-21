package com.example.zainaer;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends Activity {

    Button bnsearch;
    EditText etsearch;
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
       String Name = etsearch.getText().toString().trim();
       Query(Name);
       }
    }
 
 //查询函数
 public void  Query(String name) {
     
    DBHelper dbHelper = new DBHelper(SearchActivity.this,"My_db",null,1);
    SQLiteDatabase db = dbHelper.getReadableDatabase();
    Cursor cursor = db.query("linker", new String[] {"name","number","address"}, null, 
            null, null, null, null);
    cursor.moveToFirst();
    while(cursor.moveToNext()) {
        String n = cursor.getString(cursor.getColumnIndex("name"));
           if(n.equals(name)) {
               DisplayToast("查找成功！");
           } else {
               DisplayToast("查无此人！");
           }
    }
  }
 


//消息短提示
public void DisplayToast(String string) {
   // TODO Auto-generated method stub
   Toast.makeText(SearchActivity.this, string, Toast.LENGTH_SHORT).show();
  }
}
