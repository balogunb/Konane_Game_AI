import java.util.*;

public class Agent {
    Board state;
    int color;
    Random rand = new Random();
    List<Piece> activePieces;

    public Agent(Board brd, int color){
        this.state = brd;
        this.color = color;
        this.activePieces = new ArrayList<Piece>();

    }


    //if algo = 1, move uses minMax withalphabeta
    //if algo = 2, move uses minMax
    //if algo = 3, move uses random agent
    //if algo = 4, move request for user input
    public Board move(int algo){
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

        Move action = new Move();
        if(algo == 1){
            action = alphaBetaHeurisitic();

        }
        else if (algo == 2){
            action = miniMaxHeurisitic();
        }
        else if (algo == 3){
            action = randomHeurisitic();

        }
        else if (algo == 4){

        }
        moveHelper(action);

        return state;
    }

    public Move miniMaxHeurisitic(){
        Node head = new Node(state,color, 3);
        Result res = head.miniMax(head,0);
        return  res.optimal;
    }

    public Move alphaBetaHeurisitic(){
        Node head = new Node(state,color, 2);
        Result res = head.alphaBeta(head,Integer.MIN_VALUE, Integer.MAX_VALUE,0);
        return  res.optimal;
    }

    public Move randomHeurisitic(){
        List<Move> movesList = getAllMoves();
        //Currently picking a random move
        int randMoveIndex = rand.nextInt(movesList.size());
        return movesList.get(randMoveIndex);

    }
    public List<Move> getAllMoves(){
        List<Move> movesList = new ArrayList<Move>();
        activePieces = getPiece();

        for(int i = 0; i < activePieces.size();i++){
            List<Move> currMoves = activePieces.get(i).possibleMoves(state);
            movesList.addAll(currMoves);
        }
        return  movesList;
    }

    public List<Move> getAllMoves(int clr){
        List<Move> movesList = new ArrayList<Move>();
        activePieces = getPiece(clr);

        for(int i = 0; i < activePieces.size();i++){
            List<Move> currMoves = activePieces.get(i).possibleMoves(state);
            movesList.addAll(currMoves);
        }
        return  movesList;
    }

    public Board nextState(Move action) {
        Board temp = new Board(state);

        //System.out.println("Previous board");
        //temp.print();

        temp.increaseMoveCount();
        int jump = action.getTimes()*2;// position to move
        int [] initial_pos = action.getStart();
        int move = 1;
        temp.remove(initial_pos[0] ,initial_pos[1]);
        //System.out.println("Moved from:" + initial_pos[0] + ", " + initial_pos[1]);

        //System.out.println(action.getDirection());
        switch (action.getDirection()){
            case 'U':
                temp.set(initial_pos[0] - jump ,initial_pos[1],color);
                //System.out.println("Moved To:" + (initial_pos[0] - jump) + ", " + initial_pos[1]);

                while(jump > 1){
                    temp.remove(initial_pos[0]- move++ ,initial_pos[1]);
                    jump--;
                }
                return temp;

            case 'D':
                temp.set(initial_pos[0] + jump ,initial_pos[1],color);
                //System.out.println("Moved To:" + (initial_pos[0] + jump) + ", " + initial_pos[1]);
                while(jump > 1){
                    temp.remove(initial_pos[0]+ move++ ,initial_pos[1]);
                    jump--;
                }
                return temp;
            case 'R':
                temp.set(initial_pos[0]  ,initial_pos[1] + jump,color);
                //System.out.println("Moved To:" + initial_pos[0] + ", " + (initial_pos[1]+ jump));

                while(jump > 1){
                    temp.remove(initial_pos[0] ,initial_pos[1] + move++);
                    jump--;
                }
                return temp;
            case 'L':
                temp.set(initial_pos[0]  ,initial_pos[1] - jump,color);
                //System.out.println("Moved To:" + initial_pos[0] + ", " + (initial_pos[1] - jump));

                while(jump > 1){
                    temp.remove(initial_pos[0] ,initial_pos[1] - move++);
                    jump--;
                }
        }
        return temp;
    }

    private void moveHelper(Move action) {

        state.increaseMoveCount();
        int jump = action.getTimes()*2;// position to move
        int [] initial_pos = action.getStart();
        int move = 1;
        state.remove(initial_pos[0] ,initial_pos[1]);
        System.out.println("Moved from:" + initial_pos[0] + ", " + initial_pos[1]);

        System.out.println(action.getDirection());
        switch (action.getDirection()){
            case 'U':
                state.set(initial_pos[0] - jump ,initial_pos[1],color);
                System.out.println("Moved To:" + (initial_pos[0] - jump) + ", " + initial_pos[1]);

                while(jump > 1){
                    state.remove(initial_pos[0]- move++ ,initial_pos[1]);
                    jump--;
                }
                return;

            case 'D':
                state.set(initial_pos[0] + jump ,initial_pos[1],color);
                System.out.println("Moved To:" + (initial_pos[0] + jump) + ", " + initial_pos[1]);
                while(jump > 1){
                    state.remove(initial_pos[0]+ move++ ,initial_pos[1]);
                    jump--;
                }
                return;
            case 'R':
                state.set(initial_pos[0]  ,initial_pos[1] + jump,color);
                System.out.println("Moved To:" + initial_pos[0] + ", " + (initial_pos[1]+ jump));

                while(jump > 1){
                    state.remove(initial_pos[0] ,initial_pos[1] + move++);
                    jump--;
                }
                return;
            case 'L':
                state.set(initial_pos[0]  ,initial_pos[1] - jump,color);
                System.out.println("Moved To:" + initial_pos[0] + ", " + (initial_pos[1] - jump));

                while(jump > 1){
                    state.remove(initial_pos[0] ,initial_pos[1] - move++);
                    jump--;
                }
                return;
        }
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

    public List<Piece> getPiece(int clr){
        List<Piece> pieces = new ArrayList<Piece>();

        for(int i = 0; i < 8;i++){
            List<Piece> row = state.getBoard().get(i);

            for(int j = 0; j < 8; j++){
                Piece curr = row.get(j);

                if(curr.getColor() == clr){
                    pieces.add(curr);
                }
            }
        }
        return pieces;
    }


    public boolean hasMoves() {
        //if its a black agent
        if(color != 0){
            activePieces = getPiece();

            for(int i = 0; i < activePieces.size(); i++){
                Piece curr = activePieces.get(i);

                if(curr.possibleMoves(state).size() > 0){
                    //curr.printSpecial();
                    return true;
                }
            }
            return false;
        }
        System.out.println("Empty stop error");
        return false;
    }

    public boolean hasMoves(int clr) {
        //if its a black agent
        if(clr != 0){
            activePieces = getPiece();

            for(int i = 0; i < activePieces.size(); i++){
                Piece curr = activePieces.get(i);

                if(curr.possibleMoves(state).size() > 0){
                    //curr.printSpecial();
                    return true;
                }
            }
            return false;
        }
        System.out.println("Empty stop error");
        return false;
    }




}
