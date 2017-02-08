package com.example.android.serialquizz;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static com.example.android.serialquizz.R.string.answer;
import static com.example.android.serialquizz.R.string.play;

public class QuizzActivity extends AppCompatActivity {
    private MediaPlayer player;
    String answer1, answer4;
    int answer2 = 0;
    double result = 0;
    boolean answer31, answer32, answer33;


    /**
     * Fragment's state saved.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getAnswers();
        outState.putString("answer1", answer1);
        outState.putInt("answer2", answer2);
        outState.putBoolean("answer31", answer31);
        outState.putBoolean("answer32", answer32);
        outState.putBoolean("answer33", answer33);
        outState.putString("answer4", answer4);

    }

    /**
     * Fragment's state restore.
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        answer1 = savedInstanceState.getString("answer1");
        answer2 = savedInstanceState.getInt("answer2");
        answer31= savedInstanceState.getBoolean("answer31");
        answer32 = savedInstanceState.getBoolean("answer32");
        answer33 = savedInstanceState.getBoolean("answer33");
        answer4 = savedInstanceState.getString("answer4");
        setAnswers();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
    }


    public void play(View view) {
        player = MediaPlayer.create(this, R.raw.testsound);
        player.setLooping(false);
        player.start();
    }


    public void getAnswers() {

        EditText eT1 = (EditText) findViewById(R.id.eTAnswer1);
        answer1 = eT1.getText().toString();

        RadioGroup rg = (RadioGroup) findViewById(R.id.rGquestion2);
        answer2 = rg.getCheckedRadioButtonId();

        CheckBox cB1 = (CheckBox) findViewById(R.id.cBShredder);
        answer31 = cB1.isChecked();

        CheckBox cB2 = (CheckBox) findViewById(R.id.cBHighway);
        answer32 = cB2.isChecked();

        CheckBox cB3 = (CheckBox) findViewById(R.id.cBGargamel);
        answer33 = cB3.isChecked();

        EditText eT4 = (EditText) findViewById(R.id.eTAnswer4);
        answer4 = eT4.getText().toString();
    }

    public void setAnswers() {

        EditText eT1 = (EditText) findViewById(R.id.eTAnswer1);
        eT1.setText(answer1);

        RadioButton rb = (RadioButton) findViewById(answer2);
        rb.setChecked(true);

        CheckBox cB1 = (CheckBox) findViewById(R.id.cBShredder);
        cB1.setChecked(answer31);

        CheckBox cB2 = (CheckBox) findViewById(R.id.cBHighway);
        cB2.setChecked(answer32);

        CheckBox cB3 = (CheckBox) findViewById(R.id.cBGargamel);
        cB3.setChecked(answer33);

        EditText eT4 = (EditText) findViewById(R.id.eTAnswer4);
        eT4.setText(answer4);
    }

    private void calculateResult() {
        //every time you claculate result get reset
        result = 0;

        //we get the answers from interface
        getAnswers();

        //if correct answer you recive your puntuation aded to result
        if (answer1.equalsIgnoreCase(getResources().getString(R.string.answer_1))) {
            result++;
        }
        if (answer4.equalsIgnoreCase(getResources().getString(R.string.answer_4))) {
            result++;
        }
        if (answer2 == R.id.Master4) {
            result++;
        }
        if (answer31) {
            result += 0.5;
        }
        if (answer32) {
            result -= 0.5;
        }
        if (answer33) {
            result += 0.5;
        }

    }

    public void getResult(View view) {
        String mensage;

        calculateResult();

        //For each result interval a message is generated
        if (result == 4) {
            mensage = getResources().getString(R.string.mensage_total) + "\n" + getResources().getString(R.string.score) + result;
        } else if (2 <= result && result < 4) {
            mensage = getResources().getString(R.string.mensage_high) + "\n" + getResources().getString(R.string.score) + result;
        } else if (0 < result && result < 2) {
            mensage = getResources().getString(R.string.mensage_low) + "\n" + getResources().getString(R.string.score) + result;
        } else if (result == 0) {
            mensage = getResources().getString(R.string.mensage_0) + "\n" + getResources().getString(R.string.score) + result;
        } else {
            mensage = getResources().getString(R.string.mensage_negative) + "\n" + getResources().getString(R.string.score) + result;
        }

        //shows the result
        Toast.makeText(this, mensage, Toast.LENGTH_LONG).show();


    }

}
