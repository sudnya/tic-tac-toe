tic-tac-toe
===========
Artifacts to be returned:     
 
1. A short description of how you approached the problem including design tradeoffs and future improvements.  
3. A description of the algorithm used including the strengths and weaknesses.
This solution has a main TicTacToe class that starts the program. It creates a game Engine class which drives the game. 
The main components of the Engine are 
(a.) User Input (text)
(b.) AI Player (the computer to play against human) 
(c.) The game Board. 
UserInput class:
----------------
    This class gets the user's input choices viz. 
    whether user wants to quit
    which location does the user want to move (sanitizes user input)
    calls AI to play (depending on whether X or O)
    updates the Board when move is legal.

AI Player class:
----------------
    This is the class that evaluates all possible moves on a given Board and then makes the best move against the human player. The 'best move' metric is different depending on the human player's choice of opponent. There are 3 possible choices Champ, Toby and George (from the problem statement). We have 3 child classes that inherit from the AI class, one each for Champ, Toby and George which determine what the 'best move' for this AI player is.
    Champ:
        considers all moves where it is the winner as early as possible as the 'best move'.
    Toby:
        considers all moves where there is a tie/draw as early as possible as the 'best move'.
    George:
        considers moves randomly. 

The solution space for this problem is fairly small, so this code uses brute force method to explore the possible board configurations.
For future improvements (we could have bigger tic tac toe board) in which case it would be useful to use dynamic programming to explore the solution space.



# TODO:
2. Working java code with instructions on how to run it.            
Use the shell scripts in the bin/ folder to execute. 
Set the DIRECTORY_ROOT constant to point to DIRECTORY_ROOT_CMDLINE (default)
Now go to to the project bin folder in command line and run, java -jar ./TicTacToe.jar



4. Any alternate approaches considered.      
The MiniMax approach in Game Theory seemed like an interesting algorithm to implement, but would probably be an overkill for a 3x3 Tic Tac Toe (but more useful for Chess/Go configurations).



5. A test suite that measures that probability of each of the computer based opponents winning when pitted against one another.                        
# TODO:



6. Anything else you think would be valuable. 
It would be neat to have a UI / mouse clickable version of this instead of a text based input.
I haven't written Java in a long time, so please ignore the javaisms/syntax/formatting style I might have violated.
