package com.example.reza.eecs1022w18lab3r_p_sgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String getTextViewValue(int id){
        TextView textView = (TextView)findViewById(id);
        return textView.getText().toString();
    }

    public void setTextViewValue(int id, String input){
        TextView textView = (TextView)findViewById(id);
        textView.setText(input);
    }

    public String getSpinnerSelected(int id){
        Spinner spinner = (Spinner) findViewById(id);
        return spinner.getSelectedItem().toString();
    }

    public void round1pressed(View view){
        game = new Game(getTextViewValue(R.id.p1name), getTextViewValue(R.id.p2name));
        String p1choice = getSpinnerSelected(R.id.p1round1);
        String p2choice = getSpinnerSelected(R.id.p2round1);
        int result = game.match(p1choice, p2choice);
        switch (result){
            case 0: setTextViewValue(R.id.result, "round 1 finished: tie between "+ game.p1name + " and" + game.p2name); break;
            case 1: setTextViewValue(R.id.result, "round 1 finished: winner is "+game.p1name); break;
            case 2: setTextViewValue(R.id.result, "round 1 finished: winner is "+game.p2name); break;
        }
    }

    public void round2pressed(View view){
        String p1choice = getSpinnerSelected(R.id.p1round2);
        String p2choice = getSpinnerSelected(R.id.p2round2);
        game.match(p1choice, p2choice);
        if(Math.abs(game.p1winnings - game.p2winnings) > 1){
            game.isFinished = true;
        }

        if(game.isFinished){
            switch (game.overallWinner()){
                case 0: setTextViewValue(R.id.result, "round 2 finished: tie between "+ game.p1name + " and" + game.p2name + "(Game Over)"); break;
                case 1: setTextViewValue(R.id.result, "round 2 finished: winner is "+game.p1name + "(Game Over)"); break;
                case 2: setTextViewValue(R.id.result, "round 2 finished: winner is "+game.p2name + "(Game Over)"); break;
            }
        }
        else{
            switch (game.overallWinner()){
                case 0: setTextViewValue(R.id.result, "round 2 finished: tie between "+ game.p1name + " and" + game.p2name); break;
                case 1: setTextViewValue(R.id.result, "round 2 finished: winner is "+game.p1name); break;
                case 2: setTextViewValue(R.id.result, "round 2 finished: winner is "+game.p2name); break;
            }
        }
    }

    public void round3pressed(View view){
        if (game.isFinished){
            setTextViewValue(R.id.result, "Error: Game is already over");
            return;
        }

        String p1choice = getSpinnerSelected(R.id.p1round3);
        String p2choice = getSpinnerSelected(R.id.p2round3);
        game.match(p1choice, p2choice);

        switch (game.overallWinner()){
            case 0: setTextViewValue(R.id.result, "round 3 finished: tie between "+ game.p1name + " and" + game.p2name + "(Game Over)"); break;
            case 1: setTextViewValue(R.id.result, "round 3 finished: winner is "+game.p1name + "(Game Over)"); break;
            case 2: setTextViewValue(R.id.result, "round 3 finished: winner is "+game.p2name + "(Game Over)"); break;
        }
    }
}
