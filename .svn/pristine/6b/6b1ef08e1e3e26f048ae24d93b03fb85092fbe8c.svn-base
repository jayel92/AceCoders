package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Boonyh on 3/22/2015.
 */
public class ForgetPassBackground extends AsyncTask<String, Void, String> {

    String user;
    private Context context;
    private Exception exception;
    private String response = "";
    public ForgetPassBackground(Context context) {
        this.context = context;
    }
    protected String doInBackground(String... arg0) {
        try {
            //String
            user = (String)arg0[0];


            String link = "http://acecoders.netau.net/resetPassword.php";
            String data = URLEncoder.encode("nric", "UTF-8")
                    + "=" + URLEncoder.encode(user, "UTF-8");
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
        Toast.makeText(context.getApplicationContext(), user ,Toast.LENGTH_SHORT).show();
        if(response.length() == 0) {
            Toast.makeText(context.getApplicationContext(), "Please check your email for new password." ,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context.getApplicationContext(), response ,Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Sorry! Reset fail, please key in a valid NRIC!", Toast.LENGTH_SHORT).show();
        }
    }
}