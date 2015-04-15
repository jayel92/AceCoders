package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.AsyncResponse;
import com.example.jianlongguo.abs.Activities.NewAppt;
import com.example.jianlongguo.abs.Drawer.ApptAdapter;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class TypeCheck extends AsyncTask<String, String, String> {

    private Context context;
    private Exception exception;
    private String response = null;
    private String res = null;
    Patient p1 = new Patient();
    Appointment appt = new Appointment();
    public AsyncResponse delegate=null;
    private ApptAdapter myAdapter;
    ArrayList<String> apptArray = new ArrayList<>();

    public TypeCheck(Context context) {
        this.context = context;
    }

    protected String doInBackground(String... arg0) {
        try {
            String type = (String)arg0[0];

            String link = "http://acecoders.netau.net/fetchType.php";
            String data = URLEncoder.encode("type", "UTF-8")
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
/*After adaptor is pass into background similarly to context, at postExecute method, set the adaptor stuff method
s here with the info gotten, it will be arr[0] to arr[4] then after update adaptor stuff, do onNOtifyChanged similar to displaycurrentAppt last method before this u need either
a add function or clear or edit or dlush of the adapter*/
    protected void onPostExecute(String result) {
        if(response.length() >0) {
            String[] arr = new String[14];
            for(int i=0;i<14; i++)
                arr[i] = "";
            arr = response.split("<br>",-1);
            for(int j=arr.length;j>0;j--)
               apptArray.add(arr[j]);
            myAdapter.notifyDataSetChanged();

            Intent i = new Intent(context, NewAppt.class);
            i.putExtra("apptArray",new Gson().toJson(apptArray));
            i.putExtra("myAdapter",new Gson().toJson(myAdapter));
            context.startActivity(i);
        }
        else {
            Toast.makeText(context, "Sorry! Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Please ensure that your NRIC is unique.", Toast.LENGTH_SHORT).show();
        }
    }
}

