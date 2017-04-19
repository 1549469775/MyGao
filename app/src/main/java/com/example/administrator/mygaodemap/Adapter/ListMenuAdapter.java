package com.example.administrator.mygaodemap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mygaodemap.Bean.Menu;
import com.example.administrator.mygaodemap.R;

import java.util.List;

/**
 * Created by John on 2017/4/14.
 */

public class ListMenuAdapter extends BaseAdapter {

    private List<Menu> list;
    private Context context;

    public ListMenuAdapter(Context context,List<Menu> list) {
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
        Menu menu= (Menu) getItem(position);
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view= LayoutInflater.from(context).inflate(R.layout.menu_item,null,false);
            viewHolder=new ViewHolder();
            viewHolder.img_menu= (ImageView) view.findViewById(R.id.img_menu);
            viewHolder.img_show= (ImageView) view.findViewById(R.id.img_show);
            viewHolder.tv_name= (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_show=(TextView) view.findViewById(R.id.tv_show);;
            view.setTag(viewHolder);
        }else {
            view=convertView;
            viewHolder= (ViewHolder) view.getTag();
        }
        viewHolder.img_menu.setImageResource(menu.getImg_menu());
        viewHolder.tv_name.setText(menu.getTv_name());
        if (menu.getTv_show()!=null){
            viewHolder.tv_show.setText(menu.getTv_show());
        }
        if (menu.getImg_show()!=0){
            viewHolder.img_show.setImageResource(menu.getImg_show());
        }
        return view;
    }

    class ViewHolder{
        ImageView img_menu;
        TextView tv_name;
        ImageView img_show;
        TextView tv_show;
    }
}
