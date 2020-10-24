import java.util.*;

public class Piece {
    int color; //1 represents black and -1 represents white and 0 represents empty
    int x,y; //piece positon on the board
    int[] position = new int [2];


    public Piece(int color, int x, int y){
        this.color = color;
        this.position[0] = x;
        this.position[1] = y;
    }


    public int getColor(){
        return this.color;
    }

    public int[] getPosition(){
        return position;
    }
    public void delete(){
        this.color = 0;
    }
    public void print(){
        //System.out.print("["+this.color+ " (" + this.position[0]+ ", " + this.position[1] + " )]");
        System.out.print(this.color);
    }


    public ArrayList<String> possibleMoves(Board board){

        return new ArrayList<String>();
    }
}
