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
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Game extends BaseAdapter {

    private Context context;
    private final Integer rows = 9;
    private final Integer cols = 9;

    //for continue
    private int[][] numArray = new int[rows][cols];

    private Resources resources;
    private ArrayList<String> arrayPictures;
    int unblockPositions[] = new int [rows * cols];
    int[][] helperArray;


    public Game(Context context) {
        this.context = context;
        arrayPictures = new ArrayList<>(cols * rows);
        resources = context.getResources();

        createField();
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
        shiftNumbersCycle();
        //transpose array
        transposeMatrix(numArray);
        //shake numbers
        shakeArray();
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
        while (i < 5)
        {
            int tmp = random.nextInt(80);
            arrayPictures.set(tmp, "nempty");
            unblockPositions[i] = tmp;
            helperArray[getRow(tmp)][getCell(tmp)] = -1;
            ++i;
        }
    }

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

    private int getCell(int position) {
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

    private void shiftNumbersCycle()
    {
        List<Integer> fill = new ArrayList<>();
        for ( int i = 0; i < 8; i++ ) {
            fill.add(i + 1);
        }
        Collections.shuffle(fill);

        List<Integer> to = new ArrayList<>();
        to.addAll(fill);
        Collections.shuffle(to);
        for(int i = 0; i < 8; ++i)
        {
            shiftNumbers(fill.get(i), to.get(i));
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
        int i = 0;
        do {
            int tempArray[] = numArray[i];
            int tempArrayTwo[] = numArray[i + 1];

            numArray[i] = numArray[i + 2];
            numArray[i + 1] = tempArray;
            numArray[i + 2] = tempArrayTwo;

            i += 3;
        }while (i < rows);
    }

    public boolean checkRepeatedValues (String selectedButton)
    {
        int repeatedX = 0;
        int repeatedY = 0;

        int number = Integer.parseInt(selectedButton.split("n")[1]);

        for (int i = 0; i < rows; ++i)
        {
            for (int j = 0; j < cols; ++j)
            {
                if(helperArray[i][j] == number){
                    repeatedX++;
                }

                if(helperArray[j][i] == number){
                    repeatedY++;
                }
            }

            if(repeatedX >= 2 || repeatedY >= 2)
            {
                return true;
            }

            repeatedX = 0;
            repeatedY = 0;
        }
        return false;
    }

    public boolean checkWinner(){
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0, i5 = 0, i6 = 0, i7 = 0, i8 = 0, i9 = 9;
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                if(helperArray[i][j] == 1) i1++;
                if(helperArray[i][j] == 2) i2++;
                if(helperArray[i][j] == 3) i3++;
                if(helperArray[i][j] == 4) i4++;
                if(helperArray[i][j] == 5) i5++;
                if(helperArray[i][j] == 6) i6++;
                if(helperArray[i][j] == 7) i7++;
                if(helperArray[i][j] == 8) i8++;
                if(helperArray[i][j] == 9) i9++;
            }
        }

        if(i1 == 9 && i2 == 9 && i3 == 9 &&
                i4 == 9 && i5 == 9 && i6 == 9 &&
                i7 == 9 && i8 == 9 && i9 == 9){
            return true;
        }else return false;
    }

    public void setNumber(int position, String selectedBtn) {
        for (int i = 0; i < unblockPositions.length; ++i)
        {
            if(unblockPositions[i] == position)
            {
                arrayPictures.set(position, selectedBtn);
                helperArray[getRow(position)][getCell(position)] = Integer.parseInt(selectedBtn.split("n")[1]);
                notifyDataSetChanged();
            }
        }
    }
}
