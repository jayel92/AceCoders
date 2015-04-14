package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.MainActivity;

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
public class RegisterBackground extends AsyncTask<String, Void, String> {

    private Context context;
    private Exception exception;
    private String response = "";
    public RegisterBackground(Context context) {
        this.context = context;
    }
    protected String doInBackground(String... arg0) {
        try {
            String user = (String)arg0[0];
            String pass = (String)arg0[1];
            String name = (String)arg0[2];
            String email = (String)arg0[3];
            String address = (String)arg0[4];
            String gender = (String)arg0[5];
            String contact = (String) arg0[6];
            String mode = ((String)arg0[7]);

            SimpleDateFormat from = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat to = new SimpleDateFormat("yyyy-MM-dd");

            Date date = from.parse((String) arg0[8]);       // 01-02-2014
            String mysqlString = to.format(date);     // 2014-02-01

            String link = "http://acecoders.netau.net/newAcct.php";
            String data = URLEncoder.encode("username", "UTF-8")
                    + "=" + URLEncoder.encode(user, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(pass, "UTF-8");
            data += "&" + URLEncoder.encode("name", "UTF-8")
                    + "=" + URLEncoder.encode(name, "UTF-8");
            data += "&" + URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data += "&" + URLEncoder.encode("address", "UTF-8")
                    + "=" + URLEncoder.encode(address, "UTF-8");
            data += "&" + URLEncoder.encode("gender", "UTF-8")
                    + "=" + URLEncoder.encode(gender, "UTF-8");
            data += "&" + URLEncoder.encode("contact", "UTF-8")
                    + "=" + URLEncoder.encode(contact, "UTF-8");
            data += "&" + URLEncoder.encode("mode", "UTF-8")
                    + "=" + URLEncoder.encode(mode, "UTF-8");
            data += "&" + URLEncoder.encode("dob", "UTF-8")
                    + "=" + URLEncoder.encode(mysqlString, "UTF-8");
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
            Intent i = new Intent(context,MainActivity.class);
            context.startActivity(i);
            Toast.makeText(context.getApplicationContext(), "Registration Successful." ,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Sorry! Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Please ensure that your NRIC is unique.", Toast.LENGTH_SHORT).show();
        }
    }
}