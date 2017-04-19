package com.example.administrator.mygaodemap.View;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mygaodemap.Adapter.TicketAdapter;
import com.example.administrator.mygaodemap.BaseActivity;
import com.example.administrator.mygaodemap.Bean.Ticket;
import com.example.administrator.mygaodemap.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/4/14.
 */

public class TicketActivity extends BaseActivity {

    private ListView lv_ticket;
    private List<Ticket> list;
    private TicketAdapter adapter;

    private SwipeRefreshLayout srl_ticket;

    @Override
    public void setContentView() {
        setContentView(R.layout.ticket_activity);
        setupToolbar(true,true,"我的订单");
    }

    @Override
    public void initView() {
        if (list==null){
            list=new ArrayList<>();
            list.add(new Ticket());
            list.add(new Ticket());
            list.add(new Ticket());
            list.add(new Ticket());
            list.add(new Ticket());
            list.add(new Ticket());
            list.add(new Ticket());
        }
        lv_ticket= (ListView) findViewById(R.id.lv_ticket);
        adapter=new TicketAdapter(this,list);
        lv_ticket.setAdapter(adapter);

        srl_ticket= (SwipeRefreshLayout) findViewById(R.id.srl_ticket);
        srl_ticket.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srl_ticket.setRefreshing(false);
                        Toast.makeText(TicketActivity.this.getApplicationContext(),"加载数据测试",Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });
    }

    @Override
    public void initOperation() {

    }
}
