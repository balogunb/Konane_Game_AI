import java.util.*;
public class Node {
    Board b;
    int player;
    Agent black;
    Agent white;
    int staticEvalCnt;
    int maxDepth;
    int total_branches;
    int cutoffs;
    Move prev;

    public Node(Board b, int player, int maxDepth){
        this.b = new Board(b);
        this.player = player;
        this.black = new Agent(this.b,1);
        this.white = new Agent(this.b,1);
        this.staticEvalCnt = 0;
        this.maxDepth = maxDepth;
        this.prev = null;
        this.cutoffs = 0;
    }

    public Node(Board b, int player, int maxDepth, Move prev){
        this.b = new Board(b);
        this.player = player;
        this.black = new Agent(this.b,1);
        this.white = new Agent(this.b,1);
        this.staticEvalCnt = 0;
        this.maxDepth = maxDepth;
        this.prev = prev;
        this.cutoffs = 0;
    }

    public int  avgBranchFactor(){
        return total_branches / maxDepth;
    }

    public int staticEvaluation(){
           return black.getAllMoves().size() - white.getAllMoves().size();
    }

    public int getPlayer(){
        return player;
    }


    //Object first element is score and second is move
    public Object[] alphaBeta(Node b, int alpha, int beta, int depth){

        //
        if (depth == maxDepth){
            staticEvalCnt++;
            Object[] arr = new Object[2];
            arr[0] = b.staticEvaluation();
            arr[1] = null;
            return arr;
        }
        else if (b.getPlayer() ==  1){//if black player (Max player)
            Move bestMove = null;
            List<Node> successors = successorNodes();

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Object[] arr = miniMax(curNode, alpha,beta, depth+1);

                if ((int)arr[0] > alpha){
                    alpha = (int)arr[0];
                    bestMove = b.previousMove();
                }
                if (alpha >= beta){
                    cutoffs++;
                    Object[] temp = new Object[2];
                    temp[0] = beta;
                    temp[1] = bestMove;
                    return temp;
                }
            }
            Object[] temp = new Object[2];
            temp[0] = alpha;
            temp[1] = bestMove;
            return temp;
        }
        else if (b.getPlayer() == -1){
            Move bestMove = null;
            List<Node> successors = successorNodes();

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Object[] arr = miniMax(curNode, alpha,beta, depth+1);

                if ((int)arr[0] < beta){
                    beta = (int)arr[0];
                    bestMove = b.previousMove();
                }
                if (beta <= alpha){
                    cutoffs++;
                    Object[] temp = new Object[2];
                    temp[0] = alpha;
                    temp[1] = bestMove;
                    return temp;
                }
            }
            Object[] temp = new Object[2];
            temp[0] = beta;
            temp[1] = bestMove;
            return temp;

        }
        return new Object[2];
    }



    //Object first element is score and second is move
    public Object[] miniMax(Node b, int depth){


        if (depth == maxDepth){
            staticEvalCnt++;
            Object[] arr = new Object[2];
            arr[0] = b.staticEvaluation();
            arr[1] = null;
            return arr;
        }
        else if (b.getPlayer() ==  1){//if black player (Max player)
            Move bestMove = null;
            List<Node> successors = successorNodes();
            int currMax = Integer.MIN_VALUE;

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Object[] arr = miniMax(curNode, depth+1);
                if(currMax < (int)arr[0]){
                    currMax = (int)arr[0];
                    
                }
            }
        }
        else if (b.getPlayer() == -1){
            Move bestMove = null;
            List<Node> successors = successorNodes();

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Object[] arr = miniMax(curNode,  depth+1);
            }
        }
        return new Object[2];
    }

    private Move  previousMove() {
        return prev;
    }


    public List<Node> successorNodes(){
        List<Node> successor = new ArrayList<Node>();

        List<Move> allMoves;

        //Black player
        if(player == 1){
            allMoves = black.getAllMoves(1);
            for(int i = 0; i <allMoves.size();i++){
                Board nextBoard = black.nextState(allMoves.get(i));
                Node tempNode = new Node(nextBoard,-1,maxDepth, allMoves.get(i));
                successor.add(tempNode);
            }
        }
        else if (player == -1){//If white player
            allMoves = white.getAllMoves(-1);
            for(int i = 0; i <allMoves.size();i++){
                Board nextBoard = white.nextState(allMoves.get(i));
                Node tempNode = new Node(nextBoard,1,maxDepth, allMoves.get(i));
                successor.add(tempNode);
            }
        }
        return successor;
    }
}
