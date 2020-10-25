import java.util.*;

public class Piece {
    int color; //1 represents black and -1 represents white and 0 represents empty
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

    public void checkUp(ArrayList<Move> res, List<List<Piece>> grid){
        int x = this.position[0];
        int y = this.position[1];

        //if color is black
        if (color == 1){
            //check if you can move up
            if(this.position[0] >= 3){
                //check if a single up move is possible
                if(grid.get(x-2).get(y-1).getColor() == -1 && grid.get(x-3).get(y-1).getColor() == 0){
                    Move possible = new Move(this.position,'U',1);
                    res.add(possible);
                    //check if a double upward move is possible
                    if(this.position[0] >= 5){
                        if(grid.get(x-4).get(y-1).getColor() == -1 && grid.get(x-5).get(y-1).getColor() == 0){
                            Move possible2 = new Move(this.position,'U',2);
                            res.add(possible2);
                        }

                        //if triple move upward is possible
                        if(this.position[0] >= 7){
                            if(grid.get(x-6).get(y-1).getColor() == -1 && grid.get(x-7).get(y-1).getColor() == 0){
                                Move possible2 = new Move(this.position,'U',3);
                                res.add(possible2);
                            }
                        }

                    }
                }
            }
        }
        else if (color == -1){
            //check if you can move up
            if(this.position[0] >= 3){
                //check if a single up move is possible
                if(grid.get(x-2).get(y-1).getColor() == 1 && grid.get(x-3).get(y-1).getColor() == 0){
                    Move possible = new Move(this.position,'U',1);
                    res.add(possible);


                    //check if a double upward move is possible
                    if(this.position[0] >= 5){
                        if(grid.get(x-4).get(y-1).getColor() == 1 && grid.get(x-5).get(y-1).getColor() == 0){
                            Move possible2 = new Move(this.position,'U',2);
                            res.add(possible2);
                        }

                        //if triple move upward is possible
                        if(this.position[0] >= 7){
                            if(grid.get(x-6).get(y-1).getColor() == 1 && grid.get(x-7).get(y-1).getColor() == 0){
                                Move possible2 = new Move(this.position,'U',3);
                                res.add(possible2);
                            }
                        }
                    }
                }
            }
        }
    }


    public ArrayList<Move> possibleMoves(Board board){
        List<List<Piece>> grid = board.getBoard();
        ArrayList<Move> result = new ArrayList<Move>();

        //if its an empty spot
        if(color == 0){
            System.out.println("This is not not a movable piece");
        }
        else{
            checkUp(result,grid);
            checkDown(result,grid);
            checkLeft(result,grid);
            checkRight(result,grid);
        }
        return result;
    }

    private void checkRight(ArrayList<Move> res, List<List<Piece>> grid) {
        int x = this.position[0];
        int y = this.position[1];

        //if color is black
        if (color == 1){
            //check if you can move right
            if(this.position[1] <= 6){
                //check if a single right move is possible
                if(grid.get(x-1).get(y).getColor() == -1 && grid.get(x-1).get(y+1).getColor() == 0){
                    Move possible = new Move(this.position,'R',1);
                    res.add(possible);
                    //check if a double right move is possible
                    if(this.position[1] <= 4){
                        if(grid.get(x-1).get(y+2).getColor() == -1 && grid.get(x-1).get(y+3).getColor() == 0){
                            Move possible2 = new Move(this.position,'R',2);
                            res.add(possible2);
                        }
                        //if triple move right is possible
                        if(this.position[0] <= 2){
                            if(grid.get(x-1).get(y+4).getColor() == -1 && grid.get(x-1).get(y+5).getColor() == 0){
                                Move possible2 = new Move(this.position,'R',3);
                                res.add(possible2);
                            }
                        }

                    }
                }
            }
        }
        else if (color == -1){
            //check if a single up right is possible
            if(grid.get(x-1).get(y).getColor() == 1 && grid.get(x-1).get(y+1).getColor() == 0) {
                Move possible = new Move(this.position, 'R', 1);
                res.add(possible);
                //check if a double right move is possible
                if (this.position[1] <= 4) {
                    if (grid.get(x - 1).get(y + 2).getColor() == 1 && grid.get(x - 1).get(y + 3).getColor() == 0) {
                        Move possible2 = new Move(this.position, 'R', 2);
                        res.add(possible2);
                    }
                    //if triple move right is possible
                    if (this.position[0] <= 2) {
                        if (grid.get(x - 1).get(y + 4).getColor() == 1 && grid.get(x - 1).get(y + 5).getColor() == 0) {
                            Move possible2 = new Move(this.position, 'R', 3);
                            res.add(possible2);
                        }
                    }

                }
            }
        }
    }

    private void checkLeft(ArrayList<Move> result, List<List<Piece>> grid) {
    }

    private void checkDown(ArrayList<Move> result, List<List<Piece>> grid) {
    }
}
