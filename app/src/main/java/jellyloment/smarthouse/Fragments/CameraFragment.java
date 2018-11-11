package jellyloment.smarthouse.Fragments;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    final static String URL = "http://192.168.1.73:8090/";

    private VideoView piVideo;


    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);

        piVideo = v.findViewById(R.id.vidVCamera);

        try{
            piVideo.setVideoURI(Uri.parse(URL));
        } catch (Exception e){
            Log.e("Error found here->", e.getMessage());
            e.printStackTrace();
        }
        piVideo.requestFocus();
        piVideo.start();

        piVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override

            public void onPrepared(MediaPlayer mp) {
                piVideo.start();
            }
        });

        return v;
    }
}
