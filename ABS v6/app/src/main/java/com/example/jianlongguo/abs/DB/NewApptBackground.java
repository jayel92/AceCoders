package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.DisplayCurrAppt;
import com.example.jianlongguo.abs.Activities.ManageAppt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Boonyh on 3/22/2015.
 */
public class NewApptBackground extends AsyncTask<String, Void, String> {

    private Context context;
    private Exception exception;
    private String response = null;
    private String id = "";
    public NewApptBackground(Context context) {
        this.context = context;
    }
    protected String doInBackground(String... arg0) {
        try {
            String nric = (String)arg0[0];
            String desStr = (String)arg0[1];
            SimpleDateFormat from = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");
            Date date = from.parse((String) arg0[2]);       // 01-02-2014
            String dateStr = to.format(date);     // 2014-02-01
            String time = ((String) arg0[3]).substring(0,4);
            String referral = ((String)arg0[4]);
            String type = ((String)arg0[5]);
            String clinic = ((String)arg0[6]);
            id = nric;

            String link ="";
            if (clinic.equals("Dental"))
                link = "http://acecoders.netau.net/newDEN.php";
            else if (clinic.equals("ENT"))
                link = "http://acecoders.netau.net/newENT.php";
            else if (clinic.equals("Women's Health"))
                link = "http://acecoders.netau.net/newWO.php";


            String data = URLEncoder.encode("nric", "UTF-8")
                    + "=" + URLEncoder.encode(nric, "UTF-8");
            data += "&" + URLEncoder.encode("Description", "UTF-8")
                    + "=" + URLEncoder.encode(desStr, "UTF-8");
            data += "&" + URLEncoder.encode("Date", "UTF-8")
                    + "=" + URLEncoder.encode(dateStr, "UTF-8");
            data += "&" + URLEncoder.encode("Time", "UTF-8")
                    + "=" + URLEncoder.encode(time, "UTF-8");
            data += "&" + URLEncoder.encode("Referral", "UTF-8")
                    + "=" + URLEncoder.encode(referral, "UTF-8");
            data += "&" + URLEncoder.encode("Type", "UTF-8")
                    + "=" + URLEncoder.encode(type, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            // Read Server Response
            while((response = reader.readLine()) != null)
            {
                sb.append(response);
                break;
            }
            return sb.toString();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String result) {
        if(response.length() == 4) {
            Intent c = new Intent(context,DisplayCurrAppt.class);
            Bundle d = new Bundle();
            d.putString("id",id);
            c.putExtras(d);
            context.startActivity(c);
            Toast.makeText(context.getApplicationContext(), "Appointment successfully booked.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, response , Toast.LENGTH_SHORT).show();
        }


    }
}
