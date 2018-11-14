package com.example.soufiane.htbooking;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.FragmentManager;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtLogin,txtEmail;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Récupération des arguments passés
        int id_adherent=this.getIntent().getExtras().getInt("id_adherent");
        String Login=this.getIntent().getExtras().getString("login");
        String Email=this.getIntent().getExtras().getString("email");
        String Telephone=this.getIntent().getExtras().getString("telephone");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View headerView = navigationView.getHeaderView(0);
        txtLogin=headerView.findViewById(R.id.Logintxt);
        txtEmail=headerView.findViewById(R.id.textView);
        txtLogin.setText(Login);
        txtEmail.setText(Email);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_mesreservations) {
            Bundle bundle=new Bundle();
            bundle.putInt("id_adherent",this.getIntent().getExtras().getInt("id_adherent"));
            Class fragmentClass;
            MesReservationsFragment mrf=new MesReservationsFragment();
            fragmentClass=mrf.getClass();
            mrf.setArguments(bundle);
            android.app.FragmentManager fm=getFragmentManager();
            fm.beginTransaction().replace(R.id.frame,mrf).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
            drawer.closeDrawers();
        } else if (id == R.id.nav_réserver) {
            Bundle bundle=new Bundle();
            bundle.putInt("id_adherent",this.getIntent().getExtras().getInt("id_adherent"));
            Class fragmentClass;
            ReserverFragment rf=new ReserverFragment();
            fragmentClass=rf.getClass();
            rf.setArguments(bundle);
            android.app.FragmentManager fm=getFragmentManager();
            fm.beginTransaction().replace(R.id.frame,rf).commit();
            item.setChecked(true);
            setTitle(item.getTitle());
            drawer.closeDrawers();
        }  else if (id == R.id.nav_exit) {
            System.exit(0);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
