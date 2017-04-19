package com.example.administrator.mygaodemap.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.mygaodemap.R;
import com.example.administrator.mygaodemap.View.MainActivity;

/**
 * Created by John on 2017/4/14.
 */

public class DialogCreate {

    static Ringtone r;
    public static void rawList(final Context context, final TextView tv_name){
        AlertDialog dialog=new AlertDialog.Builder(context)
                .setTitle("raw")
                .setSingleChoiceItems(new String[]{"放个屁先", "笑声", "小新", "女鬼"}, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                    Uri notification0 = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.pi);
                                    r = RingtoneManager.getRingtone(context.getApplicationContext(), notification0);
                                    if (r.isPlaying()){
                                        r.stop();

                                    }else {
                                        r.play();
                                        tv_name.setText("放个屁先");
                                    }
                                break;
                            case 1:
                                    Uri notification1 = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.a);
                                    r = RingtoneManager.getRingtone(context.getApplicationContext(), notification1);
                                    if (r.isPlaying()){
                                        r.stop();
                                    }else {
                                        r.play();
                                        tv_name.setText("笑声");
                                    }
                                break;
                            case 2:
                                    Uri notification2 = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.b);
                                    r = RingtoneManager.getRingtone(context.getApplicationContext(), notification2);
                                    if (r.isPlaying()){
                                        r.stop();
                                    }else {
                                        r.play();
                                        tv_name.setText("小新");
                                    }
                                break;
                            case 3:
                                    Uri notification3 = Uri.parse("android.resource://"+context.getPackageName()+"/"+ R.raw.c);
                                    r = RingtoneManager.getRingtone(context.getApplicationContext(), notification3);
                                    if (r.isPlaying()){
                                        r.stop();
                                    }else {
                                        r.play();
                                        tv_name.setText("女鬼");
                                    }
                                break;
                            default:
                                break;
                        }
                    }
                })
                .setPositiveButton("确认",null)
                .create();
        dialog.show();
    }

    //地图选择起点终点的对话框
    private static int position=0;
    public static void mapAction(final Context context, final AMap aMap, LatLng latLng){
        final String[] tit=new String[]{"起点","终点"};
        final LatLng latLng1=latLng;
        AlertDialog dialog=new AlertDialog.Builder(context)
                .setTitle("Choose")
                .setSingleChoiceItems(tit, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position=which;
                        Toast.makeText(context.getApplicationContext(),tit[which],Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (position){
                            case 0:
                                MarkerOptions otMarkerOptions0 = new MarkerOptions();
                                otMarkerOptions0.position(latLng1);
//                                otMarkerOptions0.icon(BitmapDescriptorFactory.fromBitmap(
//                                        BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_st)));
                                aMap.addMarker(otMarkerOptions0);
                                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng1));
                                break;
                            case 1:
                                MarkerOptions otMarkerOptions1 = new MarkerOptions();
                                otMarkerOptions1.position(latLng1);
//                                otMarkerOptions1.icon(BitmapDescriptorFactory.fromBitmap(
//                                        BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_en)));
                                aMap.addMarker(otMarkerOptions1);
                                aMap.moveCamera(CameraUpdateFactory.changeLatLng(latLng1));
                                break;
                        }
                    }
                })
                .create();
        dialog.show();
    }

    public static void mapDelete(final Context context, final Marker marker){
        AlertDialog dialog=new AlertDialog.Builder(context)
                .setTitle("删除")
                .setMessage("想要删除这个点吗")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        marker.remove();
                    }
                })
                .create();
        dialog.show();
    }

}
