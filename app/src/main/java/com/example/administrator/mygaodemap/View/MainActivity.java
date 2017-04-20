package com.example.administrator.mygaodemap.View;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.support.design.widget.Snackbar;
import android.util.Log;
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
import android.widget.Button;
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
import com.bumptech.glide.Glide;
import com.example.administrator.mygaodemap.Adapter.ListMenuAdapter;
import com.example.administrator.mygaodemap.Bean.Constant;
import com.example.administrator.mygaodemap.Bean.FaceTest;
import com.example.administrator.mygaodemap.Interface.JudgeListener;
import com.example.administrator.mygaodemap.Map.Location;
import com.example.administrator.mygaodemap.Map.MapUtil;
import com.example.administrator.mygaodemap.R;
import com.example.administrator.mygaodemap.Service.SocketService;
import com.example.administrator.mygaodemap.Util.CircleImageView;
import com.example.administrator.mygaodemap.Util.DialogCreate;
import com.example.administrator.mygaodemap.Util.ShowUtil;
import com.example.administrator.mygaodemap.Util.TwoToExit;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_menu;
    private List<com.example.administrator.mygaodemap.Bean.Menu> menuList;
    private TextView tv_statue;
    private Button btn_ticket,btn_location,btn_saveFace;
    private CircleImageView img_person;

    private MapView mMapView = null;
    private AMap aMap;

    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMenu();
        initStatue();
        initBtn();
        initMap(savedInstanceState);
        initMapOperation();
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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    private void initBtn() {
        btn_ticket= (Button) findViewById(R.id.btn_ticket);
        btn_location= (Button) findViewById(R.id.btn_location);
        btn_saveFace= (Button) findViewById(R.id.btn_saveFace);
        //防止按钮的多触
        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                btn_ticket.setEnabled(false);
                DialogCreate.fillMessage(MainActivity.this, new JudgeListener() {
                    @Override
                    public void onSuccess() {
                        btn_ticket.setEnabled(true);
                    }

                    @Override
                    public void onError(Exception e) {
                        btn_ticket.setEnabled(true);
                        ShowUtil.showSnack(v,e.getMessage());
                    }
                });
            }
        });
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_saveFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initStatue() {
        tv_statue= (TextView) findViewById(R.id.tv_statue);
        tv_statue.setText("当前暂无订单，请您下单");
        initStatueOperation(tv_statue,0);
    }

    private void initStatueOperation(final TextView tv_statue, final int type){

        tv_statue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type){
                    case 0:
                        tv_statue.setText("当前暂无订单，请您下单");
                        initStatueOperation(tv_statue,1);
                        break;
                    case 1:
                        tv_statue.setText("飞机尚在路途中，请等待.......点击查看");
                        initStatueOperation(tv_statue,2);
                        break;
                    case 2:
                        tv_statue.setText("飞机已到达指定地点，点击确认");
                        ShowUtil.showSnack(v,tv_statue.getText().toString());
                        initStatueOperation(tv_statue,0);
                        break;
                }
            }
        });
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
            MapUtil.setMapCustomStyleFile(this,aMap);
//            aMap.setMapCustomEnable(true);//载入自定义底图
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
        initMenuStart();
        initMenuHead();
        initMenuList();
        initMenuBottom();
    }

    private void initMenuBottom() {
        //音效测试
        ImageView img_ads= (ImageView) findViewById(R.id.img_ads);
        Glide.with(this).load(R.drawable.test).asBitmap().into(img_ads);
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

    private void initMenuList() {
        lv_menu= (ListView) findViewById(R.id.lv_menu);
        if (menuList==null){
            menuList=new ArrayList<>();
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,PlaneActivity.class,R.drawable.join_wb,"飞机位置"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,TicketActivity.class,R.drawable.join_plan300,"我的订单"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,null,R.drawable.join_wb,"钱包",0,"$20"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,null,R.drawable.join_plan300,"存脸"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,null,R.drawable.join_wb,"使用规则"));
            menuList.add(new com.example.administrator.mygaodemap.Bean.Menu(MainActivity.this,AboutActivity.class,R.drawable.join_plan300,"关于"));
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
    }

    private void initMenuHead() {
        img_person=(CircleImageView) findViewById(R.id.img_person);
        Glide.with(this).load(R.drawable.myhead).asBitmap().into(img_person);
    }

    private void initMenuStart() {
        // /菜单
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            TwoToExit.newInstance().onBackPressed(this);
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
//
//    private String user_;
//    private String pass_;
//    private String token="";
//    private String tokens="";
//    private ProgressDialog progress;
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 3 && resultCode == RESULT_OK ) {
//
//            //存脸
//            File file = new File(path_);
//            byte[] buff = FaceTest.getBytesFromFile(file);
//            final String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
//
//
//            final HashMap<String, String> map = new HashMap<>();
//            final HashMap<String, byte[]> byteMap = new HashMap<>();
//            map.put("api_key", Constant.Key);
//            map.put("api_secret", Constant.Secret);
//            byteMap.put("image_file", buff);
//            try{
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            byte[] bacd = FaceTest.post(url, map, byteMap);
//                            String str = new String(bacd);
//                            Log.i("progress",str);
//                            String h=LoginActivity.getFaceToken(str);
//                            if(h.equals("error"))
//                            {
//                                Looper.prepare();
//                                Toast.makeText(MainActivity.this, "获取人脸失败，请重新拍照",
//                                        Toast.LENGTH_SHORT).show();
//                                Looper.loop();
//                                return;
//                            }
//                            flag=flag+1;
//                            if(flag==1)
//                            {
//                                token=token+h+"###";
//                                tokens=tokens+h+",";
//                                Looper.prepare();
//                                Toast.makeText(MainActivity.this, "请再次拍照",
//                                        Toast.LENGTH_SHORT).show();
//                                Looper.loop();
//                            }
//                            else if(flag==2)
//                            {
//                                token=token+h;
//                                tokens=tokens+h;
//
//
//                                //获取数据库已有信息
//                                HashMap<String,Object> hashmap1=new HashMap<String,Object>();
//                                String content1 ="";
//                                hashmap1.put("id",user_);
//                                hashmap1.put("password",pass_);
//                                hashmap1.put("clienttype","用户端");
//                                hashmap1.put("taskid","获取人脸数据");
//                                hashmap1.put("type","获取人脸数据");
//                                hashmap1.put("taskdate",content1);
//                                try {
//                                    boolean isSend = iBackService.sendMessage((new Gson()).toJson(hashmap1));//Send Content by socket
//
//                                } catch (RemoteException e) {
//                                    e.printStackTrace();
//                                }
//                                //发送数据
//                                HashMap<String,Object> hashmap=new HashMap<>();
//                                String content =token;
//                                hashmap.put("id",user_);
//                                hashmap.put("password",pass_);
//                                hashmap.put("clienttype","用户端");
//                                hashmap.put("taskid","存储人脸数据");
//                                hashmap.put("type","存储人脸数据");
//                                hashmap.put("taskdate",content);
//                                flag=0;
//                                try {
//                                    boolean isSend = iBackService.sendMessage((new Gson()).toJson(hashmap));//Send Content by socket
//                                    Looper.prepare();
//                                    Toast.makeText(MainActivity.this, isSend ? "照片存储成功" : "fail",
//                                            Toast.LENGTH_SHORT).show();
//                                    Looper.loop();
//
//                                } catch (RemoteException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        }catch (Exception e)
//                        {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }).start();
//            }catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    private void init(){
//        progress = new ProgressDialog(MainActivity.this);
//        Intent it=getIntent();
//        user_=it.getStringExtra("userID");
//        pass_=it.getStringExtra("userPassword");
//    }
//    class MessageBackReciver extends BroadcastReceiver {
//        private WeakReference<TextView> textView;
//
//        public MessageBackReciver(TextView tv) {
//            textView = new WeakReference<TextView>(tv);
//        }
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            TextView tv = textView.get();
//            if (action.equals(SocketService.HEART_BEAT_ACTION)) {
//                if (null != tv) {
//                    tv.setText("Get a heart heat");
//                }
//            } else {
//                String message = intent.getStringExtra("message");
//                char[] strChar = message.substring(0, 1).toCharArray();
//                char firstChar = strChar[0];
//                if (firstChar == '[') {
//                    Intent it0=getIntent();
//                    Intent ticket_it=new Intent();
//                    ticket_it.putExtra("data",message);
//                    ticket_it.putExtra("user",it0.getStringExtra("userID"));
//                    ticket_it.putExtra("pass",it0.getStringExtra("userPassword"));
//                    Log.i("progress",message);
//                    ticket_it.setClass(MainActivity.this, TicketActivity.class);
//                    startActivity(ticket_it);
//                    progress.dismiss();
//                }
//                else if(firstChar=='{')
//                {
//                    try {
//
//                        JSONObject json=new JSONObject(message);
//                        if(json.getString("type").equals("获取人脸数据"))
//                        {
//                            final String url1="https://api-cn.faceplusplus.com/facepp/v3/faceset/removeface";
//                            String f=json.getString("xinxi");
//                            String a[]=f.split("###");
//                            if(a[0].equals("null")||a[1].equals("null"))
//                            {
//                                Toast.makeText(MainActivity.this,"请先存照片",
//                                        Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            final HashMap<String, String> map= new HashMap<>();
//                            map.put("api_key", Constant.Key);
//                            map.put("api_secret", Constant.Secret);
//                            map.put("outer_id",user_);
//                            map.put("face_tokens",a[0]+","+a[1]);
//                            try{
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        try {
//                                            byte[] haha= FaceTest.createSet(url1,map);
//                                            String hehe=new String(haha);
//                                            Log.i("progress","删除集合"+hehe);
//
//                                            final String addface_url=" https://api-cn.faceplusplus.com/facepp/v3/faceset/addface";
//                                            final HashMap<String, String> map_addface=new HashMap<>();
//
//                                            map_addface.put("api_key", Constant.Key);
//                                            map_addface.put("api_secret", Constant.Secret);
//                                            map_addface.put("outer_id",user_);
//                                            map_addface.put("face_tokens",tokens);
//                                            //增加faceset
//                                            byte[] heng= FaceTest.createSet(addface_url, map_addface);
//                                            String str1 = new String(heng);
//                                            Log.i("progress","增加"+str1);
//
//                                        }catch (Exception e)
//                                        {
//                                            e.printStackTrace();
//                                        }
//
//                                    }
//                                }).start();
//
//                            }catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }catch (Exception e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        };
//
//    }
}
