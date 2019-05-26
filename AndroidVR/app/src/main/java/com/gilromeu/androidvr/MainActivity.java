package com.gilromeu.androidvr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private VrPanoramaView mVRPanoramaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVRPanoramaView = findViewById(R.id.vrPanoramaView);
        loadPhoto360();
    }

    private void loadPhoto360() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
            inputStream = assetManager.open("img1.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();
    }
}
