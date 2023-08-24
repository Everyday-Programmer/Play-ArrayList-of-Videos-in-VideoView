package com.example.arraylistvideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
        if (result) {
            getVideos();
        }
    });
    ArrayList<Video> arrayList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_MEDIA_VIDEO) != PackageManager.PERMISSION_GRANTED) {
                activityResultLauncher.launch(Manifest.permission.READ_MEDIA_VIDEO);
            } else {
                getVideos();
            }
        } else {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                activityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            } else {
                getVideos();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getVideos();
    }

    private void getVideos() {
        arrayList.clear();
        String path = "/storage/emulated/0/Movies";
        File file = new File(path);
        File[] files = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (file1.getPath().endsWith(".mp4")) {
                    arrayList.add(new Video(file1.getPath()));
                }
            }
        }
        App.arrayList = arrayList;
        VideoAdapter adapter = new VideoAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener((view, index) -> startActivity(new Intent(MainActivity.this, VideoPlayActivity.class).putExtra("index", index)));
    }
}