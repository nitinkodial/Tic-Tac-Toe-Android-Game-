package com.company.nitinkodialbail.tictactoe;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 - yellow, 1 - red
    int activePlayer = 0;

    //2 - unplayed location
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    //all combinations of straight lines covering 3 locations
    int[][] winningGames = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    //flag for if game is finished
    boolean isGameActive = true;

    public void dropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(view.getTag().toString());

        //if location is unplayed
        if(gameState[tappedCounter]==2&&isGameActive) {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);

            //System.out.println(tappedCounter);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            for(int[] currWinningGame:winningGames){
                if(gameState[currWinningGame[0]]==gameState[currWinningGame[1]]&&gameState[currWinningGame[1]]==gameState[currWinningGame[2]]
                        &&gameState[currWinningGame[0]]!=2){

                    isGameActive = false;
                    String winnerStr = "";
                    if(gameState[currWinningGame[0]]==0){
                        winnerStr = "Yellow";
                    }
                    else{
                        winnerStr = "Red";
                    }
                    winnerStr = winnerStr+ " has won!";
                    activateResultMsgView(winnerStr);
                }
                else{
                    boolean gameOver = true;
                    for(int i = 0;i<gameState.length;i++){
                        if(gameState[i]==2){
                            gameOver = false;
                        }
                    }
                    if(gameOver){
                        activateResultMsgView("It's a draw!");
                    }
                }

            }
        }

    }

    public void activateResultMsgView(String str){
        LinearLayout linearLayoutView = (LinearLayout) findViewById(R.id.playAgainView);
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(str);
        linearLayoutView.setVisibility(View.VISIBLE);
    }
    public void playAgain(View view){
        //restart new game with yellow first
        activePlayer = 0;

        LinearLayout linearLayoutView = (LinearLayout) findViewById(R.id.playAgainView);
        linearLayoutView.setVisibility(View.INVISIBLE);

        //reset game
        for(int i = 0 ;i<gameState.length;i++){
            gameState[i]=2;
        }

        isGameActive = true;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i<gridLayout.getChildCount();i++){
            ImageView  imgView = (ImageView) (gridLayout.getChildAt(i));
            imgView.setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
