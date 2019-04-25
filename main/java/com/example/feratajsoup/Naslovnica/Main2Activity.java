package com.example.feratajsoup.Naslovnica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.feratajsoup.CrnaKronika.CrnaKronikaFragment;
import com.example.feratajsoup.Kultura.KulturaFragment;
import com.example.feratajsoup.R;
import com.example.feratajsoup.Radio.HitRadio;
import com.example.feratajsoup.Sport.SportFragment;
import com.example.feratajsoup.Vijesti.VijestiFragment;
import com.mancj.materialsearchbar.MaterialSearchBar;

import static java.lang.Math.min;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));






        tabLayout=(TabLayout)findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab());



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        displaySelectedScreen(R.id.nav_naslovnica);
    }


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);


        return true;
    }



    private boolean displaySelectedScreen(int id ){
        Fragment fragment=null;

        switch (id){
            case R.id.nav_naslovnica:
                fragment=new NaslovnicaFragment();
                tabLayout.getTabAt(0).setText("Naslovnica");
                break;
            case R.id.nav_vijesti:
                fragment=new VijestiFragment();
                tabLayout.getTabAt(0).setText("Vijesti");
                break;
            case R.id.nav_sport:
                fragment=new SportFragment();
                tabLayout.getTabAt(0).setText("Sport");
                break;
            case R.id.nav_kultura:
                    fragment=new KulturaFragment();
                tabLayout.getTabAt(0).setText("Kultura");
                break;
            case R.id.nav_crna_kronika:
                fragment=new CrnaKronikaFragment();
                tabLayout.getTabAt(0).setText("Crna Kronika");
                break;

        }
        if (fragment!=null){
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main,fragment);
            ft.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.hit_radio) {
            // Handle the camera action
            Intent integer=new Intent(Main2Activity.this, HitRadio.class);
            startActivity(integer);
        }

        displaySelectedScreen(id);

        return true;
    }
}
