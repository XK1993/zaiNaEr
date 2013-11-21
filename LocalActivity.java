package com.example.zainaer;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;

public class LocalActivity extends MapActivity {  
    private BMapManager mapManager;  
    private MapView mapView;  
    private MapController mapController;  
  
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_local);  
  
        // 初始化MapActivity   
        mapManager = new BMapManager(getApplication());  
        // init方法的第一个参数需填入申请的API Key   
        mapManager.init("285B415EBAB2A92293E85502150ADA7F03C777C4", null);  
        super.initMapActivity(mapManager);  
  
        mapView = (MapView) findViewById(R.id.map_View);  
        // 设置地图模式为交通地图   
        mapView.setTraffic(true);  
        // 设置启用内置的缩放控件   

        mapView.setBuiltInZoomControls(true);  
  
        // 用给定的经纬度构造一个GeoPoint（纬度，经度）   
        GeoPoint point = new GeoPoint((int) (47.118440 * 1E6), (int) (87.493147 * 1E6));  
  
        // 创建标记maker   
        Drawable marker = this.getResources().getDrawable(R.drawable.love_press);  
        // 为maker定义位置和边界   
        marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());  
  
        // 取得地图控制器对象，用于控制MapView   
        mapController = mapView.getController();  
        // 设置地图的中心   
        mapController.setCenter(point);  
        // 设置地图默认的缩放级别   
        mapController.setZoom(12);  
    }  
  
    @Override  
    protected boolean isRouteDisplayed() {  
        return false;  
    }  
  
    @Override  
    protected void onDestroy() {  
        if (mapManager != null) {  
            mapManager.destroy();  
            mapManager = null;  
        }  
        super.onDestroy();  
    }  
  
    @Override  
    protected void onPause() {  
        if (mapManager != null) {  
            mapManager.stop();  
        }  
        super.onPause();  
    }  
  
    @Override  
    protected void onResume() {  
        if (mapManager != null) {  
            mapManager.start();  
        }  
        super.onResume();  
    }  
}  
