package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.ManageProfile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Boonyh on 3/22/2015.
 */
public class ProfileEditBackground extends AsyncTask<String, Void, String> {

        private Context context;
        private Exception exception;
        private String response = null;
        private String id = "";
        public ProfileEditBackground(Context context) {
            this.context = context;
        }
        protected String doInBackground(String... arg0) {
            try {
                String user = (String)arg0[0];
                String email = (String)arg0[1];
                String address = (String)arg0[2];
                String contact = (String) arg0[3];
                String mode = ((String)arg0[4]);
                id = user;
                String link = "http://acecoders.netau.net/editProfile.php";
                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(user, "UTF-8");
                data += "&" + URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");
                data += "&" + URLEncoder.encode("address", "UTF-8")
                        + "=" + URLEncoder.encode(address, "UTF-8");
                data += "&" + URLEncoder.encode("contact", "UTF-8")
                        + "=" + URLEncoder.encode(contact, "UTF-8");
                data += "&" + URLEncoder.encode("mode", "UTF-8")
                        + "=" + URLEncoder.encode(mode, "UTF-8");

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
                Intent z = new Intent(context,ManageProfile.class);

                Bundle b = new Bundle();
                b.putString("id",id);
                z.putExtras(b);

                context.startActivity(z);
                Toast.makeText(context.getApplicationContext(), "Your profile is updated!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(context, response , Toast.LENGTH_SHORT).show();
            }


        }
}
