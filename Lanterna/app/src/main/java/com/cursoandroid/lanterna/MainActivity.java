package com.cursoandroid.lanterna;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import android.Manifest;
import android.widget.ImageButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    boolean status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton = findViewById(R.id.imageButton);

        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                runFlash();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(MainActivity.this, "permissao da camera necessaria", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

            }
        }).check();

    }

    private void runFlash() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!status){
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    try{
                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,true);
                        status = true;
                        imageButton.setImageResource(R.drawable.sun);

                    }
                    catch (CameraAccessException e){

                    }
                }
                else{
                    CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                    try{
                        String cameraId = cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(cameraId,false);
                        status = false;
                        imageButton.setImageResource(R.drawable.moon);

                    }
                    catch (CameraAccessException e){

                    }
                }
            }
        });
    }
}