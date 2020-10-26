import java.util.*;


public class Move {
    int [] start; //start position
    char direction; // U = up, D = down, L = left, R = Right
    int times; // number of jumps



    public Move(int [] start, char direction, int times){
            this.start = start;
            this.direction = direction;
            this.times = times;
    }

    public Move(){

    }


    public char getDirection(){
        return direction;
    }

    public int getTimes(){
        return times;
    }

    public int[] getStart(){
        return start;
    }
}
