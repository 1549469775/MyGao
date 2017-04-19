package com.example.administrator.mygaodemap.View;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.mygaodemap.Adapter.ListMenuAdapter;
import com.example.administrator.mygaodemap.Map.Location;
import com.example.administrator.mygaodemap.R;
import com.example.administrator.mygaodemap.Util.DialogCreate;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_menu;
    private List<com.example.administrator.mygaodemap.Bean.Menu> menuList;

    private MapView mMapView = null;
    private AMap aMap;

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenu();
        initMap(savedInstanceState);
        initMapOperation();
    }

    private void initMap(Bundle savedInstanceState){
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.amap);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        location=Location.newInstance(this,mMapView);//定位
    }

    private void initMapOperation(){
        if (aMap==null){
            aMap=mMapView.getMap();
        }
        aMap.setOnMapClickListener(new AMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                DialogCreate.mapAction(MainActivity.this,aMap,latLng);
            }
        });
        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                DialogCreate.mapDelete(MainActivity.this,marker);
                return false;
            }
        });
    }

    private void initMenu(){
        //菜单
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        lv_menu= (ListView) findViewById(R.id.lv_menu);
        if (menuList==null){
            menuList=new ArrayList<>();
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,PlaneActivity.class,R.drawable.volley,"飞机位置"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,TicketActivity.class,R.drawable.volley,"我的订单"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,null,R.drawable.volley,"钱包",0,"$20"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,null,R.drawable.volley,"存脸"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,AboutActivity.class,R.drawable.volley,"关于"));
        }
        lv_menu.setAdapter(new ListMenuAdapter(MainActivity.this,menuList));
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuList.get(position).toActivity();
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(Gravity.START);
            }
        });
        //音效测试
        ImageView img_ads= (ImageView) findViewById(R.id.img_ads);
        final TextView tv_music_name=(TextView) findViewById(R.id.tv_music_name);
        img_ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri notification0 = Uri.parse("android.resource://"+MainActivity.this.getPackageName()+"/"+ R.raw.pi);
                Ringtone r = RingtoneManager.getRingtone(MainActivity.this.getApplicationContext(), notification0);
                r.play();
            }
        });
        img_ads.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogCreate.rawList(MainActivity.this,tv_music_name);
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
