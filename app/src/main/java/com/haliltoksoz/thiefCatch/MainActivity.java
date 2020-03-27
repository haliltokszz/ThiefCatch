package com.haliltoksoz.thiefCatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txtTime;
    TextView txtScore;
    int score=0, topScore=0, pastRand=0;
    ImageView imgThief1;
    ImageView imgThief2;
    ImageView imgThief3;
    ImageView imgThief4;
    ImageView imgThief5;
    ImageView imgThief6;
    ImageView imgThief7;
    ImageView imgThief8;
    ImageView imgThief9;
    ImageView[] imgArray;
    SharedPreferences sharedPreferences;
    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTime = findViewById(R.id.txtTimer);
        txtScore = findViewById(R.id.txtScore);
        imgThief1 = findViewById(R.id.imgThief);
        imgThief2 = findViewById(R.id.imgThief2);
        imgThief3 = findViewById(R.id.imgThief3);
        imgThief4 = findViewById(R.id.imgThief4);
        imgThief5 = findViewById(R.id.imgThief5);
        imgThief6 = findViewById(R.id.imgThief6);
        imgThief7 = findViewById(R.id.imgThief7);
        imgThief8 = findViewById(R.id.imgThief8);
        imgThief9 = findViewById(R.id.imgThief9);

        imgArray = new ImageView[] {imgThief1, imgThief2, imgThief3, imgThief4, imgThief5, imgThief6, imgThief7, imgThief8, imgThief9};

        sharedPreferences = this.getSharedPreferences("com.haliltoksoz.thiefCatch", Context.MODE_PRIVATE);
        TopScore();

        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText("Time: " + millisUntilFinished/1000+"sn");
            }

            @Override
            public void onFinish() {
                txtTime.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                AlertDialog.Builder finisAlert = new AlertDialog.Builder(MainActivity.this);
                finisAlert.setTitle("Game Over");
                finisAlert.setMessage("Restart the game?");
                finisAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                finisAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                finisAlert.show();
            }
        }.start();

        ImgHide();

    }

    public void increaseScore (View view){
        score++;
        txtScore.setText("Score: "+ score);
        if (score>topScore){
            topScore=score;
            sharedPreferences.edit().putInt("TopScore", topScore).apply();
        }
    }

    public void ImgHide(){
        handler = new Handler();
        runnable= new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imgArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random= new Random();
                int i = random.nextInt(9);
                if(pastRand!=i){
                    imgArray[i].setVisibility(View.VISIBLE);
                    pastRand = i;
                }else{
                    i = random.nextInt(9);
                    imgArray[i].setVisibility(View.VISIBLE);
                }
                handler.postDelayed(this, 350);
            }
        };
        handler.post(runnable);

    }

    public void TopScore(){
        sharedPreferences = this.getSharedPreferences("com.haliltoksoz.thiefCatch", Context.MODE_PRIVATE);
        topScore = sharedPreferences.getInt("TopScore", 0);
    }


}
