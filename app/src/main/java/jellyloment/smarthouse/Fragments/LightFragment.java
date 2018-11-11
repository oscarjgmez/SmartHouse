package jellyloment.smarthouse.Fragments;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import jellyloment.smarthouse.Activities.LoginActivity;
import jellyloment.smarthouse.Activities.MainActivity;
import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {

    private Switch swLED1, swLED2, swLED3, swLED4;
    private String usuario = "";
    private DatabaseReference mDatabase;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public LightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_light, container, false);

        try{

            swLED1 = v.findViewById(R.id.swFoco1);
            swLED2 = v.findViewById(R.id.swFoco2);
            swLED3 = v.findViewById(R.id.swFoco3);
            swLED4 = v.findViewById(R.id.swFoco4);

            BufferedReader fin = new BufferedReader(new InputStreamReader(getContext().openFileInput("usuario_iniciado.txt")));

            usuario = fin.readLine();
            fin.close();

            mDatabase = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco1");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String valor = dataSnapshot.getValue().toString();
                    if (valor.equals("true"))
                        swLED1.setChecked(true);
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });

            mDatabase = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco2");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String valor = dataSnapshot.getValue().toString();
                    if (valor.equals("true"))
                        swLED2.setChecked(true);
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
            mDatabase = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco3");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String valor = dataSnapshot.getValue().toString();
                    if (valor.equals("true"))
                        swLED3.setChecked(true);
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });

            mDatabase = FirebaseDatabase.getInstance().getReference(usuario).child("Focos").child("Foco4");
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String valor = dataSnapshot.getValue().toString();
                    if (valor.equals("true"))
                        swLED4.setChecked(true);
                }
                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });

            swLED1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DatabaseReference myRef = database.getReference(usuario).child("Focos").child("Foco1");
                    myRef.setValue("" + isChecked);
                }
            });
            swLED2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DatabaseReference myRef = database.getReference(usuario).child("Focos").child("Foco2");
                    myRef.setValue("" + isChecked);
                }
            });
            swLED3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DatabaseReference myRef = database.getReference(usuario).child("Focos").child("Foco3");
                    myRef.setValue("" + isChecked);
                }
            });
            swLED4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    DatabaseReference myRef = database.getReference(usuario).child("Focos").child("Foco4");
                    myRef.setValue("" + isChecked);
                }
            });
        }catch (Exception ex){
            Toast.makeText(getContext(), "Error en: " + ex, Toast.LENGTH_LONG).show();
        }
        return v;
    }

    /*public void getIPandPort()
    {
        String iPandPort = "192.168.1.77:21567";
        Log.d("MYTEST","IP String: "+ iPandPort);
        String temp[]= iPandPort.split(":");
        wifiModuleIp = temp[0];
        wifiModulePort = Integer.valueOf(temp[1]);
        Log.d("MY TEST","IP:" +wifiModuleIp);
        Log.d("MY TEST","PORT:"+wifiModulePort);
    }
    public class Socket_AsyncTask extends AsyncTask<Void,Void,Void>
    {
        Socket socket;

        @Override
        protected Void doInBackground(Void... params){
            try{
                InetAddress inetAddress = InetAddress.getByName(wifiModuleIp);
                socket = new java.net.Socket(inetAddress,wifiModulePort);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeBytes(CMD);
                dataOutputStream.close();
                socket.close();
            }catch (UnknownHostException e){e.printStackTrace();}catch (IOException e){e.printStackTrace();}
            return null;
        }
    }*/
}
