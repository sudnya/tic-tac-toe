tic-tac-toe
===========
Artifacts to be returned:     
 
1. A short description of how you approached the problem including design tradeoffs and future improvements.  
3. A description of the algorithm used including the strengths and weaknesses.

    This solution has a main TicTacToe class that starts the program. It creates a game Engine class which drives the game. The main components of the Engine are 
    1. User Input (text)
    2. AI Player (the computer to play against human) 
    3. The game Board.

UserInput class:
----------------
    This class gets the user's input choices viz. 
        whether user wants to quit
        which location does the user want to move (sanitizes user input)
        calls AI to play (depending on whether X or O)
        updates the Board when move is legal

AI Player class:
----------------
    This is the class that evaluates all possible moves on a given Board, assumes that the opponent (human player) will make the best possible move and evaluates it's move accordingly. It adds the subtrees that lead to 'final state' and then makes a move which has the maximum 'final states' under it's subtree assuming the opponent will make the best possible move. The 'winning state' metric is different depending on the human player's choice of opponent. There are 3 possible choices Champ, Toby and George (from the problem statement). We have 3 child classes that inherit from the AI class, one each for Champ, Toby and George which determine what the 'best move' for this AI player is.
    Champ:
        considers all moves where it is the winner as the 'final state', tie is next best choice for Champ and losing is the worst option.
    Toby:
        considers all moves where there is a tie/draw as the 'final state'.
    George:
        considers moves randomly. 

The solution space for this problem is fairly small, so this code uses brute force method to explore the possible board configurations.
For future improvements (we could have bigger tic tac toe board) in which case it would be useful to use dynamic programming and exploiting symmetry to explore the solution space. The brute force solution is fast enough to be interactive with a human player and hence there is no need to introduce complexity for speedup.

Board class:
--------------
    This class stores the game board and it's state.  



2. Working java code with instructions on how to run it.            
The code can be found in the tic-tac-toe folder.  Use the shell scripts in the bin/ folder to execute. 

To play tic tac toe:
--------------------
java -jar bin/tictactoe.jar [-h] 

To run all combinations of 2 AI players against each other:
-----------------------------------------
java -jar bin/unittest.jar [-h]



4. Any alternate approaches considered.      
The MiniMax approach in Game Theory seemed like an interesting algorithm to implement, but would probably be an overkill for a 3x3 Tic Tac Toe (but more useful for Chess/Go configurations).
The next type of player to implement would probably PitPat which plays the popular Tit for Tat (symmetry) from game theory.



5. A test suite that measures that probability of each of the computer based opponents winning when pitted against one another.                        
Running the following command will run {Champ, Toby, George} X {Champ, Toby, George}
java -jar bin/unittest.jar
The attached both_ai.png shows a plot of how each player performs against the other in terms of wins, losses and ties.


6. Anything else you think would be valuable. 
It would be neat to have a UI / mouse clickable version of this instead of a text based input.  I haven't written Java in a long time, so please excuse the javaisms/syntax/formatting style I might have violated.
