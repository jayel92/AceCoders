package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.jianlongguo.abs.Entities.Appointment;
import com.example.jianlongguo.abs.Entities.Patient;
import com.example.jianlongguo.abs.UI.DispCurrApptUI;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class DeleteBackground extends AsyncTask<Void, Void, String> {

        private Context context;
        private Exception exception;
        private String response = "";
        private Patient p1 = new Patient();
        private Appointment appt = new Appointment();

        public DeleteBackground(Context context, Patient p1, Appointment appt) {
            this.context = context;
            this.p1 = p1;this.appt = appt;
        }

        protected String doInBackground(Void... arg0) {
            try {
                String link = "";
                if (appt.getClinic().equals("Dental"))
                    link = "http://acecoders.netau.net/deleteDental.php";
                else if (appt.getClinic().equals("ENT"))
                    link = "http://acecoders.netau.net/deleteENT.php";
                else if (appt.getClinic().equals("Women Health"))
                    link = "http://acecoders.netau.net/deleteWomen.php";


                String data = URLEncoder.encode("nric", "UTF-8")
                        + "=" + URLEncoder.encode(p1.getNric(), "UTF-8");
                data += "&" + URLEncoder.encode("id", "UTF-8")
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
            if(response.length() == 0) {
                Toast.makeText(context.getApplicationContext(), "Your appointment is deleted!", Toast.LENGTH_SHORT).show();


                Intent z = new Intent(context,DispCurrApptUI.class);

                z.putExtra("Patient",new Gson().toJson(p1));
                z.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(z);

            }
            else {
                Toast.makeText(context, response , Toast.LENGTH_SHORT).show();
            }


        }

}
