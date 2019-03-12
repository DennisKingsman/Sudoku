package com.example.denniskingsman.gamesudoku;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GameActivity extends Activity implements View.OnClickListener {

    private GridView gridView;
    private Button buttonOne, buttonTwo, buttonThree, buttonFour, buttonFive, buttonSix, buttonSeven, buttonEight, buttonNine;
    private String selectedBtn = "n1";
    private Game game;

    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        game = new Game(this);

        getUIItems();

        setFont();

        gridView.setNumColumns(9);
        gridView.setEnabled(true);
        gridView.setAdapter(game);

    }

    private void getUIItems(){
        gridView = findViewById(R.id.field);

        buttonOne = findViewById(R.id.btn1);
        buttonTwo = findViewById(R.id.btn2);
        buttonThree = findViewById(R.id.btn3);
        buttonFour = findViewById(R.id.btn4);
        buttonFive = findViewById(R.id.btn5);
        buttonSix = findViewById(R.id.btn6);
        buttonSeven = findViewById(R.id.btn7);
        buttonEight = findViewById(R.id.btn8);
        buttonNine = findViewById(R.id.btn9);

        title = findViewById(R.id.gameTitle);
    }

    public void setFont(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "GoT.ttf");

        title.setTypeface(typeface);
    }

    public void setOnClickListener(){
        buttonOne.setOnClickListener(this);
        buttonTwo.setOnClickListener(this);
        buttonThree.setOnClickListener(this);
        buttonFour.setOnClickListener(this);
        buttonFive.setOnClickListener(this);
        buttonSix.setOnClickListener(this);
        buttonSeven.setOnClickListener(this);
        buttonEight.setOnClickListener(this);
        buttonNine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn1: selectedBtn ="n1"; break;
            case R.id.btn2: selectedBtn ="n2"; break;
            case R.id.btn3: selectedBtn ="n3"; break;
            case R.id.btn4: selectedBtn ="n4"; break;
            case R.id.btn5: selectedBtn ="n5"; break;
            case R.id.btn6: selectedBtn ="n6"; break;
            case R.id.btn7: selectedBtn ="n7"; break;
            case R.id.btn8: selectedBtn ="n8"; break;
            case R.id.btn9: selectedBtn ="n9"; break;

        }
    }

    public void setNumber(View view) {
    }

}
