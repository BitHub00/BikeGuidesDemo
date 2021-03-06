package com.example.joaquinchou.bikesguide;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.enums.IconType;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.example.joaquinchou.bikesguide.utils.NavigationVoiceController;

import static com.amap.api.navi.AMapNaviView.CAR_UP_MODE;
import static com.amap.api.navi.AMapNaviView.NORTH_UP_MODE;


public class NavigateActivity extends AppCompatActivity implements AMapNaviListener,AMapNaviViewListener, View.OnClickListener {


    private AMapNaviView aMapNaviView=null;
    private AMapNavi aMapNavi=null;
    private AMap aMap;
    private FloatingActionButton Code_text=null;
    SensorManager mSensorManager = null;



    //private NavigationVoiceController controller=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);
        aMapNaviView=(AMapNaviView)findViewById(R.id.navigate_view);
        aMapNaviView.setNaviMode(CAR_UP_MODE);
        Code_text=findViewById(R.id.code_text);
        aMapNaviView.onCreate(savedInstanceState);
        aMapNaviView.setAMapNaviViewListener(this);
        Code_text.setOnClickListener(this);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



        AMapNaviViewOptions options=new AMapNaviViewOptions();
        options.setReCalculateRouteForYaw(true);
        options.setScreenAlwaysBright(true);
        aMapNaviView.setViewOptions(options);
//        controller= NavigationVoiceController.getInstance(getApplicationContext());
        aMapNavi=AMapNavi.getInstance(getApplicationContext());
//        aMapNavi.addAMapNaviListener(controller);
        AMapNavi.setTtsPlaying(false);
//        aMapNavi.startNavi(NaviType.GPS);
        aMapNavi.startNavi(NaviType.EMULATOR);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        aMapNaviView.onDestroy();
        aMapNavi.stopNavi();
//        aMapNavi.removeAMapNaviListener(controller);
//        controller.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        aMapNaviView.onPause();
        aMapNavi.pauseNavi();
//        controller.stopSpeaking();
    }

    @Override
    protected void onResume() {
        super.onResume();
        aMapNaviView.onResume();
        aMapNavi.resumeNavi();
        final SensorEventListener myListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(myListener, mSensor,
                SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    public void onLockMap(boolean b) {

    }

    //导航组件返回按钮点击事件处理
    @Override
    public boolean onNaviBackClick() {
        return false;
    }

    //点击返回按钮后弹出对话框选择“确定”事件处理
    @Override
    public void onNaviCancel() {
        Log.e("NavigateActivity","导航结束。");
        finish();
    }

    @Override
    public void onNaviMapMode(int i) {

    }

    @Override
    public void onNaviSetting() {

    }

    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }

    @Override
    public void onScanViewButtonClick() {

    }

    @Override
    public void onNaviViewLoaded() {

    }


    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onInitNaviSuccess() {

    }

    @Override
    public void onStartNavi(int i) {


    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

        if (aMapNaviLocation != null) {
            LatLng latLng = new LatLng(aMapNaviLocation.getCoord().getLatitude(), aMapNaviLocation.getCoord().getLongitude());
            // 显示定位小图标，初始化时已经创建过了，这里修改位置即可
            aMap.animateCamera(CameraUpdateFactory.changeLatLng(latLng));
        }
    }

    @Override
    public void onGetNavigationText(int i, String s) {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onEndEmulatorNavi() {

    }

    @Override
    public void onArriveDestination() {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int i) {

    }

    @Override
    public void onGpsOpenStatus(boolean b) {

    }

    @Override
    public void onNaviInfoUpdate(NaviInfo naviInfo) {
//        int next = naviInfo.getIconType();
//        Toast.makeText(NavigateActivity.this,next,Toast.LENGTH_LONG).show();
//        switch (next){
//            case 2:
//                Toast.makeText(NavigateActivity.this,"左转",Toast.LENGTH_LONG).show();
//                break;
//            case 6:
//                Toast.makeText(NavigateActivity.this,"左后方",Toast.LENGTH_LONG).show();
//                break;
//            case 4:
//                Toast.makeText(NavigateActivity.this,"左前方",Toast.LENGTH_LONG).show();
//                break;
//            case 3:
//                Toast.makeText(NavigateActivity.this,"右转",Toast.LENGTH_LONG).show();
//                break;
//            case 5:
//                Toast.makeText(NavigateActivity.this,"右前方",Toast.LENGTH_LONG).show();
//                break;
//            case 9:
//                Toast.makeText(NavigateActivity.this,"直行",Toast.LENGTH_LONG).show();
//                break;
//        }
    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

    }

    @Override
    public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

    }

    @Override
    public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

    }

    @Override
    public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {

    }

    @Override
    public void hideCross() {

    }

    @Override
    public void showModeCross(AMapModelCross aMapModelCross) {

    }

    @Override
    public void hideModeCross() {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

    }

    @Override
    public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

    }

    @Override
    public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

    }

    @Override
    public void onPlayRing(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onCalculateRouteFailure(AMapCalcRouteResult aMapCalcRouteResult) {

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(NavigateActivity.this,"2929",Toast.LENGTH_SHORT).show();
    }
}
