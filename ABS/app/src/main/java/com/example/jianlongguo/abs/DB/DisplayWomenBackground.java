package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.os.AsyncTask;

import com.example.jianlongguo.abs.Activities.AsyncResponse;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


/**
 * Created by Boonkc on 4/14/2015.
 */
public class DisplayWomenBackground extends AsyncTask<Object, String, Appointment> {

    private Context context;
    private Exception exception;
    private String response = null;
    private String res = null;
    Patient p1 = new Patient();
    Appointment appt = new Appointment();
    public AsyncResponse delegate=null;


    public DisplayWomenBackground(Context context, Patient p1) {
        this.context = context;
        this.p1 = p1;

    }

    protected Appointment doInBackground(Object... arg0) {
        try {
            String link = "http://acecoders.netau.net/viewWomen.php";

            String data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(p1.getNric(), "UTF-8");

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
            res =  sb.toString();
            if (res.length()>0){
                String[] arr = new String[14];
                for(int i=0;i<14; i++)
                    arr[i] = "";
                arr = res.split("<br>",-1);
                appt  = new Appointment(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5],arr[6],arr[7]);

                return appt;
           }
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
        return null;
    }



    protected void onPostExecute(Appointment result){

        delegate.processFinish(appt);


    }
}