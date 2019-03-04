package com.example.moodshift;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    SeekBar volumeBar;
    AudioManager audioManager;
    int maxVol;
    int currVol;
    int minVol;
    ImageButton buttonHappy;
    ImageButton buttonSad;
    ImageButton buttonBored;
    ImageButton buttonAngry;
    Button buttonReturn;
    VideoView videoView;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView)findViewById(R.id.title);

        buttonHappy = (ImageButton) findViewById(R.id.buttonHappy);
        buttonSad = (ImageButton) findViewById(R.id.buttonSad);
        buttonBored = (ImageButton) findViewById(R.id.buttonBored);
        buttonAngry = (ImageButton) findViewById(R.id.buttonAngry);
        videoView = (VideoView) findViewById(R.id.videoView);
        buttonReturn = (Button) findViewById(R.id.buttonReturn);

        // volumeBar
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        volumeBar.setOnSeekBarChangeListener(seekVolume);
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        currVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        minVol = audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC);
        volumeBar.setMax(maxVol);
        volumeBar.setProgress(currVol);

        // angry
        player = MediaPlayer.create(this, R.raw.angrysound);

        // buttons
        buttonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButtons();
                View root = v.getRootView();
                root.setBackgroundColor(Color.GREEN);

                videoView.setVideoPath("android.resource://"+getPackageName()
                        + "/"+ R.raw.happyvideo);
                videoView.start();
//                happy(v);
            }
        });

        buttonSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButtons();
                View root = v.getRootView();
                root.setBackgroundColor(Color.BLACK);

                videoView.setVideoPath("android.resource://"+getPackageName()
                        + "/"+ R.raw.sadvideo);
                videoView.start();
            }
        });

        buttonBored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButtons();
                View root = v.getRootView();
                root.setBackgroundColor(Color.GRAY);

                videoView.setVideoPath("android.resource://"+getPackageName()
                        + "/"+ R.raw.boringvideo);
                videoView.start();
            }
        });

        buttonAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideButtons();
                videoView.setVisibility(View.INVISIBLE);
                View root = v.getRootView();
                root.setBackgroundColor(Color.RED);

                player.seekTo(0);
                player.start();
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMain(v);
                player.pause();
                videoView.stopPlayback();
                View root = v.getRootView();
                root.setBackgroundColor(Color.WHITE);
            }
        });
        //
    }

    public void returnMain(View v) {
        buttonHappy.setVisibility(View.VISIBLE);
        buttonSad.setVisibility(View.VISIBLE);
        buttonBored.setVisibility(View.VISIBLE);
        buttonAngry.setVisibility(View.VISIBLE);

        buttonHappy.animate().alpha(1f).setDuration(500);
        buttonSad.animate().alpha(1f).setDuration(500);
        buttonBored.animate().alpha(1f).setDuration(500);
        buttonAngry.animate().alpha(1f).setDuration(500);

        videoView.setVisibility(View.INVISIBLE);
        volumeBar.setVisibility(View.INVISIBLE);
        buttonReturn.setVisibility(View.INVISIBLE);
    }

    private void hideButtons() {
        buttonHappy.animate().alpha(0f).setDuration(500);
        buttonSad.animate().alpha(0f).setDuration(500);
        buttonBored.animate().alpha(0f).setDuration(500);
        buttonAngry.animate().alpha(0f).setDuration(500);

        buttonHappy.setVisibility(View.INVISIBLE);
        buttonSad.setVisibility(View.INVISIBLE);
        buttonBored.setVisibility(View.INVISIBLE);
        buttonAngry.setVisibility(View.INVISIBLE);

        videoView.setVisibility(View.VISIBLE);
        volumeBar.setVisibility(View.VISIBLE);
        buttonReturn.setVisibility(View.VISIBLE);
    }

    public SeekBar.OnSeekBarChangeListener seekVolume =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };
}