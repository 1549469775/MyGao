package com.example.administrator.mygaodemap.Bean;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by John on 2017/4/14.
 */

public class Menu {

    private int img_menu;
    private String tv_name;
    private int img_show;
    private String tv_show;
    private Context context;
    private Class aClass;

    public Menu(Context context,Class aClass,int img_menu, String tv_name) {
        this.img_menu = img_menu;
        this.tv_name = tv_name;
        this.aClass=aClass;
        this.context=context;
    }

    public Menu(Context context,Class aClass,int img_menu, String tv_name, int img_show, String tv_show) {
        this.img_menu = img_menu;
        this.tv_name = tv_name;
        this.img_show = img_show;
        this.tv_show = tv_show;
        this.aClass=aClass;
        this.context=context;
    }

    public int getImg_menu() {
        return img_menu;
    }

    public void setImg_menu(int img_menu) {
        this.img_menu = img_menu;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public void toActivity(){
        if (aClass==null){
            Toast.makeText(context.getApplicationContext(),getTv_name(),Toast.LENGTH_SHORT).show();
        }else {
            context.startActivity(new Intent(context,aClass));
        }
    }



    public int getImg_show() {
        return img_show;
    }

    public void setImg_show(int img_show) {
        this.img_show = img_show;
    }

    public String getTv_show() {
        return tv_show;
    }

    public void setTv_show(String tv_show) {
        this.tv_show = tv_show;
    }
}
