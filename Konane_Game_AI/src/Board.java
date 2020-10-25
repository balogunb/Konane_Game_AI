import java.util.*;

public class Board {
    List<List<Piece>> board;
    boolean started;
    int movesMade;

    public Board(){
        board = new ArrayList<List<Piece>>();
        started = false;
        movesMade = 0;

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
    
    
    public boolean checkEmpty(int x, int y){ return 0  == board.get(x-1).get(y-1).getColor();}

    public int noMovesMade(){
        return movesMade;
    }

    public void increaseMoveCount(){
        this.movesMade++;
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
        
        Agent white = new Agent(brd, -1);
        white.move();
        brd.print();
        
        while (black.hasMoves()){
            black.move();
            brd.print();
        
            if(white.hasMoves()){
                white.move();
                brd.print();
            }
            else {
                System.out.println("Black won");
                break;
            }
            if(!black.hasMoves()){
                System.out.println("White won");
                break;
            }
        }
        

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




    public List<int[]> getAdjacentSpots() {
        int[] emptySpot = findEmptySpot();

        List<int[]> adjacentSpots = new ArrayList<int[]>();

        if(emptySpot[0] == 1){
            int [] bt = new int [] {2,1};
            int [] right = new int [] {1,2};
            adjacentSpots.add(bt);
            adjacentSpots.add(right);
        }
        else if (emptySpot[0] == 4){
            int [] bt = new int [] {5,4};
            int [] right = new int [] {4,5};
            int [] left = new int [] {4,3};
            int [] up = new int [] {3,4};
            adjacentSpots.add(bt);
            adjacentSpots.add(right);
            adjacentSpots.add(left);
            adjacentSpots.add(up);
        }
        else if (emptySpot[0] == 5){
            int [] bt = new int [] {6,5};
            int [] right = new int [] {5,6};
            int [] left = new int [] {5,4};
            int [] up = new int [] {4,5};
            adjacentSpots.add(bt);
            adjacentSpots.add(right);
            adjacentSpots.add(left);
            adjacentSpots.add(up);
        }
        else if (emptySpot[0] == 8){
            int [] left = new int [] {8,7};
            int [] up = new int [] {7,8};
            adjacentSpots.add(left);
            adjacentSpots.add(up);
        }
        else{}
        return adjacentSpots;
    }
    
    
    
    
    public int[] findEmptySpot(){
        if(this.checkEmpty(1,1)){
            return new int [] {1,1};
            
        }
        else if (this.checkEmpty(4,4)){
            return new int [] {4,4}; 
        }
        else if (this.checkEmpty(5,5)){
            return new int [] {5,5};
        }
        
        return new int [] {8,8};
    }

    public List<List<Piece>> getBoard() {
        return board;
    }
}
