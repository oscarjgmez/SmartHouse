package jellyloment.smarthouse.Fragments;


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import jellyloment.smarthouse.Activities.MainActivity;
import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {

    private Switch swLED1, swLED2, swLED3, swLED4;
    private String linea = "", usuario = "", led1 = "", led2 = "", led3 = "", led4 = "";
    private String[] config;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public LightFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_light, container, false);
        swLED1 = v.findViewById(R.id.swFoco1);
        swLED2 = v.findViewById(R.id.swFoco2);
        swLED3 = v.findViewById(R.id.swFoco3);
        swLED4 = v.findViewById(R.id.swFoco4);

        try
        {
            BufferedReader fin = new BufferedReader(new InputStreamReader(getContext().openFileInput("usuario_iniciado.txt")));

            linea = fin.readLine();
            config = linea.split(",");
            usuario = config[0];
            fin.close();

            if (config[1].equals("True"))
                swLED1.setChecked(true);
            if (config[2].equals("True"))
                swLED2.setChecked(true);
            if (config[3].equals("True"))
                swLED3.setChecked(true);
            if (config[4].equals("True"))
                swLED4.setChecked(true);
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

        swLED1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DatabaseReference myRef = database.getReference(usuario).child("Foco1");
                    myRef.setValue("True");
                    led1 = "True";
                } else {
                    DatabaseReference myRef = database.getReference(usuario).child("Foco1");
                    myRef.setValue("False");
                    led2 = "False";
                }
                try
                {
                    OutputStreamWriter fout = new OutputStreamWriter(getContext().openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                    fout.write(usuario + "," + led1 + "," + led2 + "," + led3 + "," +led4);
                    fout.close();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getContext(), "Error al escribir fichero a memoria interna", Toast.LENGTH_LONG).show();
                }
            }
        });
        swLED2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DatabaseReference myRef = database.getReference(usuario).child("Foco2");
                    myRef.setValue("True");
                    led2 = "True";
                } else {
                    DatabaseReference myRef = database.getReference(usuario).child("Foco2");
                    myRef.setValue("False");
                    led2 = "False";
                }
                try
                {
                    OutputStreamWriter fout = new OutputStreamWriter(getContext().openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                    fout.write(usuario + "," + led1 + "," + led2 + "," + led3 + "," +led4);
                    fout.close();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getContext(), "Error al escribir fichero a memoria interna", Toast.LENGTH_LONG).show();
                }
            }
        });
        swLED3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DatabaseReference myRef = database.getReference(usuario).child("Foco3");
                    myRef.setValue("True");
                    led3 = "True";
                } else {
                    DatabaseReference myRef = database.getReference(usuario).child("Foco3");
                    myRef.setValue("False");
                    led3 = "False";
                }
                try
                {
                    OutputStreamWriter fout = new OutputStreamWriter(getContext().openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                    fout.write(usuario + "," + led1 + "," + led2 + "," + led3 + "," +led4);
                    fout.close();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getContext(), "Error al escribir fichero a memoria interna", Toast.LENGTH_LONG).show();
                }
            }
        });
        swLED4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    DatabaseReference myRef = database.getReference(usuario).child("Foco4");
                    myRef.setValue("True");
                    led4 = "True";
                } else {
                    DatabaseReference myRef = database.getReference(usuario).child("Foco4");
                    myRef.setValue("False");
                    led4 = "False";
                }
                try
                {
                    OutputStreamWriter fout = new OutputStreamWriter(getContext().openFileOutput("usuario_iniciado.txt", Context.MODE_PRIVATE));

                    fout.write(usuario + "," + led1 + "," + led2 + "," + led3 + "," +led4);
                    fout.close();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getContext(), "Error al escribir fichero a memoria interna", Toast.LENGTH_LONG).show();
                }
            }
        });
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
