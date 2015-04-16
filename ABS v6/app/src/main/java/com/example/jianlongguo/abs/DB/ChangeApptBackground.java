package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.DisplayCurrAppt;
import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by jianlongguo on 16/4/15.
 */
public class ChangeApptBackground extends AsyncTask<String, Void, String> {

        private Context context;
        private Exception exception;
        private String response = null;
        private String id = "";
        private Patient p1 = new Patient();
        private Appointment appt = new Appointment();
        public ChangeApptBackground(Context context,Patient p1,Appointment appt) {
            this.context = context;
            this.p1 = p1;
            this.appt = appt;
        }
        protected String doInBackground(String... arg0) {
            try {
                String nric = (String)arg0[0];
                String desStr = (String)arg0[1];
                String dateStr = (String)arg0[2];
                String time = ((String) arg0[3]).substring(0,4);
                String referral = ((String)arg0[4]);
                String type = ((String)arg0[5]);


                id = nric;

                String link ="";
                if (appt.getClinic().equals("Dental"))
                    link = "http://acecoders.netau.net/editDental.php";
                else if (appt.getClinic().equals("ENT"))
                    link = "http://acecoders.netau.net/editENT.php";
                else if (appt.getClinic().equals("Women Health"))
                    link = "http://acecoders.netau.net/editWomen.php";


                String data = URLEncoder.encode("nric", "UTF-8")
                        + "=" + URLEncoder.encode(nric, "UTF-8");
                data += "&" + URLEncoder.encode("Description", "UTF-8")
                        + "=" + URLEncoder.encode(desStr, "UTF-8");
                data += "&" + URLEncoder.encode("Date", "UTF-8")
                        + "=" + URLEncoder.encode(dateStr, "UTF-8");
                data += "&" + URLEncoder.encode("Time", "UTF-8")
                        + "=" + URLEncoder.encode(time, "UTF-8");
                data += "&" + URLEncoder.encode("Referral", "UTF-8")
                        + "=" + URLEncoder.encode(referral, "UTF-8");
                data += "&" + URLEncoder.encode("Type", "UTF-8")
                        + "=" + URLEncoder.encode(type, "UTF-8");
                data += "&" + URLEncoder.encode("AppointmentNo", "UTF-8")
                        + "=" + URLEncoder.encode(appt.getId(), "UTF-8");
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
                Intent c = new Intent(context,DisplayCurrAppt.class);
                c.putExtra("Patient",new Gson().toJson(p1));

                context.startActivity(c);
                Toast.makeText(context.getApplicationContext(), "Appointment successfully changed.", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(context, response , Toast.LENGTH_SHORT).show();
            }


        }
    }

