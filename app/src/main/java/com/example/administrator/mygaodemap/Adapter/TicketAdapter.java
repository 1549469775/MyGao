package com.example.administrator.mygaodemap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mygaodemap.Bean.Menu;
import com.example.administrator.mygaodemap.Bean.Ticket;
import com.example.administrator.mygaodemap.R;

import java.util.List;

/**
 * Created by John on 2017/4/14.
 */

public class TicketAdapter extends BaseAdapter {

    private List<Ticket> list;
    private Context context;

    public TicketAdapter(Context context, List<Ticket> list) {
        this.list=list;
        this.context=context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(context).inflate(R.layout.ticket_item,null,false);
        return view;
    }
}
