package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Boonkc on 3/22/2015.
 */
public class ProfileBackground extends AsyncTask<String, Void, String> {

        private Context context;
        private Exception exception;
        private String response = null;
        private String res = null;
        TextView nricTxt, pwdTxt, emailTxt, addTxt, contactTxt, dobTxt;

        public ProfileBackground(Context context,TextView nricTxt,TextView pwdTxt ,TextView emailTxt ,TextView addTxt ,TextView contactTxt,TextView dobTxt ) {
            this.context = context;
            this.nricTxt = nricTxt;
            this.pwdTxt = pwdTxt;
            this.emailTxt = emailTxt;
            this.addTxt = addTxt;
            this.contactTxt = contactTxt;
            this.dobTxt = dobTxt;
        }
        protected String doInBackground(String... arg0) {
            try {
                String name = (String)arg0[0];
                String link = "http://acecoders.netau.net/Profile.php";
                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(name, "UTF-8");
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
                String[] arr = result.split("<br>");
                this.nricTxt.setText(result);
                //this.pwdTxt.setText(arr[2]);
                //this.emailTxt.setText(arr[3]);
                //this.addTxt.setText(arr[4]);
                //this.contactTxt.setText(arr[6]);
                //this.dobTxt.setText(arr[8]);

            }
            else
                Toast.makeText(context, "Unable to load profile.", Toast.LENGTH_SHORT).show();

        }
    }