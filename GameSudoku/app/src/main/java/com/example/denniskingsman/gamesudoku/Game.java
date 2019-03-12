package com.example.denniskingsman.gamesudoku;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Random;

class Game extends BaseAdapter {

    private Context context;
    private final Integer rows = 9;
    private final Integer cols = 9;

    private int[][] numArray = new int[rows][cols];

    private Resources resources;
    private ArrayList<String> arrayPictures;
    int unblockPositions[] = new int [rows * cols];
    int[][] helperArray;


    public Game(Context context) {
        this.context = context;
        arrayPictures = new ArrayList<>(cols * rows);
        resources = context.getResources();
    }


    @Override
    public int getCount() {
        return rows * cols;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if(view == null){
            imageView = new ImageView(context);
        }else {
            imageView = (ImageView) view;
        }

        Integer drawableId = resources.getIdentifier(
                arrayPictures.get(position), "drawable", context.getPackageName());

        imageView.setImageResource(drawableId);
        return imageView;
    }

    private void createField(){
        //init array
        initArray();
        //shift numbers
        shiftNumbers(3, 1);
        //transpose array
        transposeMatrix(numArray);
        //shake numbers

        //transpose array
        transposeMatrix(numArray);
        //add pictures numbers to field
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; ++j)
            {
                arrayPictures.add("n" + numArray[i][j]);
            }
        }

        //hide numbers

        helperArray = numArray;
        Random random = new Random();
        int i = 0;
        while (i < 70)
        {
            int tmp = random.nextInt(80);
            arrayPictures.set(tmp, "nempty");
            unblockPositions[i] = tmp;
            helperArray[getRow(tmp)][getCell(tmp)] = -1;
            ++i;
        }
    }

    //TODO
    private int getRow(int position) {
        int row = 1;
        if(position <= 8){
            return 0;
        }else {
            while (position >= 0 && position <9)
            {
                row++;
            }

            while (position >= 9){
                position -= 9;
                row++;

            }

            return row - 1;
        }
    }

    public int getCell(int position) {
        if(position <= 8){
            return position;
        }else {
            return position % 9;
        }
    }

    private void transposeMatrix(int array[][]){
        int tmp;

        for (int i = 0; i < array.length; ++i)
        {
            for (int j = 0; j < array.length; ++j)
            {
                tmp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = tmp;
            }
        }
    }

    private void shiftNumbers(int count, int row){
        int index;

        for (int j = 0; j < cols; ++j)
        {
            index = (j + count) % 9 + 1;
            numArray[row][j] = index;
        }
    }

    private void initArray(){
        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < cols; ++j)
            {
                numArray[i][j] = j + 1;
            }
        }
    }

    private void shakeArray(){
        
    }
}
