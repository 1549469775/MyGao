package com.example.administrator.mygaodemap.Util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by John on 2017/4/20.
 */

public class TwoToExit {
    private long lastBackKeyDownTick=0;
    private static  final long MAX_DOUBLE_BACK_DURATION=1500;
    private static TwoToExit twoToExit = null;

    public static TwoToExit newInstance() {
        if (twoToExit==null){
            twoToExit = new TwoToExit();
        }
        return twoToExit;
    }

    public void onBackPressed(Activity activity) {
        if (beforeOnBackPressed()){
            long currentTick=System.currentTimeMillis();
            if (currentTick - lastBackKeyDownTick >MAX_DOUBLE_BACK_DURATION) {
                ShowUtil.showToast(activity,"再按一次退出");
                lastBackKeyDownTick=currentTick;
            }else {
                try {
                    twoToExit.finalize();//销毁？
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
                activity.finish();
            }
        }
    }
    private boolean beforeOnBackPressed(){
        return true;
    }
}
