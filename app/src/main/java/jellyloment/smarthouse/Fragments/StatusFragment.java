package jellyloment.smarthouse.Fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {

    private TextView txvPuerta, txvVentana, txvFocos, txvCamara, txvCasa;
    private ImageView imvCasa, imvPuertas, imvVentanas, imvFocos;
    private Switch swPuerta, swVentana;
    private String linea = "", usuario = "", puerta = "", ventana = "", io = "", foco1 = "", foco2 = "", foco3 ="", foco4 = "";
    private int foquitos = 0, sensores = 0, contSenPuer = 0, contSenVen = 0;
    private String[] config;

    private DatabaseReference mDatabaseP, mDatabaseV, mDatabaseF1, mDatabaseF2, mDatabaseF3, mDatabaseF4, mDatabaseIOP, mDatabaseIOV;
    FirebaseDatabase databaseE = FirebaseDatabase.getInstance();

    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, container, false);

        txvPuerta = v.findViewById(R.id.txvDoorE);
        txvVentana = v.findViewById(R.id.txvVentanaE);
        txvFocos = v.findViewById(R.id.txvFocosE);
        txvCamara = v.findViewById(R.id.txvCamaraE);
        txvCasa = v.findViewById(R.id.txvEstadoE);

        imvCasa = v.findViewById(R.id.imgvEstado);
        imvPuertas = v.findViewById(R.id.imgvDoorE);
        imvVentanas = v.findViewById(R.id.imgvVentanaE);
        imvFocos = v.findViewById(R.id.imgvFocosE);

        swPuerta = v.findViewById(R.id.swPuertas);
        swVentana = v.findViewById(R.id.swVentanas);

        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(getContext().openFileInput("usuario_iniciado.txt")));

            linea = fin.readLine();
            config = linea.split(",");
            usuario = config[0];
            fin.close();
        }catch (Exception ex){
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        mDatabaseP = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("Puerta");
        mDatabaseP.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                puerta = dataSnapshot.getValue().toString();
                if (puerta.equals("desactivado")){
                    txvPuerta.setText("El sensor está desactivado");
                    contSenPuer = 1;
                    if (sensores > 0)
                        sensores--;
                }
                if (puerta.equals("true")){
                    txvPuerta.setText("La puerta se encuentra sellada");
                    if (sensores < 2)
                        sensores++;
                    if (contSenPuer == 1)
                        contSenPuer = 0;
                    imvPuertas.setImageResource(R.drawable.puerta_cerrada);
                }else if (puerta.equals("false")){
                    txvPuerta.setText("La puerta esta abierta!");
                    if (sensores > 0)
                        sensores--;
                    if (contSenPuer == 1)
                        contSenPuer = 0;
                    imvPuertas.setImageResource(R.drawable.puerta_abierta);
                }
                mostrar_texto_sensores();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseV = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("Ventana");
        mDatabaseV.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ventana = dataSnapshot.getValue().toString();
                if (ventana.equals("desactivado")){
                    txvVentana.setText("El sensor está desactivado");
                    contSenVen = 1;
                    if (sensores > 0)
                        sensores--;
                }
                if (ventana.equals("true")){
                    txvVentana.setText("La ventana se encuentra sellada");
                    if (sensores < 2)
                        sensores++;
                    if (contSenVen == 1)
                        contSenVen = 0;
                    imvVentanas.setImageResource(R.drawable.ventana_cerrada);
                }else if (ventana.equals("false")){
                    txvVentana.setText("La ventana esta abierta!");
                    if (sensores > 0)
                        sensores--;
                    if (contSenVen == 1)
                        contSenVen = 0;
                    imvVentanas.setImageResource(R.drawable.ventana_abierta);
                }
                mostrar_texto_sensores();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseF1 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco1");
        mDatabaseF1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco1 = dataSnapshot.getValue().toString();
                if (foco1.equals("true"))
                    foquitos++;
                mostrar_texto_focos();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF2 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco2");
        mDatabaseF2.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco2 = dataSnapshot.getValue().toString();
                if (foco2.equals("true"))
                    foquitos++;
                mostrar_texto_focos();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF3 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco3");
        mDatabaseF3.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco3 = dataSnapshot.getValue().toString();
                if (foco3.equals("true"))
                    foquitos++;
                mostrar_texto_focos();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF4 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco4");
        mDatabaseF4.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco4 = dataSnapshot.getValue().toString();
                if (foco4.equals("true"))
                    foquitos++;
                mostrar_texto_focos();
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        swPuerta.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    imvPuertas.setImageResource(R.drawable.puerta_cerrada);
                else
                    imvPuertas.setImageResource(R.drawable.puerta_abierta);
                DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("PuertaIO");
                myRef.setValue("" + isChecked);
            }
        });
        swVentana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    imvVentanas.setImageResource(R.drawable.ventana_cerrada);
                else
                    imvVentanas.setImageResource(R.drawable.ventana_abierta);
                DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("VentanaIO");
                myRef.setValue("" + isChecked);
            }
        });

        mDatabaseIOP = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("PuertaIO");
        mDatabaseIOP.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                io = dataSnapshot.getValue().toString();
                if (io.equals("true")) {
                    imvPuertas.setImageResource(R.drawable.puerta_cerrada);
                    swPuerta.setChecked(true);
                    DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("Puerta");
                    myRef.setValue("true");
                }else{
                    imvPuertas.setImageResource(R.drawable.puerta_abierta);
                    swPuerta.setChecked(false);
                    DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("Puerta");
                    myRef.setValue("desactivado");
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseIOV = FirebaseDatabase.getInstance().getReference(usuario).child("Sensores").child("VentanaIO");
        mDatabaseIOV.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                io = dataSnapshot.getValue().toString();
                if (io.equals("true")) {
                    imvVentanas.setImageResource(R.drawable.ventana_cerrada);
                    swVentana.setChecked(true);
                    DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("Ventana");
                    myRef.setValue("true");
                }else{
                    imvVentanas.setImageResource(R.drawable.ventana_abierta);
                    swVentana.setChecked(false);
                    DatabaseReference myRef = databaseE.getReference(usuario).child("Sensores").child("Ventana");
                    myRef.setValue("desactivado");
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        return v;
    }

    public void mostrar_texto_focos() {
        if (foquitos > 0) {
            if (foquitos == 1)
                txvFocos.setText("Hay solo un foco prendido");
            else
                txvFocos.setText("Hay " + foquitos + " prendidos");
            imvFocos.setImageResource(R.drawable.foco_prendido);
        }else {
            txvFocos.setText("Estan todos los focos apagados");
            imvFocos.setImageResource(R.drawable.foco_apagado);
        }
    }

    public void mostrar_texto_sensores() {
        if (sensores == 2 && contSenPuer == 0 && contSenVen == 0) {
            txvCasa.setText("Está todo en orden");
            int verde = Color.parseColor("#FF1F8600");
            imvCasa.setColorFilter(verde);
        } else if (sensores == 1) {
            if ((contSenPuer == 0 && contSenVen == 1) || (contSenPuer == 1 && contSenVen == 0)){
                txvCasa.setText("Están los sensores parcialmente activados");
                int verde = Color.parseColor("#FF1F8600");
                imvCasa.setColorFilter(verde);
            } else {
                txvCasa.setText("No está totalmente sellada la casa");
                int naranja = Color.parseColor("#FFC45200");
                imvCasa.setColorFilter(naranja);
            }
        }else if (sensores == 0){
            if ((contSenPuer == 0 && contSenVen == 1) || (contSenPuer == 1 && contSenVen == 0)) {
                txvCasa.setText("Ni la puerta ni la ventana estan selladas");
                int rojo = Color.parseColor("#FFA10000");
                imvCasa.setColorFilter(rojo);
            }
        }
    }
}
