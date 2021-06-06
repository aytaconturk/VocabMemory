package com.example.vocabmemory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static FragmentManager fragmentManager;

    public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Database.getInstance(getApplicationContext());

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if (savedInstanceState!=null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();

        }

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    Fragment fragment = null;

                    switch (item.getItemId()){
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.user:
                            fragment = new UserFragment();
                            break;

                    }

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();

                    return true;
                }
            };


}