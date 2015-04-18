package com.example.jianlongguo.abs.Drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.Entities.NavItem;

import java.util.ArrayList;

/**
 * Created by jianlongguo on 3/4/15.
 */
public class DrawerAdapter extends BaseAdapter{
    Context mContext;
    ArrayList<NavItem> mNavItems;

    public DrawerAdapter(Context context, ArrayList<NavItem> navItems){
        this.mContext = context;
        this.mNavItems = navItems;
    }

    @Override
    public int getCount(){
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_item, null);
        }
        else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.title);
        ImageView iconView = (ImageView) view.findViewById(R.id.icon);

        titleView.setText( mNavItems.get(position).mTitle );
        iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }

}
