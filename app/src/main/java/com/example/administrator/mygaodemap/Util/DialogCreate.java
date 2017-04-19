package com.example.administrator.mygaodemap.Util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.example.administrator.mygaodemap.Bean.BeanUtil;
import com.example.administrator.mygaodemap.Bean.Ticket;
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

    private static Ticket ticket;
    public static void fillMessage(final Context context){
        ticket=new Ticket();
        View view= LayoutInflater.from(context).inflate(R.layout.layout_ticket,null,false);
        final EditText weight=(EditText)view.findViewById(R.id.weight);
        final EditText beizhu=(EditText)view.findViewById(R.id.beizhu);
        final EditText phone=(EditText)view.findViewById(R.id.telephone);
        final EditText sender=(EditText)view.findViewById(R.id.sender);
        final EditText reciever=(EditText)view.findViewById(R.id.reciver);
        final EditText  etStartTime=(EditText)view.findViewById(R.id.time_);
        Spinner spinner=(Spinner)view.findViewById(R.id.Spi_ispersonal);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] someone=context.getResources().getStringArray(R.array.someone);
                ticket.setType(someone[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        TextView time_choose=(TextView)view.findViewById(R.id.time_choose);
        time_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeDialog(context,etStartTime);;
            }
        });
        AlertDialog dialog=new AlertDialog.Builder(context)
                .setTitle("填写")
                .setView(view)
                .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ticket.setFromWho(sender.getText().toString());
                        ticket.setToWho(reciever.getText().toString());
                        ticket.setWantTime(etStartTime.getText().toString());
                        ticket.setWeight(weight.getText().toString());
                        ticket.setNumber(phone.getText().toString());
                        ticket.setNote(beizhu.getText().toString());
                        BeanUtil.setTicket(ticket);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create();
        dialog.show();
    }

    public static void showTimeDialog(Context context, final EditText editText){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view= LayoutInflater.from(context).inflate(R.layout.time_dialog,null);
        builder.setView(view);
        final TimePicker timePicker1= (TimePicker) view.findViewById(R.id.time_picker);
        timePicker1.setIs24HourView(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //因为不能时间选择一开始是当前时间
                String hour=timePicker1.getCurrentHour()+"";
                String minute=timePicker1.getCurrentMinute()+ "";

                StringBuffer sb = new StringBuffer();
                sb.append(hour)
                        .append(":").append(minute);
                editText.setText(sb.toString());
                editText.setTextColor(Color.BLACK);
                dialog.cancel();
            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
}
