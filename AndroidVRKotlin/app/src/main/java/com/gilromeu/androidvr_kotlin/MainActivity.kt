package com.gilromeu.androidvr_kotlin

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPhoto360()
    }

    private fun loadPhoto360() {
        val options = VrPanoramaView.Options()
        var inputStream: InputStream? = null

        val assetManager = assets
        try {
            inputStream = assetManager.open("img1.jpg")
            options.inputType = VrPanoramaView.Options.TYPE_MONO
            vrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options)
            inputStream!!.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        super.onPause()
        vrPanoramaView.pauseRendering()
    }

    override fun onResume() {
        super.onResume()
        vrPanoramaView.resumeRendering()
    }

    override fun onDestroy() {
        vrPanoramaView.shutdown()
        super.onDestroy()
    }
}
