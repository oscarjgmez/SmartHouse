package jellyloment.smarthouse.Fragments;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
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

import jellyloment.smarthouse.Activities.MainActivity;
import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {

    private Switch swLED1, swLED2, swLED3, swLED4;
    Socket myAppSocket = null;
    public static String wifiModuleIp = "";
    public static int wifiModulePort = 0;
    public static String CMD = "0";

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

        swLED1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        getIPandPort();
                        CMD = "01";
                        Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                        cmd_increase_servo.execute();
                    } else {
                        getIPandPort();
                        CMD = "0apagauno";
                        Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                        cmd_increase_servo.execute();
                    }
            }
        });
        swLED2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getIPandPort();
                    CMD = "02";
                    Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                    cmd_increase_servo.execute();
                } else {
                    getIPandPort();
                    CMD = "0apagados";
                    Socket_AsyncTask cmd_increase_servo = new Socket_AsyncTask();
                    cmd_increase_servo.execute();
                }
            }
        });
        return v;
    }

    public void getIPandPort()
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
    }
}
