package com.example.jianlongguo.abs.DB;

/**
 * Created by Boonyh on 3/21/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.DisplayCurrAppt;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class LoginBackground extends AsyncTask<String, Void, String> {

    private Context context;
    private Exception exception;
    private String response = null;
    private String res = null;

    public LoginBackground(Context context) {
        this.context = context;
    }


    protected String doInBackground(String... arg0) {
        try {
            String name = (String)arg0[0];
            String pass = (String)arg0[1];
            String link = "http://acecoders.netau.net/loginDB.php";
            String data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(name, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(pass, "UTF-8");
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
        try {
            if (response.length() > 0) {
                String[] arr = new String[14];
                for (int i = 0; i < 14; i++)
                    arr[i] = "";
                arr = result.split("<br>", -1);
                Patient pat = new Patient(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9], arr[10], arr[11], arr[12]);

                Intent i = new Intent(context, DisplayCurrAppt.class);
                i.putExtra("Patient", new Gson().toJson(pat));
                context.startActivity(i);
            } else
                Toast.makeText(context, "Sorry! Incorrect Username or Password.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Database connection is unavailable now. Please try again later", Toast.LENGTH_SHORT).show();
        }



    }
}