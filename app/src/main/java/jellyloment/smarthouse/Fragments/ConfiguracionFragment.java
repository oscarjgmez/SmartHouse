package jellyloment.smarthouse.Fragments;


import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Calendar;

import jellyloment.smarthouse.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfiguracionFragment extends Fragment {

    private ImageButton btnHora1, btnHora2, btnHora3, btnHora4;
    private TextView txvProg1, txvProg2, txvProg3, txvProg4;
    private Button btnSim1, btnSim2, btnSim3;
    private EditText edtFoco1, edtFoco2, edtFoco3, edtFoco4;
    private int simulacion = 0, hora = 0, minutos = 0;
    private String foco1 = "", foco2 = "", foco3 = "", foco4 = "", usuario = "";
    private DatabaseReference mDatabaseB, mDatabaseF1, mDatabaseF2, mDatabaseF3, mDatabaseF4, mDatabaseHF1, mDatabaseHF2, mDatabaseHF3, mDatabaseHF4;


    FirebaseDatabase databaseE = FirebaseDatabase.getInstance();


    public ConfiguracionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_configuracion, container, false);

        btnSim1 = v.findViewById(R.id.btnSimulacion1Conf);
        btnSim2 = v.findViewById(R.id.btnSimulacion2Conf);
        btnSim3 = v.findViewById(R.id.btnSimulacion3Conf);
        edtFoco1 = v.findViewById(R.id.edtFoco1conf);
        edtFoco2 = v.findViewById(R.id.edtFoco2conf);
        edtFoco3 = v.findViewById(R.id.edtFoco3conf);
        edtFoco4 = v.findViewById(R.id.edtFoco4conf);
        btnHora1 = v.findViewById(R.id.btnProgramar1Conf);
        btnHora2 = v.findViewById(R.id.btnProgramar2Conf);
        btnHora3 = v.findViewById(R.id.btnProgramar3Conf);
        btnHora4 = v.findViewById(R.id.btnProgramar4Conf);
        txvProg1 = v.findViewById(R.id.txvProgF1Conf);
        txvProg2 = v.findViewById(R.id.txvProgF2Conf);
        txvProg3 = v.findViewById(R.id.txvProgF3Conf);
        txvProg4 = v.findViewById(R.id.txvProgF4Conf);

        try{
            BufferedReader fin = new BufferedReader(new InputStreamReader(getContext().openFileInput("usuario_iniciado.txt")));

            usuario = fin.readLine();
            fin.close();
        }catch (Exception ex){
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        mDatabaseB = FirebaseDatabase.getInstance().getReference(usuario).child("Simulacion");
        mDatabaseB.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                simulacion = Integer.parseInt(dataSnapshot.getValue().toString());
                cambiar_colores_simulacion(simulacion);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseF1 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Nombre1");
        mDatabaseF1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco1 = dataSnapshot.getValue().toString();
                edtFoco1.setText(foco1);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF1 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Conf1");
        mDatabaseF1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco1 = dataSnapshot.getValue().toString();
                txvProg1.setText(foco1);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseF2 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Nombre2");
        mDatabaseF2.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco2 = dataSnapshot.getValue().toString();
                edtFoco2.setText(foco2);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF2 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Conf2");
        mDatabaseF2.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco2 = dataSnapshot.getValue().toString();
                txvProg2.setText(foco2);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseF3 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Nombre3");
        mDatabaseF3.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco3 = dataSnapshot.getValue().toString();
                edtFoco3.setText(foco3);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF3 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Conf3");
        mDatabaseF3.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco3 = dataSnapshot.getValue().toString();
                txvProg3.setText(foco3);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        mDatabaseF4 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Nombre4");
        mDatabaseF4.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco4 = dataSnapshot.getValue().toString();
                edtFoco4.setText(foco4);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });
        mDatabaseF4 = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Conf4");
        mDatabaseF4.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                foco4 = dataSnapshot.getValue().toString();
                txvProg4.setText(foco4);
            }
            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){

            }
        });

        btnSim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Simulacion");
                myRef.setValue("1");
            }
        });

        btnSim2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Simulacion");
                myRef.setValue("2");
                DatabaseReference myRefC1 = databaseE.getReference(usuario).child("Focos").child("Conf1");
                myRefC1.setValue(txvProg1.getText().toString());
                DatabaseReference myRefC2 = databaseE.getReference(usuario).child("Focos").child("Conf2");
                myRefC2.setValue(txvProg2.getText().toString());
                DatabaseReference myRefC3 = databaseE.getReference(usuario).child("Focos").child("Conf3");
                myRefC3.setValue(txvProg3.getText().toString());
                DatabaseReference myRefC4 = databaseE.getReference(usuario).child("Focos").child("Conf4");
                myRefC4.setValue(txvProg4.getText().toString());
            }
        });

        btnSim3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Simulacion");
                myRef.setValue("0");
            }
        });


        edtFoco1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Focos").child("Nombre1");
                myRef.setValue(edtFoco1.getText().toString());
            }
        });

        edtFoco2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Focos").child("Nombre2");
                myRef.setValue(edtFoco2.getText().toString());
            }
        });

        edtFoco3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Focos").child("Nombre3");
                myRef.setValue(edtFoco3.getText().toString());
            }
        });

        edtFoco4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                DatabaseReference myRef = databaseE.getReference(usuario).child("Focos").child("Nombre4");
                myRef.setValue(edtFoco4.getText().toString());
            }
        });

        btnHora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10)
                            txvProg1.setText(hourOfDay + ":0" + minute);
                        else
                            txvProg1.setText(hourOfDay + ":" + minute);
                    }
                },hora,minutos,false);
                timePickerDialog.setTitle("Programe la hora");
                timePickerDialog.show();
            }
        });

        btnHora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10)
                            txvProg2.setText(hourOfDay + ":0" + minute);
                        else
                            txvProg2.setText(hourOfDay + ":" + minute);
                    }
                },hora,minutos,false);
                timePickerDialog.setTitle("Programe la hora");
                timePickerDialog.show();
            }
        });

        btnHora3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10)
                            txvProg3.setText(hourOfDay + ":0" + minute);
                        else
                            txvProg3.setText(hourOfDay + ":" + minute);
                    }
                },hora,minutos,false);
                timePickerDialog.setTitle("Programe la hora");
                timePickerDialog.show();
            }
        });

        btnHora4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                hora = c.get(Calendar.HOUR_OF_DAY);
                minutos = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity() , new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (minute < 10)
                            txvProg4.setText(hourOfDay + ":0" + minute);
                        else
                            txvProg4.setText(hourOfDay + ":" + minute);
                    }
                },hora,minutos,false);
                timePickerDialog.setTitle("Programe la hora");
                timePickerDialog.show();
            }
        });

        return v;
    }

    private void cambiar_colores_simulacion(int simulacion){
        switch (simulacion){
            case 1:
                btnSim1.setEnabled(false);
                btnSim1.setTextColor(Color.parseColor("#FF1F8600"));


                btnSim2.setTextColor(Color.parseColor("#FFA10000"));
                btnSim3.setTextColor(Color.parseColor("#FFA10000"));
                btnSim2.setEnabled(true);
                btnSim3.setEnabled(true);
                break;
            case 2:
                btnSim2.setEnabled(false);
                btnSim2.setTextColor(Color.parseColor("#FF1F8600"));


                btnSim3.setTextColor(Color.parseColor("#FFA10000"));
                btnSim1.setTextColor(Color.parseColor("#FFA10000"));
                btnSim3.setEnabled(true);
                btnSim1.setEnabled(true);
                break;
            case 0:
                btnSim3.setEnabled(false);
                btnSim3.setTextColor(Color.parseColor("#FF1F8600"));


                btnSim2.setTextColor(Color.parseColor("#FFA10000"));
                btnSim1.setTextColor(Color.parseColor("#FFA10000"));
                btnSim2.setEnabled(true);
                btnSim1.setEnabled(true);
                break;
        }
    }
}
