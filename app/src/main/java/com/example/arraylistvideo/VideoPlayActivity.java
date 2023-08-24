package com.example.arraylistvideo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class VideoPlayActivity extends AppCompatActivity {
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        VideoView videoView = findViewById(R.id.videoView);
        ImageButton pausePlay = findViewById(R.id.pausePlay);
        ImageButton next = findViewById(R.id.next);
        ImageButton previous = findViewById(R.id.previous);

        Intent intent = getIntent();
        if (intent != null) {
            index = intent.getIntExtra("index", 0);
            previous.setEnabled(index > 0);
            videoView.setVideoPath(App.arrayList.get(index).getPath());
            videoView.setOnPreparedListener(mp -> {
                mp.start();

                pausePlay.setOnClickListener(v -> {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_play_circle_24).into(pausePlay);
                    } else {
                        videoView.resume();
                        Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_pause_circle_24).into(pausePlay);
                    }
                });
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.stopPlayback();
                    if (App.arrayList.size() >= index + 1) {
                        index++;
                    } else {
                        finish();
                    }
                    videoView.setVideoPath(App.arrayList.get(index).getPath());
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();

                            pausePlay.setOnClickListener(v -> {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.pause();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_play_circle_24).into(pausePlay);
                                } else {
                                    mediaPlayer.start();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_pause_circle_24).into(pausePlay);
                                }
                            });
                        }
                    });
                    previous.setEnabled(index - 1 > 0);
                    next.setEnabled(App.arrayList.size() > index + 1);
                }
            });

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    videoView.stopPlayback();
                    if (index - 1 >= 0) {
                        index--;
                    } else {
                        finish();
                    }
                    videoView.setVideoPath(App.arrayList.get(index).getPath());
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();

                            pausePlay.setOnClickListener(v -> {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.pause();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_play_circle_24).into(pausePlay);
                                } else {
                                    mediaPlayer.start();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_pause_circle_24).into(pausePlay);
                                }
                            });
                        }
                    });
                    next.setEnabled(App.arrayList.size() > index + 1);
                    previous.setEnabled(index - 1 > 0);
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    videoView.stopPlayback();
                    if (App.arrayList.size() > index + 1) {
                        index++;
                    } else {
                        finish();
                    }
                    videoView.setVideoPath(App.arrayList.get(index).getPath());
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mediaPlayer.start();

                            pausePlay.setOnClickListener(v -> {
                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.pause();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_play_circle_24).into(pausePlay);
                                } else {
                                    mediaPlayer.start();
                                    Glide.with(VideoPlayActivity.this).load(R.drawable.ic_baseline_pause_circle_24).into(pausePlay);
                                }
                            });
                        }
                    });
                    next.setEnabled(App.arrayList.size() > index + 1);
                }
            });
        }
    }
}