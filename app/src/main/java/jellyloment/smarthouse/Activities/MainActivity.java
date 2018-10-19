package jellyloment.smarthouse.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import jellyloment.smarthouse.Fragments.AyudaFragment;
import jellyloment.smarthouse.Fragments.CameraFragment;
import jellyloment.smarthouse.Fragments.LightFragment;
import jellyloment.smarthouse.Fragments.InfoFragment;
import jellyloment.smarthouse.R;
import jellyloment.smarthouse.Fragments.StatusFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    boolean simulacion = false, sensor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Cuando inicia, se ven las camaras
        String sTitle = "Camaras";
        Fragment miFragment = new CameraFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(sTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement4
        switch (id) {
            case R.id.on_off:
                if (simulacion == false) {
                    Toast.makeText(this, "Simulación activada", Toast.LENGTH_LONG).show();
                    simulacion = true;
                } else if(simulacion == true)  {
                    Toast.makeText(this, "Simulación desactivada", Toast.LENGTH_LONG).show();
                    simulacion = false;
                }
                break;
            case R.id.sensor_mov:
                if (sensor == false) {
                    Toast.makeText(this, "Sensor activado", Toast.LENGTH_LONG).show();
                    sensor = true;
                } else if(sensor == true)  {
                    Toast.makeText(this, "Sensor desactivado", Toast.LENGTH_LONG).show();
                    sensor = false;
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment = null;
        String sTitle = "SmartHouse";
        boolean fragmentSeleccionado = false;

        switch (id){
            case R.id.nav_camera:
                sTitle = "Camaras";
                miFragment = new CameraFragment();
                fragmentSeleccionado = true;
                break;
            case R.id.nav_lights:
                sTitle = "Focos";
                miFragment = new LightFragment();
                fragmentSeleccionado = true;
                break;
            case R.id.nav_status:
                sTitle = "Estado";
                miFragment = new StatusFragment();
                fragmentSeleccionado = true;
                break;
            case R.id.nav_config:
                //sTitle = "Focos";
                //miFragment = new LightFragment();
                //fragmentSeleccionado = true;
                break;
            case R.id.nav_help:
                sTitle = "Ayuda";
                miFragment = new AyudaFragment();
                fragmentSeleccionado = true;
                break;
            case R.id.nav_info:
                sTitle = "Contactanos";
                miFragment = new InfoFragment();
                fragmentSeleccionado = true;
                break;
        }
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(sTitle);
        
        if (fragmentSeleccionado == true){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
