package com.ex1.webinterfaces.misea.uc3m.ex1pedrlarg;


import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final int CAMERA_REQUEST = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonStart = (Button) findViewById(R.id.startquest);

        //Check if the user has a functional camera or an app
        if(!hasCamera())
            buttonStart.setEnabled(false);
    }

    //Check if the user has a camera
    private boolean hasCamera(){
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    //Whenever the start button is pressed then launch the camera
    public void launchCamera(View view){
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraintent, CAMERA_REQUEST);
    }

    String mCurrentPhotoPath;

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        //We check if the user has accepted the image captured
        if(resultCode == RESULT_OK && requestCode == CAMERA_REQUEST){
            Bundle extras = data.getExtras();

        }
    }
}

