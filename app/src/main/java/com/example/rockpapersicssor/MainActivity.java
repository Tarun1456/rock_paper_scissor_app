package com.example.rockpapersicssor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textscoreboard,textResult,textturnsleft;
    private Button buttonrock, buttonpaper,buttonscissors;
    private int score=0;
    private int turnsleft=8;
    private static String[] options= {"Rock","Paper","Scissors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textscoreboard= findViewById(R.id.scoreboard);
        textturnsleft=findViewById(R.id.turnsleft);
        textResult= findViewById(R.id.textresult);


        buttonrock= findViewById(R.id.b1);
        buttonpaper=findViewById(R.id.b2);
        buttonscissors=findViewById(R.id.b3);


        if (textscoreboard == null || textturnsleft == null || textResult == null ||
                buttonrock == null || buttonpaper == null || buttonscissors == null) {

        } else {
            updateScoreBoard();

            buttonrock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playGame("Rock");
                }
            });

            buttonpaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playGame("Paper");
                }
            });

            buttonscissors.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playGame("Scissors");
                }
            });
        }
    }

    private void updateScoreBoard(){
        textscoreboard.setText("Score: " + score);
    }

    private void playGame(String choice){
        Random random = new Random();
        int computerchoiceIndex = random.nextInt(3);
        String computerchoice = options[computerchoiceIndex];
        String result = determineResult(choice, computerchoice);
        displayResult(result);
        turnsleft--;
        updateTurnsLeft();
        if(turnsleft == 0) {
            endGame();
        }
    }

    private String determineResult(String userchoice, String computerchoice){

        
        if(userchoice.equals(computerchoice)) {
            return "It's a Tie";
        } else if (userchoice.equals("Rock") && computerchoice.equals("Scissors") ||
                userchoice.equals("Paper") && computerchoice.equals("Rock") ||
                userchoice.equals("Scissors") && computerchoice.equals("Paper")) {
            score++;
            return "User Wins";
        } else {
            score--;
            return "Computer wins";
        }
    }

    private void displayResult(String result){
        textResult.setText(result);
        updateScoreBoard();
    }

    private void updateTurnsLeft(){
        textturnsleft.setText("Turns Left: " + turnsleft);
    }

    private void endGame(){
        buttonrock.setEnabled(false);
        buttonpaper.setEnabled(false);
        buttonscissors.setEnabled(false);

        if(score > 0){
            textResult.setText("You won the game");
        } else if (score < 0) {
            textResult.setText("You lose the game");
        } else {
            textResult.setText("Oops Tie match");
        }
    }
}
