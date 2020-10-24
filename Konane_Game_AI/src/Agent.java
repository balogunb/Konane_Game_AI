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
        //if the game has not been started;
        if (!state.started()){
            state.start();

            int [] arr= new int [] {1,4,5,8};

            int rand_int = rand.nextInt(4);
            System.out.println("Removed Piece at index "+ arr[rand_int] +", " + arr[rand_int] );

            state.remove(arr[rand_int],arr[rand_int]);
        }

        return state;
    }














    
}
