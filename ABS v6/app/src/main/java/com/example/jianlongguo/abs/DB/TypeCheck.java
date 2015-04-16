package com.example.jianlongguo.abs.DB;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jianlongguo.abs.Activities.AsyncResponse;
import com.example.jianlongguo.abs.Activities.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by jianlongguo on 15/4/15.
 */
public class TypeCheck extends AsyncTask<String, String, String> {


    private String response = null;
    private Context c;
    private Activity a;

    public AsyncResponse delegate=null;
    private Exception exception;
    private String type;

    public ArrayList<String> arr = new ArrayList<>(5);

    public TypeCheck(Context c, Activity a) {
        this.c = c;
        this.a = a;
    }

    protected String doInBackground(String... arg0) {
        try {
            type = (String) arg0[0];



            String link = "http://acecoders.netau.net/fetchType.php";
            String data = URLEncoder.encode("type", "UTF-8")
                    + "=" + URLEncoder.encode(type, "UTF-8");

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
/*After adaptor is pass into background similarly to context, at postExecute method, set the adaptor stuff method
s here with the info gotten, it will be arr[0] to arr[4] then after update adaptor stuff, do onNOtifyChanged similar to displaycurrentAppt last method before this u need either
a add function or clear or edit or dlush of the adapter*/
@Override
    protected void onPostExecute(String result) {
    // TODO Auto-generated method stub
    //access UI elements here..
    if (result.length()>0) {
        String[] arr = new String[5];
        for (int i = 0; i < 5; i++)
            arr[i] = "";
        arr = result.split("<br>", -1);
    }
    //   if(response.length() >0) {
    Spinner mySpinner = (Spinner) a.findViewById(R.id.typeSpinner);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, arr);

    Toast.makeText(c, type + result + "HEY", Toast.LENGTH_SHORT).show();
//    delegate.processFinish(result);
        /*    Spinner mySpinner = (Spinner) activity.findViewById(R.id.typeSpinner);
            myAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_spinner_item, apptArray);
            myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
            myAdapter.notifyDataSetChanged();
            //....*/
    super.onPostExecute(result);

    // }
         /*   Intent i = new Intent(context, NewAppt.class);
            i.putExtra("apptArray",new Gson().toJson(apptArray));
            i.putExtra("myAdapter",new Gson().toJson(myAdapter));
            context.startActivity(i);
        }
        else {
            Toast.makeText(context, "Sorry! Registration Failed. Please try again.", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Please ensure that your NRIC is unique.", Toast.LENGTH_SHORT).show();
        }*/
    }

}

