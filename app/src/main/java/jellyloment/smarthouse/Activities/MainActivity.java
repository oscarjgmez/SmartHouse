package jellyloment.smarthouse.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import jellyloment.smarthouse.Fragments.AyudaFragment;
import jellyloment.smarthouse.Fragments.CameraFragment;
import jellyloment.smarthouse.Fragments.ConfiguracionFragment;
import jellyloment.smarthouse.Fragments.LightFragment;
import jellyloment.smarthouse.Fragments.InfoFragment;
import jellyloment.smarthouse.R;
import jellyloment.smarthouse.Fragments.StatusFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;

    private DatabaseReference mDatabaseS;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private TextView txvUsuario, txvColonia;
    private String usuario = "", colonia = "";
    private String[] config;
    private boolean alarma = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{
            BufferedReader fin0 = new BufferedReader(new InputStreamReader(openFileInput("colonia_usuario.txt")));

            colonia = fin0.readLine();
            fin0.close();
            BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("usuario_iniciado.txt")));

            usuario = fin.readLine();
            fin.close();
        }catch (Exception ex){
            Toast.makeText(this, "Error al leer fichero a memoria interna", Toast.LENGTH_LONG).show();
        }

        NavigationView navigationView = findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);

        txvColonia = v.findViewById(R.id.txvDireccion);
        txvUsuario = v.findViewById(R.id.txvNombreUsuario);

        txvUsuario.setText(usuario);
        txvColonia.setText(colonia);

        final FloatingActionButton fab = findViewById(R.id.fabAlarma);

        mDatabaseS = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("Puerta");
        mDatabaseS.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valor = dataSnapshot.getValue().toString();
                if (valor.equals("true")){
                    fab.setEnabled(true);
                } else {
                    fab.setEnabled(false);
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseS = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("Ventana");
        mDatabaseS.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valor = dataSnapshot.getValue().toString();
                if (valor.equals("true")){
                    fab.setEnabled(true);
                } else {
                    fab.setEnabled(false);
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseS = FirebaseDatabase.getInstance().getReference(usuario).child("Alarma");
        mDatabaseS.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String alarmaF = dataSnapshot.getValue().toString();
                if (alarmaF.equals("true")){
                    alarma = true;
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Naranja)));
                } else {
                    alarma = false;
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Gris)));
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                DatabaseReference myRef = database.getReference(usuario).child("Alarma");
                if (!alarma){
                    alarma = true;
                    myRef.setValue("true");
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Naranja)));
                    Snackbar.make(view, "Alarma activada", Snackbar.LENGTH_LONG).setAction("Nada", null).show();
                } else {
                    alarma = false;
                    myRef.setValue("false");
                    fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.Gris)));
                    Snackbar.make(view, "Alarma desactivada", Snackbar.LENGTH_LONG).setAction("Nada", null).show();
                }
                return false;
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Cuando inicia, se ven las camaras
        String sTitle = "Estado";
        Fragment miFragment = new StatusFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFragment).commit();
        ActionBar actionBar = getSupportActionBar();
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(sTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement4
//        switch (id) {
//            case R.id.simulacion_1:
//                if (simulacion == false) {
//                    Toast.makeText(this, "Simulación activada", Toast.LENGTH_LONG).show();
//                    simulacion = true;
//                    sensor = false;
//                } else if(simulacion == true)  {
//                    Toast.makeText(this, "Simulación desactivada", Toast.LENGTH_LONG).show();
//                    simulacion = false;
//                }
//                break;
//            case R.id.simulacion_2:
//                if (sensor == false) {
//                    Toast.makeText(this, "Sensor activado", Toast.LENGTH_LONG).show();
//                    sensor = true;
//                    simulacion = false;
//                } else if(sensor == true)  {
//                    Toast.makeText(this, "Sensor desactivado", Toast.LENGTH_LONG).show();
//                    sensor = false;
//                }
//                break;
//        }
//
//        if (simulacion == true){
//            DatabaseReference myRef = database.getReference(usuario).child("Simulacion");
//            myRef.setValue("1");
//        } else if (sensor == true){
//            DatabaseReference myRef = database.getReference(usuario).child("Simulacion");
//            myRef.setValue("2");
//        } else {
//            DatabaseReference myRef = database.getReference(usuario).child("Simulacion");
//            myRef.setValue("0");
//        }
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
                sTitle = "Configuración";
                miFragment = new ConfiguracionFragment();
                fragmentSeleccionado = true;
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
            case R.id.cerrar_sesion:
                try{
                    OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                    fout.write("");
                    fout.close();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                }
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
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
