package com.example.jianlongguo.abs.Drawer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jianlongguo.abs.Activities.R;
import com.example.jianlongguo.abs.Entities.Appointment;

import java.util.ArrayList;

/**
 * Created by jianlongguo on 13/4/15.
 */
public class ApptAdapter extends ArrayAdapter<Appointment> {
    Context mContext;
    int layoutResourceId;
    private ArrayList<Appointment> items;

    public ApptAdapter(Context context, int layoutResourceId, ArrayList<Appointment> items) {
        super(context, layoutResourceId, items);
        this.layoutResourceId = layoutResourceId;
        this.mContext = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.appt_list, null);
        }
        Appointment appt = items.get(position);
        if (appt != null) {
            TextView clinic = (TextView) v.findViewById(R.id.clinic);
            TextView type = (TextView) v.findViewById(R.id.type);
            TextView date = (TextView) v.findViewById(R.id.date);
            TextView time = (TextView) v.findViewById(R.id.time);
            if (clinic != null) {
                clinic.setText(appt.getClinic());
                if (type != null)
                    type.setText("Type: " + appt.getType());
                if (date != null)
                    date.setText("Date: " + appt.getDate());
                if (time != null)
                    time.setText("Time: " + appt.getTime() + "hrs");
            }
        }
        return v;

    }

    @Override
    public int getCount(){
        return items.size();
    }

    @Override
    public Appointment getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
