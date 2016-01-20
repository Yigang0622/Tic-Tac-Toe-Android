package it.miketech.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private final int FP = ViewGroup.LayoutParams.WRAP_CONTENT;

    private  Button button;
    private  Button useAIButton;
    private TableLayout mTableLayout;
    private TextView textView;
    private Board gameBoard;

    public static int counter= 1;

    private boolean CPUCtrl = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGameBoard();

        gameBoard=new Board();
        textView= (TextView) findViewById(R.id.textView);
        button= (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

    }

    private void initGameBoard(){

        mTableLayout= (TableLayout) findViewById(R.id.table_layout);
        mTableLayout.setStretchAllColumns(true);
       // mTableLayout.setBackgroundResource(R.color.blue);

        int counter = 1;
        for(int row=0;row<3;row++) {
            TableRow tableRow=new TableRow(this);
            for(int col=0;col<3;col++) {

                Button button=new Button(this);

                button.setTag(counter);

                //button.setText(row+","+col+"\nTag:"+button.getTag());

                button.setOnClickListener(this);
                button.setWidth(300);
                button.setHeight(300);
                button.setTextSize(40);

                tableRow.addView(button);

                counter++;

            }

            mTableLayout.addView(tableRow, new TableLayout.LayoutParams(FP, WC));
        }


    }


    @Override
    public void onClick(View v) {



        String Player1="O";
        String Player2="X";
        String place="";

        if (!gameBoard.gameOver){
            switch (gameBoard.getCurrentPlayer()){
                case 1: place=Player1;break;
                case 2: place=Player2;break;
            }


            int choice = Integer.valueOf(v.getTag().toString());
            gameBoard.placePiece(choice); ((Button)v).setText(place);
            updateUI();

        }

    }

    private void updateUI(){

        switch (gameBoard.getGameResult()){
            case 1:  textView.setText("Player 1 Wins!"); break;
            case 2:  textView.setText("Player 2 Wins!"); break;
            case 0:  textView.setText("Game Draw!");    break;
            default: break;
        }


    }

    private void resetGame(){
        mTableLayout.removeAllViews();
        textView.setText(" ");
        initGameBoard();
        gameBoard=new Board();
    }



}
