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
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;

import jellyloment.smarthouse.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    final static String URL = "http://192.168.1.80:8081/";

    private WebView mWebView;
    private WebSettings webSettings;


    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_camera, container, false);

        mWebView = v.findViewById(R.id.vidVCamera);
        mWebView.setWebChromeClient(new WebChromeClient());
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        webSettings.setLoadWithOverviewMode(true);
        mWebView.loadUrl(URL);

        return v;
    }
}
