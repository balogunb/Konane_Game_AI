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
        this.white = new Agent(this.b,-1);
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
            int blackMoves = black.getAllMoves().size();
            int whiteMoves = white.getAllMoves().size();

            if(whiteMoves == 0) return Integer.MAX_VALUE;
            if(blackMoves == 0 ) return Integer.MIN_VALUE;


           return blackMoves - whiteMoves;
       //return black.getAllMoves().size();
    }

    public int getPlayer(){
        return player;
    }


    //Object first element is score and second is move
    public Result alphaBeta(Node b, int alpha, int beta, int depth){

        //
        if (depth == maxDepth){
            staticEvalCnt++;
            int value = b.staticEvaluation();
            Result res =  new Result(value,null);
            return res;
        }
        else if (b.getPlayer() ==  1){//if black player (Max player)
            Move bestMove = null;
            List<Node> successors = successorNodes();

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Result res = alphaBeta(curNode, alpha,beta, depth+1);

                if (res.val > alpha){
                    alpha = res.val;
                    bestMove = curNode.previousMove();
                }
                if (alpha >= beta){
                    cutoffs++;
                    res =  new Result(beta,bestMove);
                    return res;
                }
            }
            Result res =  new Result(alpha,bestMove);
            return res;
        }
        else if (b.getPlayer() == -1){
            Move bestMove = null;
            List<Node> successors = successorNodes();

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Result res = alphaBeta(curNode, alpha,beta, depth+1);

                if (res.val < beta){
                    beta = res.val;
                    bestMove = curNode.previousMove();
                }
                if (beta <= alpha){
                    cutoffs++;
                    res =  new Result(alpha,bestMove);
                    return res;
                }
            }
            Result res =  new Result(beta,bestMove);
            return res;
        }
        return new Result(0,null);
    }



    //Object first element is score and second is move
    public Result miniMax(Node b, int depth){


        if (depth == maxDepth){
            staticEvalCnt++;
            int value = b.staticEvaluation();
            Result res =  new Result(value,null);
            return res;
        }
        else if (b.getPlayer() ==  1){//if black player (Max player)
            Move bestMove = null;
            List<Node> successors = b.successorNodes();
            int currMax = Integer.MIN_VALUE;

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Result res = curNode.miniMax(curNode, depth+1);
                if(currMax < res.val){
                    currMax = res.val;
                    bestMove = curNode.previousMove();
                }

            }
            Result temp = new Result(currMax,bestMove);
            return temp;
        }
        else if (b.getPlayer() == -1){
            Move bestMove = null;
            List<Node> successors = b.successorNodes();
            int currMin = Integer.MAX_VALUE;

            for(int i = 0; i < successors.size(); i++){
                total_branches++;
                Node curNode = successors.get(i);
                Result res = curNode.miniMax(curNode, depth+1);
                if(currMin > res.val){
                    currMin = res.val;
                    bestMove = curNode.previousMove();
                }
            }
            Result temp = new Result(currMin,bestMove);
            return temp;
        }
        return new Result(0,null);
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
                //System.out.println("Possible Next board");
                //nextBoard.print();
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
