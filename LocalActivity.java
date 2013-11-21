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
  
        // ��ʼ��MapActivity   
        mapManager = new BMapManager(getApplication());  
        // init�����ĵ�һ�����������������API Key   
        mapManager.init("285B415EBAB2A92293E85502150ADA7F03C777C4", null);  
        super.initMapActivity(mapManager);  
  
        mapView = (MapView) findViewById(R.id.map_View);  
        // ���õ�ͼģʽΪ��ͨ��ͼ   
        mapView.setTraffic(true);  
        // �����������õ����ſؼ�   

        mapView.setBuiltInZoomControls(true);  
  
        // �ø����ľ�γ�ȹ���һ��GeoPoint��γ�ȣ����ȣ�   
        GeoPoint point = new GeoPoint((int) (47.118440 * 1E6), (int) (87.493147 * 1E6));  
  
        // �������maker   
        Drawable marker = this.getResources().getDrawable(R.drawable.love_press);  
        // Ϊmaker����λ�úͱ߽�   
        marker.setBounds(0, 0, marker.getIntrinsicWidth(), marker.getIntrinsicHeight());  
  
        // ȡ�õ�ͼ�������������ڿ���MapView   
        mapController = mapView.getController();  
        // ���õ�ͼ������   
        mapController.setCenter(point);  
        // ���õ�ͼĬ�ϵ����ż���   
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
