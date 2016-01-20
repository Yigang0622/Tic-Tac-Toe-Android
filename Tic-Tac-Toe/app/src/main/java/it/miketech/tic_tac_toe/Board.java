package it.miketech.tic_tac_toe;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mike on 1/18/16.
 */
public class Board {

    private static int GAME_RESULT_WIN=1;
    private static int GAME_RESULT_FAIL=2;
    private static int GAME_RESULT_DRAW=0;

    int[][] gameBoardArr=new int[3][3];

    private int playCounter=1;

    public boolean gameOver = false;

    public Board copy(){

        Board copy=new Board();

        if (this.gameOver){
            copy.gameOver=true;
        }else {
            copy.gameOver=false;
        }

        copy.playCounter=Integer.valueOf(this.playCounter);

        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                copy.gameBoardArr[i][j]=this.gameBoardArr[i][j];
            }
        }

        return copy;
    }

    public void placePiece(int position){

        if (!gameOver){
            //Log.d("Board","placePiece vaild");
            int score=0;
            switch (getCurrentPlayer()){
                case 1:score=1;break;
                case 2:score=5;break;
            }

            switch (position){
                case 1: gameBoardArr[0][0]=score; break;
                case 2: gameBoardArr[0][1]=score; break;
                case 3: gameBoardArr[0][2]=score; break;
                case 4: gameBoardArr[1][0]=score; break;
                case 5: gameBoardArr[1][1]=score; break;
                case 6: gameBoardArr[1][2]=score; break;
                case 7: gameBoardArr[2][0]=score; break;
                case 8: gameBoardArr[2][1]=score; break;
                case 9: gameBoardArr[2][2]=score; break;
                default: break;
            }
            playCounter++;

            if (getGameResult()!=4){
                gameOver=true;
              // Log.d("Board","Game Over +Player"+getCurrentPlayer()+"Wins");
            }

        }

    }

    public int getCurrentPlayer() {
        int currentPlayer;
        if (this.playCounter%2==0)
            currentPlayer=2;
        else
            currentPlayer=1;
        return currentPlayer;
    }

    public int getGameResult(){

        int gameResult=4;

        boolean hasZero=false;
        int rowSum=0;
        int columnSum=0;
        int diagonalSum=0;
        int anotherDiagonalSum=0;

        //Test Row
        for (int i=0;i<3;i++){
            rowSum=gameBoardArr[i][0]+gameBoardArr[i][1]+gameBoardArr[i][2];
            if (rowSum==15){
                return GAME_RESULT_FAIL;
            }else if (rowSum==3){
                return GAME_RESULT_WIN;
            }
        }

        //Test Column
        for (int i=0;i<3;i++){
            columnSum=gameBoardArr[0][i]+gameBoardArr[1][i]+gameBoardArr[2][i];
            if (columnSum==15){
                return GAME_RESULT_FAIL;
            }else if (columnSum==3){
                return GAME_RESULT_WIN;
            }
        }

        //test diagonalSum
        diagonalSum=gameBoardArr[0][0]+gameBoardArr[1][1]+gameBoardArr[2][2];
        anotherDiagonalSum=gameBoardArr[2][0]+gameBoardArr[1][1]+gameBoardArr[0][2];

        if (diagonalSum==3||anotherDiagonalSum==3){
            return GAME_RESULT_WIN;
        }else if (diagonalSum==15||anotherDiagonalSum==15){
            return GAME_RESULT_FAIL;
        }



        //check for draw
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (gameBoardArr[i][j]==0){
                    hasZero=true;
                }
            }
        }

        if (!hasZero){
            return GAME_RESULT_DRAW;
        }


        return gameResult;
    }

    public List getAvailableSlots(){

        List<Integer> list =new ArrayList<>();

        int flag=1;
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                if (gameBoardArr[i][j]==0){
                    list.add(flag);
                }
                flag++;
            }
        }

        //Log.d("Board","AvaliableSolts: "+list.toString());

        return list;
    }

}
