#Operating Systems Final Project: Crack the Code!
Hafsah Hanif, Faith Kim
Spring 2019

## About the Application
Crack the Code is a Java application created with the Swing library. In this app, you get to play as a hacker trying to crack the password to get into a secret system. The application provides information to help you get the password right - this includes the percentage of letters correctly entered, percentage of letters placed correctly, and hints on the length of the password. Additionally, you can ask for assistance in cracking the password by using the "Cheat" option (though true respect will be given to those who do not ask for assistance).

Can YOU crack into the system? Are you a TRUE hacker?

## How to Run the Program
In our project, we have created a class called "Solver". Please run the Solver class to open up the application on your chosen IDE. A GUI window will appear, allowing you to play the game.

## Features in the application
We have implemented the following features in our application:

* **GUI**
  - **Password Field**: This is where the player can input a password guess.
  - **Code Checker Info**: When the player clicks the submit button on the GUI, the Java application calls the CodeChecker class and computes whether the player guessed the correct password, how far off they are in guessing the correct length of the password, correct characters of the password, and correct placement of the characters. This information is shown to the players below the password field in yellow text.
  - **Cheat Button**: If the player struggles to guess the correct password, the player can ask for assistance using the "cheat" option located at the top left corner of the screen.
  - **Start Solve Button**: The GUI does not activate the timer (the game) right away and allows the player to start whenever they want. Until this button is clicked, the player cannot access the password field, submit button, or the cheat button.
  - **Timer**: The player has 60 seconds to solve the password. The remaining time will appear in red text at the top right hand corner of the screen.
  - **Game Over**: If the player guesses the correct password before the timer runs out, then the application shows a message congratulating the user and providing them "access" to the hidden information. Otherwise, the application informs the player that access has been denied. In either cases, we display the exit button so that the player can voluntarily exit out of the program.

* **Backend**
  - **CodeChecker**: Checks whether the correct password was guessed. If it was not guessed correctly, then informs the player whether the guessed password is too long or too short, whether it has the correct characters and the correct character placements in the guessed password.
  - **Cheater**: Helps the user get a password that is similar to the real password (the algorithm only picks a password that matches the real password's length)
  - **GenPass**: Generates the true password that the player must guess. Uses a built-in dictionary to generate the password.
  - **Dictionary**: A class that can easily access the built-in dictionary (in Mac) via an array. Used to generate the password in GenPass.
  - **NewApplication**: Deprecated, but was used to create the command line version of the game.


**For debugging purposes, we print out the real password on the command line along with the GUI. Please check the command line for access to the true password.**

## Reflection

### Reflection from P3:
Writing this project was incredibly fun! It was interesting creating the CodeChecker class to check the user's input for the correct password, and having to guess it correctly. The most challenging part of creating this project was implementing the timer. We originally wanted to create our own Timer class, and use thread.sleep() to have the timer decrement on the terminal while the user is playing the game. However we realized that doing so stops the thread on which the user is playing the game, and starts running the thread that has the timer in it. So, when the timer was decrementing, the user was unable play the game. We, therefore, had to find an alternative way to create an effective timer. We would have liked to have had a closer observation of how to use threading when creating a timer, and observe the use of a timer on the terminal without obscuring the actual gameplay.

Another challenging aspect of this project was creating the AI (Cheater class), specifically in designing the functionalities of the Cheater class and how far it should help the user in guessing the password. We considered synchronizing the doCheat() method in the Cheater class so that it would momentarily pause the user's input gameplay (locking the resources for Cheater to use), then resume the gameplay once Cheater had returned a guessed password to the user. However, we found that Java already handles this - it was disappointing that we did not have to put in more effort to make this happen (and thus develop a deeper understanding of how threading and synchronization works).

### Reflection from final project portion
The most challenging part of this portion of the project was using the Swing library. Transitioning from the command line to the GUI was quite difficult because we had to change the way we called on specific methods (i.e. we needed to funnel all of the method calls through event listeners, rather than having a Scanner in a while loop that waits for new user input). Another difficult part of the project was creating the UI layout of the password field, code checker info, timer, and the cheat button. The different components would overlap in the panel, so we had to create multiple nested panels to get the desired layout.

In addition, our original implementation of the timer needed to be changed drastically. We originally used the timer from the Util library, but had to transition to using the timer from the Swing library. We chose to use Swing's timer because we wanted access to the action listener callback function; we needed the action listener to program the GUI behavior needed when the timer is activated. In P3, we were unable to show the decrementing of the timer on the command line (we would have had to print the count down on a separate lines on the command line for each decrease in seconds). Swing's timer allowed us to update our countdown label on the GUI, allowing us to override and repaint the new decremented time.

Overall, we believe that we learned a lot about how threading works through using the Swing library. We originally assumed that the timer is created on a different thread than the GUI, and the multithreading allows for the timer to decrement and have the GUI button functionalities work concurrently. When working on the project, we thought that the different components in the GUI were all on different threads (such as one thread per action listener and they are multithreaded together); however, we learned that everything in the GUI is single-threaded  in a thread called Event Dispatch Thread (EDT). We would have liked to have created a more complicated application that would require for us to observe different threads working together. Perhaps we were able to witness this when the submit button or the cheat button was clicked, calling on the CodeChecker class and the Cheat class respectively. While the GUI continues to be functional (evident by our timer), the code checker and the cheat classes take time to compute information for the user (possibly created on another thread to compute this information while the GUI is running concurrently).
