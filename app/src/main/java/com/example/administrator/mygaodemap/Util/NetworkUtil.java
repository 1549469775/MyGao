package com.example.administrator.mygaodemap.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by John on 2017/4/10.
 */

public class NetworkUtil {

    public static boolean isNetworkConnected(Context context){
        if (context!=null){
            ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info=manager.getActiveNetworkInfo();
            if (info!=null){
                ShowUtil.showToast(context.getApplicationContext(),"网络可用");
                return info.isAvailable();
            }else {
                ShowUtil.showToast(context.getApplicationContext(),"请检查您的网络");
            }
        }
        return false;
    }


}
