







class Board:
    def __init__(self):
        rows, cols = (8,8)
        self.grid = [[0]*cols]*rows

    def print(self):
        print(self.grid)


class Piece:


    #Color 1 represents black and -1 represents white
    #position holds the position on the grid
    def __init__(self, color, position):
        self.color = color
        self.position = position

    def getColor(self):
        return self.color

    def printPiece(self):
        print(str(self.color)+ " "+ str(self.position))

    def getPosition(self):
        return self.position

    def checkMoves(self,board):
        print("hello")
        #check all possible moves using knowledge of the board






class State:
    def __init__(self,board=None):
        if board is None:
            self.grid = self.set_initial_state()
        else:
            self.grid = board.grid

    def set_initial_state(self):
        rows, cols = (8, 8)
        self.grid = [[0] * cols] * rows

        color = -1
        for i in range(len(self.grid)):
            color = color * -1
            color_j = color
            for j in range(len(self.grid[i])):
                curr_piece = Piece(color_j,(i+1,j+1))
                print (str(i) + " "+ str(j))
                #curr_piece.printPiece()
                self.grid[j][i] = curr_piece
                self.grid[0][0].printPiece()
                color_j = color_j * -1

        #self.grid[0][0].printPiece()



        return self.grid


    def printState(self):
        for i in range(len(self.grid)):
            print("")
            for j in range(len(self.grid[i])):
                print (str(i+1) + " "+ str(j+1))
                self.grid[i][j].printPiece()







initState = State();
#initState.printState()

