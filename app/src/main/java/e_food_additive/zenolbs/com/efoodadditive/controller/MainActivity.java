package e_food_additive.zenolbs.com.efoodadditive.controller;

import android.app.DialogFragment;
import android.app.Fragment;
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

import e_food_additive.zenolbs.com.efoodadditive.R;
import e_food_additive.zenolbs.com.efoodadditive.view.EFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.InfoDialog;
import e_food_additive.zenolbs.com.efoodadditive.view.SecondFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.SettingsFragment;
import e_food_additive.zenolbs.com.efoodadditive.view.ThirdFragment;

/*
//------------ Проблемы--------------------
в navigation drawer при выборе меню открывается  fragment в котором webview ,
но кожда нажимается  кнопка back то появляется просто пустой белый экран,
переопределение кнопки back не помогает все равно прискакивает через белый экран
а втором нажатии уже делает то что закодено в кнопке back (переход на главное activity)
 из-за чего так происходит   - откуда может браться эта пустая промежуточная зона?
 (пробывал фрагмент менять на activity то же самое происходит) -  проблема может быть в navigate drawer
 //------------------------
    реализовать кнопку back во фрагменте
 //--------------------------
 реализовать програмное прокручивание списка вверх

 //--------------------------
    реализовать меню в  ActionBAR
 //------------------------
 Два text view без id могут затерать друг друга - уточнить

 Сделать отспуп иначе будет текст на тексте
 //-------------------------
 */

    //public class MainActivity extends ActionBarActivity {

    public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        private static Context context;

        public static boolean allowAnimation;
        static SharedPreferences mySharedPreferences;
        public static String listChooserItem;
        public static int animId;


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
            toggle.syncState(); // the Hamburger icon

            NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this);
            //-----------------------------------------------------------


        }//-----END----onCreate()


        @Override
        public void onBackPressed() {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

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

            Fragment fragment = null;
            android.app.FragmentManager fragmentManager = getFragmentManager();

            if (id == R.id.list_item_attachment) {

                fragment = new EFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            } else if (id == R.id.item_2_attachment) {

                fragment = new SecondFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


            } else if (id == R.id.item_3_attachment) {

                fragment = new ThirdFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }


            else if (id == R.id.my_item2) {

                fragment = new SettingsFragment();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            }

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        //===================================================
        private static void loadPref(){
             mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

            //boolean getBoolean(String key, boolean defValue);
            boolean my_checkbox_preference = mySharedPreferences.getBoolean("chb_animation", false);
            allowAnimation=my_checkbox_preference;
        }

        public static void loadSettingsAnimation() {

            loadPref();
            String listValue = mySharedPreferences.getString("list_chooser", "не выбрано");

            listChooserItem =listValue;

            switch (listValue){
                case "1" :
                    animId =  R.anim.scaling_increase;
                    break;
                case "2":
                    animId =  R.anim.scaling_protrusion;
                    break;
                case "3":
                    animId =  R.anim.scaling_descent;
                    break;
            }

        }

        private void loadFragment() {
            Fragment fragment = null;
            fragment = new EFragment();

            if (fragment != null) {

                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment).commit();

            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }

        }

        //-----------------------------------
        protected void onResume() {

            loadSettingsAnimation();
            loadFragment();

            super.onResume();
        }




    }
