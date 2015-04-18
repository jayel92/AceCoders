package com.example.jianlongguo.abs.DB;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class TypeCheck extends AsyncTask<String, Void, String> {

    private Context context;
    private Exception exception;
    private String response = "";
    private Activity a;
    String res = "";
    String type;
    public TypeCheck(Context context, Activity a) {
        this.a =a;
        this.context = context;
    }
    protected String doInBackground(String... arg0) {
        try {
            type = (String)arg0[0];
            String link = "http://acecoders.netau.net/fetchType.php";
            String data =  URLEncoder.encode(type, "UTF-8");
            Log.d("Response is : ", "OOOOOOOOOOOOOO");
            Log.d("Data is: ", data );
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            Log.d("Data is: ", wr.toString() );
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            // Read Server Response
            while((response = reader.readLine()) != null)
            {
                sb.append(response);
                Log.d("Response is : ", response);
                break;
            }
            res = sb.toString();
            return sb.toString();
        } catch (Exception e) {
            this.exception = e;
            return null;
        }
    }

    protected void onPostExecute(String result) {
        String[] arr = new String[5];
        if (res.length()>0) {
            for (int i = 0; i < 5; i++)
                arr[i] = "";
            arr = res.split("<br>", -1);
        }
        //delegate.processFinish(arr);


    }
}

