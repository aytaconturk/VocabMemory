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

//        database = Room.databaseBuilder(getApplicationContext(), Database.class, "vocabMemory").
//                allowMainThreadQueries().build();


        Database.getInstance(getApplicationContext());

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if (savedInstanceState!=null){
                return;
            }

            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();

        }
        /*

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    verifyStoragePermissions(this);
                }
        */

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

    public void nextWordCard(View view) {
        //
    }

   /* @Override
    public void onStart() {
        super.onStart();
//        MainActivity.database.close();

//        File dbfile = new File(MainActivity.database.getOpenHelper().getWritableDatabase().getPath());
        File dbfile = this.getDatabasePath(MainActivity.database.toString());
        File sdir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"DBsaves");
        String sfpath = sdir.getPath() + File.separator + "DBsave" + String.valueOf(System.currentTimeMillis());
        if (!sdir.exists()) {
            sdir.mkdirs();
        }
        File savefile = new File(sfpath);
        try {
            savefile.createNewFile();
            int buffersize = 8 * 1024;
            byte[] buffer = new byte[buffersize];
            int bytes_read = buffersize;
            OutputStream savedb = new FileOutputStream(sfpath);
            InputStream indb = new FileInputStream(dbfile);
            while ((bytes_read = indb.read(buffer,0,buffersize)) > 0) {
                savedb.write(buffer,0,bytes_read);
            }
            savedb.flush();
            indb.close();
            savedb.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void verifyStoragePermissions(MainActivity activity) {

        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {

                //Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permission = ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }*/
}