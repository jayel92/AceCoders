package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.ManageProfile;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Boonyh on 3/22/2015.
 */
public class ProfileEditBackground extends AsyncTask<Void, Void, String> {

        private Context context;
        private Exception exception;
        private String response = null;
        private Patient p1 = new Patient();
        private String pwd,email,address,contact,mode;

        public ProfileEditBackground(Context context, Patient p1, String pwd, String email, String address, String contact, String mode) {
            this.context = context;
            this.p1 = p1;
            this.pwd = pwd;
            this.email = email;
            this.address = address;
            this.contact = contact;
            this.mode = mode;
        }

        protected String doInBackground(Void... arg0) {
            try {



                String link = "http://acecoders.netau.net/editProfile.php";
                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(p1.getNric(), "UTF-8");
                data += "&" + URLEncoder.encode("pwd", "UTF-8")
                        + "=" + URLEncoder.encode(pwd, "UTF-8");
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
            if(response.length() == 0) {
                Toast.makeText(context.getApplicationContext(), "Your profile is updated!", Toast.LENGTH_SHORT).show();
                p1.setPassword(pwd);
                p1.setEmail(email);
                p1.setAddress(address);
                p1.setContact(contact);
                p1.setMOC(mode);
                Toast.makeText(context.getApplicationContext(), mode, Toast.LENGTH_SHORT).show();


                Intent z = new Intent(context,ManageProfile.class);

                z.putExtra("Patient",new Gson().toJson(p1));

                context.startActivity(z);

            }
            else {
                Toast.makeText(context, response , Toast.LENGTH_SHORT).show();
            }


        }
}
