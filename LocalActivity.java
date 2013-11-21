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


/**public class LocalActivity extends MapActivity {
    // 定义地图引擎管理类
    private BMapManager mapManager;// 定义搜索服务类
    private MKSearch mMKSearch;
    private MapView mapView;
    private MapController mapController;

    LocationListener mLocationListener = null;// onResume时注册此listener，onPause时需要Remove
    MyLocationOverlay mLocationOverlay = null; // 定位图层

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        // 初始化MapActivity
        mapManager = new BMapManager(getApplication());

        // init方法的第一个参数需填入申请的APIKey
        mapManager.init("7d7316dd175ebd72f5eb189d1b18dce7", null);
        super.initMapActivity(mapManager);
        mapView = (MapView) findViewById(R.id.map_View);
        // 设置地图模式为交通地图
        mapView.setTraffic(true);
        // 设置启用内置的缩放控件
        mapView.setBuiltInZoomControls(true);
        // 设置在缩放动画过程中也显示overlay,默认为不绘制
        mapView.setDrawOverlayWhenZooming(true);

        // 添加定位图层
        mLocationOverlay = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(mLocationOverlay);

        // 注册定位事件
        mLocationListener = new LocationListener() {

            public void onLocationChanged(Location location) {
                if (location != null) {
                    GeoPoint geoPoint = new GeoPoint((int) (location.getLatitude() * 1e6),
                            (int) (location.getLongitude() * 1e6));
                    mapView.getController().animateTo(geoPoint);
                    mapController = mapView.getController();
                    // 设置地图的中心
                    mapController.setCenter(geoPoint);
                    // 设置地图默认的缩放级别
                    mapController.setZoom(16);
                    // 搜索贵州大学校门口附近500米范围的自动取款机
                    mMKSearch.poiSearchNearBy("ATM", geoPoint, 500);
                }
            }
        };

    }

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    @Override
    protected void onDestroy() {
        if (mapManager != null) {
            // 程序退出前需调用此方法
            mapManager.destroy();
            mapManager = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mapManager != null) {
            // 终止百度地图API
            mapManager.getLocationManager().removeUpdates(mLocationListener);
            mLocationOverlay.disableMyLocation();
            mLocationOverlay.disableCompass(); // 关闭指南针
            mapManager.stop();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (mapManager != null) {
            // 开启百度地图API
            // 注册定位事件，定位后将地图移动到定位点
            mapManager.getLocationManager().requestLocationUpdates(mLocationListener);
            mLocationOverlay.enableMyLocation();
            mLocationOverlay.enableCompass(); // 打开指南针
            mapManager.start();
        }
        super.onResume();
    }
}**/



  
  
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