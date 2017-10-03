package e_food_additive.zenolbs.com.efoodadditive.controller;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import e_food_additive.zenolbs.com.efoodadditive.App;
import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.view.ETableFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.InfoDialog;
import e_food_additive.zenolbs.com.efoodadditive.view.SecondFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.settings.SettingsFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.ThirdFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        private static Context context;
        static SharedPreferences mySharedPreferences;


        boolean restraint;
        Fragment fragment = null;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            context=this;

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            //------------------- navigationDrawer----------------------
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState(); // the Hamburger

            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this);
            //-----------------------------------------------------------

        }//-----END----onCreate()

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.action_settings) {

                 DialogFragment infoDialog = new InfoDialog();
                 infoDialog.show(getFragmentManager(), "InfoApp");

                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        //---------------------------------
        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view listChooserItem clicks here.
            int id = item.getItemId();
            FragmentManager fragmentManager = getFragmentManager();

            if (id == R.id.list_item_attachment) {

                fragment = new ETableFragment();
                restraint =true;
            } else if (id == R.id.item_2_attachment) {

                fragment = new SecondFragment();
                restraint =false;
            } else if (id == R.id.item_3_attachment) {

                fragment = new ThirdFragment();
                restraint =false;
            }
            else if (id == R.id.my_item2) {

                fragment = new SettingsFragment();
                restraint =false;

            }

            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        public static void loadSettingsAnimation() {

            loadPref();
            String listValue = mySharedPreferences.getString("list_chooser", "increase");
            App.listChooserItem =listValue;

            switch (listValue){
                case "01" :
                    App.animId =  R.anim.scaling_increase;
                    break;
                case "02":
                    App.animId =  R.anim.scaling_protrusion;
                    break;
                case "03":
                    App.animId =  R.anim.scaling_descent;
                    break;
                default:
                    App.animId =  R.anim.scaling_increase;
            }

        }

        private static void loadPref(){
            mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            boolean my_checkbox_preference = mySharedPreferences.getBoolean("chb_animation", false);
            App.allowAnimation=my_checkbox_preference;
        }

        private void loadFragment() {
            Fragment fragment = null;
            fragment = new ETableFragment();

            if (fragment != null) {

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();

            } else {
                Log.e("MainActivity", "Error in creating fragment");
            }

        }

        protected void onResume() {
            super.onResume();

            loadSettingsAnimation();
            loadFragment();
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }
            else if(!restraint) {

                fragment = new ETableFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();

                restraint =true;
            }else{
                super.onBackPressed();
            }
        }

}
