package com.example.jianlongguo.abs.Activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jianlongguo.abs.Drawer.DrawerAdapter;
import com.example.jianlongguo.abs.Drawer.NavItem;
import com.example.jianlongguo.abs.Entities.Patient;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BaseActivity extends ActionBarActivity implements View.OnClickListener{

    //nav drawer title
    private CharSequence mDrawerTitle;
    //used to store app title
    private CharSequence mTitle;
    private ArrayList<NavItem>navDrawerItems;
    private DrawerAdapter adapter;

    public String userid;
    public ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    String id = "";
    public String[] layers;
    ArrayList<NavItem> mNavItems = new ArrayList<>();
    Context context;
    public static Patient p1 = new Patient();

    protected void onCreate(Bundle savedInstanceState) {
        // DrawerLayout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_item);
        String jsonMyObject = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonMyObject = extras.getString("Patient");
        }
        p1 = new Gson().fromJson(jsonMyObject, Patient.class);
    }

    public void set(String[]navMenuTitles,TypedArray navMenuIcons) {
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navList);

        navDrawerItems = new ArrayList<>();

        //adding nav drawer items
        if (navMenuIcons == null) {
            for (int i = 0; i < navMenuTitles.length; i++) {
                navDrawerItems.add(new NavItem(navMenuTitles[i]));
            }
        } else {
            for (int i = 0; i < navMenuTitles.length; i++) {
                navDrawerItems.add(new NavItem(navMenuTitles[i], navMenuIcons.getResourceId(i, -1)));
            }
        }
        SlideMenuClickListener listener = new SlideMenuClickListener();

        mDrawerList.setOnItemClickListener(listener);
        //setting the nav drawer list adapter
        adapter = new DrawerAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        //enabling action bar app icon and behaving it as a toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setIcon(xx);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Menu");
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }



    private class SlideMenuClickListener implements ListView.OnItemClickListener {

        public SlideMenuClickListener(){}
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
// display view for selected nav drawer item
            displayView(position);
            //selectItemFromDrawer(position);
        }
    }



    private void displayView(int position) {
// update the main content by replacing fragments
        switch (position) {
            case 0:
                Intent intent = new Intent(this, NewAppt.class);
                Gson gson = new Gson();
                String myJson = gson.toJson(p1);
                intent.putExtra("Patient",myJson);
                startActivity(intent);
                finish();
                break;
            case 1:
                Intent intent1 = new Intent(this, DisplayCurrAppt.class);
                Gson gson1 = new Gson();
                String myJson1 = gson1.toJson(p1);
                intent1.putExtra("Patient",myJson1);
                startActivity(intent1);
                finish();
                break;
            case 2:
                Intent k;
                Gson gson2 = new Gson();
                String myJson2 = gson2.toJson(p1);
                k = new Intent(this,ManageProfile.class);
                k.putExtra("Patient",myJson2);
                if (this instanceof NewAppt){
                    onBackPressed();
                    onPause();
                }
                startActivity(k);
                finish();
                break;
            case 3:
                Intent intent3 = new Intent(this, ContactUsActivity.class);
                Gson gson3 = new Gson();
                String myJson3 = gson3.toJson(p1);
                intent3.putExtra("Patient",myJson3);
                startActivity(intent3);
                finish();
            default:
                break;
        }

// update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        //mDrawerLayout.closeDrawer(R.layout.drawer_item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
// Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
// if nav drawer is opened, hide the action items
// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /** Called when a particular item from the navigation drawer
    * is selected. */
    private void selectItemFromDrawer(int position) {
        /*Fragment fragment = new PreferencesFragment();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, fragment)
                .commit();*/
        Intent k = new Intent();

        switch(mNavItems.get(position).mTitle) {
            case "Appointments":
                k.setClass(this,ManageAppt.class);
                startActivity(k);
                break;
            case "Profile":
                k.setClass(this,ManageProfile.class);
                startActivity(k);
                break;

        }


        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //this method handles action bar item clicks
        int id = item.getItemId();

        //pass the event to ActionBarDrawerToggle
        //if it returns true, then it has handled the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //handling the rest of the action bar items
        switch (item.getItemId()){
            case R.id.logout:
                createLogoutDialog();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

 /*   // Called when invalidateOptionsMenu() is invoked
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public void onBackPressed() {
        createDialog();
    }

    private void createDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to exit?");
        alertDlg.setCancelable(false); // We avoid that the dialong can be cancelled, forcing the user to choose one of the options

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                BaseActivity.super.onBackPressed();
            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // We do nothing
            }
        });
        alertDlg.create().show();
        onPause();
    }

    private void createLogoutDialog() {
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
        alertDlg.setMessage("Are you sure you want to logout?");
        alertDlg.setCancelable(false); // We avoid that the dialong can be cancelled, forcing the user to choose one of the options

        alertDlg.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //clears history
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Logout successful!", Toast.LENGTH_SHORT).show();
            }
        });

        alertDlg.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // We do nothing
            }
        });
        alertDlg.create().show();
        onPause();
    }


}

