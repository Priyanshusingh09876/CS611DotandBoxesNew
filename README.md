Game Collection - CS 611 Assignment #2

A Java game collection featuring Dots and Boxes and Sliding Puzzle, complete with player stats tracking.



Authors:

Priyanshu Singh(U73441029)

Zhujun Lyu(U06761622)



Table of Contents

Features

Game Descriptions

Prerequisites

Installation

How to Run

How to Play

Project Structure

Design Patterns

Future Enhancements

Features

Two classic games: Dots and Boxes (2-player) and Sliding Puzzle (single-player)

Customizable board sizes (2×2 up to 10×10)

Player statistics including wins, losses, averages, and records

Colorful console output using ANSI color codes

Strong input validation with helpful prompts

Replay option to play multiple games without restarting

Game Descriptions

Dots and Boxes

A classic two-player strategy game where players take turns connecting dots with lines. When a player completes a box, they score a point and get an extra turn. The game finishes once all boxes are completed, and the player with the most boxes wins.



Rules:



Players alternate turns adding horizontal or vertical lines

Completing a box awards a point and an extra turn

The game ends when all boxes are filled

The player with the highest score wins

Sliding Puzzle

A single-player number puzzle where you slide tiles into an empty space to arrange them in ascending order.



Rules:



Only tiles adjacent to the empty space can move

Arrange all tiles in ascending order (1 to n)

Aim to solve the puzzle in as few moves as possible

High scores (fewest moves) are tracked across games

Prerequisites

Java Development Kit (JDK) version 17 or higher

Optional: IDE like IntelliJ IDEA, Eclipse, or VS Code

Terminal or command prompt to run the app

Installation

Clone the repository:



bash



Copy code

git clone https://github.com/Priyanshusingh09876/CS611DotandBoxesNew.git

cd CS611DotandBoxesNew

Check Java version:



bash



Copy code

java -version

(Should display Java 17 or higher.)



How to Run

Using Command Line:

Navigate to the project folder:



bash



Copy code

cd DotsAndBoxesCS611

Compile the Java files:



bash



Copy code

javac -d out src/\*.java

Run the program:



bash



Copy code

java -cp out Main

Using IntelliJ IDEA:



Open IntelliJ IDEA

File → Open → Select the project folder

Wait for indexing to finish

Right-click



Copy code

Main.java in



Copy code

src/

Click Run 'Main.main()'

Using VS Code:



Open the project folder

If needed, install the Java Extension Pack

Open



Copy code

Main.java

Click Run or press F5

How to Play

At launch, you see:





Copy code

=== MAIN MENU ===

1\. Play Sliding Puzzle

2\. Play Dots and Boxes

3\. View Statistics

4\. Quit

Playing Dots and Boxes

Select option 2

Enter names for Player 1 and Player 2

Pick board size (rows and columns)

Players alternate selecting edges (H for horizontal, V for vertical) by entering row and column coordinates

Completing a box gives an extra turn

The game ends when all boxes are filled

The player with the most boxes wins

Example move:





Copy code

Enter edge type (H/V) or 'quit': H  

Enter Row (0-3) and Column (0-2)  

Row: 1  

Col: 1

Playing Sliding Puzzle

Select option 1

Enter your name

Choose board size

Enter the row and column of the tile to move

Only tiles adjacent to the empty space can move

Arrange numbers in order from 1 to n

Example move:





Copy code

Enter tile position to move:  

Row (0-3): 2  

Col (0-3): 1

Viewing Statistics

Select option 3 to see:

Total games played (for both games)

Per-player stats: wins, losses, ties, win rates

Average boxes per game (Dots and Boxes)

Best and average moves (Sliding Puzzle)

Quitting

Type



Copy code

quit or



Copy code

q anytime during gameplay to exit

Or choose option 4 from the main menu

Final stats will display on exit

Project Structure



Copy code

DotsAndBoxesCS611/

├── src/

│   ├── Main.java                # Main entry point

│   ├── GameController.java     # Menu and game selection logic

│   ├── DotsAndBoxesGame.java   # Dots and Boxes game logic

│   ├── DotsAndBoxesBoard.java  # Board management for Dots and Boxes

│   ├── SlidingPuzzleGame.java  # Sliding Puzzle game logic

│   ├── PuzzleBoard.java        # Puzzle board management

│   ├── Player.java             # Player data

│   ├── PlayerStatistics.java   # Tracking game stats

│   ├── Box.java                # Box objects for Dots and Boxes

│   ├── Edge.java               # Edge objects for Dots and Boxes

│   ├── InputHandler.java       # Input validation utility

│   └── ColorPrinter.java       # ANSI color output utility

├── out/                        # Compiled classes

├── .idea/                      # IntelliJ project files

├── .gitignore                  # Git ignore rules

├── DotsAndBoxesCS611.iml       # IntelliJ module file

└── README.md                   # This file

Design Patterns and Principles

Encapsulation: Classes keep their data and methods private or public as needed

Single Responsibility: Each class has one defined responsibility

Separation of Concerns: UI, game logic, and data handling are cleanly separated

Key classes:



Copy code

GameController: Handles the main menu and routes



Copy code

InputHandler: Central input validation



Copy code

PlayerStatistics: Tracks stats using a HashMap



Copy code

ColorPrinter: Prints colorful output



Copy code

Edge and



Copy code

Box: Game objects modeling Dots and Boxes

Board Implementation

Dots and Boxes:



Uses arrays for horizontal and vertical edges

Boxes reference their edges for quick completion checks

Sliding Puzzle:



2D array for tiles

Empty space tracking

Random shuffling that always yields solvable puzzles

Future Enhancements

AI opponent for Dots and Boxes

Save/load game states

Leaderboards and rankings

Difficulty levels (Easy, Medium, Hard)

Undo moves

Timer mode for speed challenges

Online multiplayer

GUI using JavaFX or Swing

Sound effects

Custom color themes

Technical Details

ANSI Color Support:



Yellow: dots, headers, warnings

Green: claimed edges, success messages

Cyan: player info, boxes

Red: error messages

Note: Some terminals might not display colors correctly. If you see strange characters, try a different terminal or disable colors in



Copy code

ColorPrinter.java.

Input Validation:



Checks that integers are in range

Ensures non-empty strings

Validates yes/no prompts

Allows quitting anytime by typing



Copy code

quit or



Copy code

q

Statistics Persistence:



Stats saved in-memory using HashMap

Future work: Save stats to a file or database for session persistence

Troubleshooting

Problem: Colors not showing properly

Solution: Use a terminal supporting ANSI codes (Windows Terminal, iTerm2, recent Linux terminals)



Problem: Java version errors

Solution: Use Java 17 or later



Problem: Class not found or compilation errors

Solution: Compile all source files and ensure correct classpath



Problem: Git authentication issues

Solution: Use Personal Access Tokens instead of passwords for GitHub





Game Collection - CS 611 Assignment #2

A Java game collection featuring Dots and Boxes and Sliding Puzzle, complete with player stats tracking.



Authors:

Priyanshu Singh(U73441029)

Zhujun Lyu(U06761622)



Table of Contents

Features

Game Descriptions

Prerequisites

Installation

How to Run

How to Play

Project Structure

Design Patterns

Future Enhancements

Features

Two classic games: Dots and Boxes (2-player) and Sliding Puzzle (single-player)

Customizable board sizes (2×2 up to 10×10)

Player statistics including wins, losses, averages, and records

Colorful console output using ANSI color codes

Strong input validation with helpful prompts

Replay option to play multiple games without restarting

Game Descriptions

Dots and Boxes

A classic two-player strategy game where players take turns connecting dots with lines. When a player completes a box, they score a point and get an extra turn. The game finishes once all boxes are completed, and the player with the most boxes wins.



Rules:



Players alternate turns adding horizontal or vertical lines

Completing a box awards a point and an extra turn

The game ends when all boxes are filled

The player with the highest score wins

Sliding Puzzle

A single-player number puzzle where you slide tiles into an empty space to arrange them in ascending order.



Rules:



Only tiles adjacent to the empty space can move

Arrange all tiles in ascending order (1 to n)

Aim to solve the puzzle in as few moves as possible

High scores (fewest moves) are tracked across games

Prerequisites

Java Development Kit (JDK) version 17 or higher

Optional: IDE like IntelliJ IDEA, Eclipse, or VS Code

Terminal or command prompt to run the app

Installation

Clone the repository:



bash



Copy code

git clone https://github.com/Priyanshusingh09876/CS611DotandBoxesNew.git

cd CS611DotandBoxesNew

Check Java version:



bash



Copy code

java -version

(Should display Java 17 or higher.)



How to Run

Using Command Line:

Navigate to the project folder:



bash



Copy code

cd DotsAndBoxesCS611

Compile the Java files:



bash



Copy code

javac -d out src/\*.java

Run the program:



bash



Copy code

java -cp out Main

Using IntelliJ IDEA:



Open IntelliJ IDEA

File → Open → Select the project folder

Wait for indexing to finish

Right-click



Copy code

Main.java in



Copy code

src/

Click Run 'Main.main()'

Using VS Code:



Open the project folder

If needed, install the Java Extension Pack

Open



Copy code

Main.java

Click Run or press F5

How to Play

At launch, you see:





Copy code

=== MAIN MENU ===

1\. Play Sliding Puzzle

2\. Play Dots and Boxes

3\. View Statistics

4\. Quit

Playing Dots and Boxes

Select option 2

Enter names for Player 1 and Player 2

Pick board size (rows and columns)

Players alternate selecting edges (H for horizontal, V for vertical) by entering row and column coordinates

Completing a box gives an extra turn

The game ends when all boxes are filled

The player with the most boxes wins

Example move:





Copy code

Enter edge type (H/V) or 'quit': H  

Enter Row (0-3) and Column (0-2)  

Row: 1  

Col: 1

Playing Sliding Puzzle

Select option 1

Enter your name

Choose board size

Enter the row and column of the tile to move

Only tiles adjacent to the empty space can move

Arrange numbers in order from 1 to n

Example move:





Copy code

Enter tile position to move:  

Row (0-3): 2  

Col (0-3): 1

Viewing Statistics

Select option 3 to see:

Total games played (for both games)

Per-player stats: wins, losses, ties, win rates

Average boxes per game (Dots and Boxes)

Best and average moves (Sliding Puzzle)

Quitting

Type



Copy code

quit or



Copy code

q anytime during gameplay to exit

Or choose option 4 from the main menu

Final stats will display on exit

Project Structure



Copy code

DotsAndBoxesCS611/

├── src/

│   ├── Main.java                # Main entry point

│   ├── GameController.java     # Menu and game selection logic

│   ├── DotsAndBoxesGame.java   # Dots and Boxes game logic

│   ├── DotsAndBoxesBoard.java  # Board management for Dots and Boxes

│   ├── SlidingPuzzleGame.java  # Sliding Puzzle game logic

│   ├── PuzzleBoard.java        # Puzzle board management

│   ├── Player.java             # Player data

│   ├── PlayerStatistics.java   # Tracking game stats

│   ├── Box.java                # Box objects for Dots and Boxes

│   ├── Edge.java               # Edge objects for Dots and Boxes

│   ├── InputHandler.java       # Input validation utility

│   └── ColorPrinter.java       # ANSI color output utility

├── out/                        # Compiled classes

├── .idea/                      # IntelliJ project files

├── .gitignore                  # Git ignore rules

├── DotsAndBoxesCS611.iml       # IntelliJ module file

└── README.md                   # This file

Design Patterns and Principles

Encapsulation: Classes keep their data and methods private or public as needed

Single Responsibility: Each class has one defined responsibility

Separation of Concerns: UI, game logic, and data handling are cleanly separated

Key classes:



Copy code

GameController: Handles the main menu and routes



Copy code

InputHandler: Central input validation



Copy code

PlayerStatistics: Tracks stats using a HashMap



Copy code

ColorPrinter: Prints colorful output



Copy code

Edge and



Copy code

Box: Game objects modeling Dots and Boxes

Board Implementation

Dots and Boxes:



Uses arrays for horizontal and vertical edges

Boxes reference their edges for quick completion checks

Sliding Puzzle:



2D array for tiles

Empty space tracking

Random shuffling that always yields solvable puzzles

Future Enhancements

AI opponent for Dots and Boxes

Save/load game states

Leaderboards and rankings

Difficulty levels (Easy, Medium, Hard)

Undo moves

Timer mode for speed challenges

Online multiplayer

GUI using JavaFX or Swing

Sound effects

Custom color themes

Technical Details

ANSI Color Support:



Yellow: dots, headers, warnings

Green: claimed edges, success messages

Cyan: player info, boxes

Red: error messages

Note: Some terminals might not display colors correctly. If you see strange characters, try a different terminal or disable colors in



Copy code

ColorPrinter.java.

Input Validation:



Checks that integers are in range

Ensures non-empty strings

Validates yes/no prompts

Allows quitting anytime by typing



Copy code

quit or



Copy code

q

Statistics Persistence:



Stats saved in-memory using HashMap

Future work: Save stats to a file or database for session persistence

Troubleshooting

Problem: Colors not showing properly

Solution: Use a terminal supporting ANSI codes (Windows Terminal, iTerm2, recent Linux terminals)



Problem: Java version errors

Solution: Use Java 17 or later



Problem: Class not found or compilation errors

Solution: Compile all source files and ensure correct classpath



Problem: Git authentication issues

Solution: Use Personal Access Tokens instead of passwords for GitHub







