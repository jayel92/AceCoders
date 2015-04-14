package com.example.jianlongguo.abs;

/**
 * Created by Boonyh on 3/21/2015.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

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
        if(response.length()>0) {
            Intent i = new Intent(context, HomePage.class);

            Bundle b = new Bundle();
            b.putString("id",response);
            i.putExtras(b);

            context.startActivity(i);
        }
        else
            Toast.makeText(context, "Sorry! Incorrect Username or Password.", Toast.LENGTH_SHORT).show();



    }
}