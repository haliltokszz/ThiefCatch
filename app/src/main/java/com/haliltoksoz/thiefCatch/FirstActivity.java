package com.haliltoksoz.thiefCatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView txtTopScore;
    int topScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        TopScore();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        TopScore();
    }

    public void StartGame(View view){
        Intent intent = new Intent(FirstActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void TopScore(){
        txtTopScore = findViewById(R.id.txtTopScore);
        sharedPreferences = this.getSharedPreferences("com.haliltoksoz.thiefCatch", Context.MODE_PRIVATE);
        topScore = sharedPreferences.getInt("TopScore", 0);
        txtTopScore.setText("Top Score: "+ topScore);
    }
}
