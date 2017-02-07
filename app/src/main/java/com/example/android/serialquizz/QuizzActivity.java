package com.example.android.serialquizz;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class QuizzActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }

    public void play (View view){
            MediaPlayer player= MediaPlayer.create(this, R.raw.testsound);
            player.start();

    }

}
