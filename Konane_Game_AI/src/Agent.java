import java.util.*;

public class Agent {

    Board state;
    int color;
    Random rand = new Random();



    public Agent(Board brd, int color){
        this.state = brd;
        this.color = color;

    }

    public Board move(){
        //if the game has not been started and it is a black piece
        if (state.noMovesMade() == 0 && color == 1){
            state.increaseMoveCount();

            int [] arr= new int [] {1,4,5,8};

            int rand_int = rand.nextInt(4);
            System.out.println("Removed " + color + " Piece at index "+ arr[rand_int] +", " + arr[rand_int] );

            state.remove(arr[rand_int],arr[rand_int]);

            return state;
        }

        //if it is white first move
        if(state.noMovesMade() == 1 && color == -1){
            state.increaseMoveCount();


            List<int []> adjPositions = state.getAdjacentSpots();
            int rand_int = rand.nextInt(adjPositions.size());
            System.out.println("Removed " + color + " Piece at index "+ adjPositions.get(rand_int)[0] +", " + adjPositions.get(rand_int)[1] );

            state.remove(adjPositions.get(rand_int)[0],adjPositions.get(rand_int)[1]);
            return state;
        }











        return state;
    }


    public List<Piece> getPiece(){
        List<Piece> pieces = new ArrayList<Piece>();

        for(int i = 0; i < 8;i++){
            List<Piece> row = state.getBoard().get(i);

            for(int j = 0; j < 8; j++){
                Piece curr = row.get(j);

                if(curr.getColor() == color){
                    pieces.add(curr);
                }
            }
        }
        return pieces;
    }


    public boolean hasMoves() {
        //if its a black agent
        if(color == 1){
            List<Piece> pieces = getPiece();
            List<Moves> moves = new ArrayList<Moves>();

            for(int i = 0; i < pieces.size(); i++){
                Piece curr = pieces.get(i);

                if(curr.possibleMoves(state).size() > 0){
                    return true;
                }
            }


        }

        return false;

    }
}
