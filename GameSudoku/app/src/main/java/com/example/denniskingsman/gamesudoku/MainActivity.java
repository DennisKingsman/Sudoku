package com.example.denniskingsman.gamesudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button rulesBtn;
    Button exitBtn;
    Button newGameBtn;
    Button continueBtn;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getUIItems();

        setFonts();
    }

    public void getUIItems(){
        rulesBtn = findViewById(R.id.buttonGameRules);
        exitBtn = findViewById(R.id.buttonExitGame);
        newGameBtn = findViewById(R.id.buttonNewGame);
        continueBtn = findViewById(R.id.buttonContinueGame);

        title = findViewById(R.id.title);
    }

    public void continueGame(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void newGame(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);
    }

    public void ruleGame(View view){
        Intent intent = new Intent(MainActivity.this, Rules.class);
        startActivity(intent);
    }

    public void exitGame(View view){
        finish();
    }

    private void setFonts(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "GoT.ttf");

        title.setTypeface(typeface);

        rulesBtn.setTypeface(typeface);
        exitBtn.setTypeface(typeface);
        newGameBtn.setTypeface(typeface);
        continueBtn.setTypeface(typeface);
    }
}
