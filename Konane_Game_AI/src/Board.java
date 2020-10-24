import java.util.*;

public class Board {
    List<List<Piece>> board;
    boolean started;

    public Board(){
        board = new ArrayList<List<Piece>>();
        started = false;

        int color = -1;

        for(int i= 0; i < 8; i++){
            List<Piece> row =  new ArrayList<Piece>();
            color = color * -1;
            int color_j = color;
            for (int j = 0; j < 8; j++){
                Piece curr = new Piece(color_j,i+1, j+1);
                row.add(curr);
                color_j *= -1;
            }
            board.add(row);
        }

    }

    public void remove(int x, int y){
        board.get(x-1).get(y-1).delete();
    }


    public boolean started(){
        return started;
    }
    public void start(){
        this.started = true;
    }


    public static void main(String[] args){
        Board brd = new Board();
        brd.print();

        Agent black = new Agent(brd,1);
        black.move();
        brd.print();

    }


    public void print(){
        for(int i = 0; i < board.size();i++){
            List<Piece> row = board.get(i);

            for(int j = 0; j < row.size(); j++){
                row.get(j).print();
                System.out.print(" ");
            }
            System.out.println("");
        }
    }
}
