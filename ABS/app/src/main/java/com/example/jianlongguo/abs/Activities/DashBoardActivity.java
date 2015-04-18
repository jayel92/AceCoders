package com.example.jianlongguo.abs.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.jianlongguo.abs.UI.LoginUI;

/**
 * Created by jianlongguo on 13/4/15.
 */
public class DashBoardActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if user is not signed in, finish current activity
        // and launch login screen
        if (!isUserSignedIn()) {
            finish();
            startLoginActivity();
            return;
        }
        setContentView(R.layout.activity_dashboard);
        // do initialization
    }

    // retrieve access token from preferences
    public boolean isUserSignedIn() {
        //return PreferencesManager.getInstance().getAccessToken() != null;
        return true;
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginUI.class);
        startActivity(intent);
    }
}
