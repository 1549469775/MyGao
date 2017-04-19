package com.example.administrator.mygaodemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

/**
 * Created by John on 2017/3/24.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initView();
        initOperation();
    }

    public void setupToolbar(boolean home,boolean showtitle,String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(home);//设置toolbar返回可用true
        getSupportActionBar().setDisplayShowTitleEnabled(showtitle);//false
    }

    public void setupToolbar(boolean home,boolean showtitle) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(home);//设置toolbar返回可用true
        getSupportActionBar().setDisplayShowTitleEnabled(showtitle);//false
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    //每當用戶從操作欄中選擇在應用程序的活動層次結構中向上導航時，將調用此方法，你可以看成返回键吧
    @Override
    public boolean onSupportNavigateUp() {//如果在此活動的清單中指定了父級或其活動別名，則會自動處理默認向上導航。
        onBackPressed();
        return true;
    }

    public abstract void setContentView();
    public abstract void initView();
    public abstract void initOperation();

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}
