package com.example.jianlongguo.abs.DB;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by jianlongguo on 11/4/15.
 */
public class NetworkAvailability extends AsyncTask<String, Void, String> {

    Context context;

    public NetworkAvailability(Context contextin)
    { context = contextin;}

    protected String doInBackground(String... arg0) {
        try {
            if (!this.isOnline()) {
                Toast.makeText(context.getApplicationContext(), "No Internet connection! Please ensure that you have access to the Internet!", Toast.LENGTH_LONG).show();
            };
        } catch (Exception e) {
            return null;
        }
        return "ok";
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
