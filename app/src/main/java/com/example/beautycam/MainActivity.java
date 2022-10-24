package com.example.beautycam;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int Photo_Request_Code = 100;
    private final int Permission_Request_Code = 101;
    ImageView photoGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photoGallery = findViewById(R.id.photoGallery);
        Button galleryBtn = findViewById(R.id.galleryBtn);
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getPermissionBox(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Permission_Request_Code);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == Photo_Request_Code) {
                photoGallery.setImageURI(data.getData());
            }
        }
    }


    private void openGallery() {
        Intent pGallery = new Intent(Intent.ACTION_PICK);
        pGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pGallery, Photo_Request_Code);
    }

    private void getPermissionBox(String[] permissionArr, int requestCode) {
        int permissionChecker = PackageManager.PERMISSION_GRANTED;
        for (String permissionStr : permissionArr) {
            permissionChecker = permissionChecker + ContextCompat.checkSelfPermission(getApplicationContext(), permissionStr);
        }
        if (permissionChecker != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, permissionArr, requestCode);
        } else {
            onPermissionGranted(requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionChecker = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionChecker = permissionChecker + permission;
        }
        if (permissionChecker == PackageManager.PERMISSION_GRANTED && grantResults.length > 0) {
            onPermissionGranted(requestCode);
        } else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
        }
    }

    private void onPermissionGranted(int requestCode) {
        switch (requestCode) {
            case Permission_Request_Code:
                openGallery();
                break;
        }
    }
}