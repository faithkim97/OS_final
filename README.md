## Can YOU Crack the Password?

Name: Hafsah Hanif, Faith Kim
Final Project


## Premise of the Game

You officially have 60 seconds to correctly guess the password before the police gets here *gasp*!
The program will inform you when you got the password correct or incorrect, the number of characters placed correctly, and
so much more! Play around with it!

If you're having trouble guessing the correct password, then worry not! Press the ! on your keyboard, and an AI will
help you solve it.


## How to Launch the Application
Open up our code on your IDE, and run the NewApplication class file. The terminal should show up, and you can begin
guessing the right password!

## Reflection

Writing this project was incredibly fun! It was interesting creating the CodeChecker class to check the user's input
for the correct password, and us having to guess it correctly. The most challenging part of creating this project was
implementing the timer. We originally wanted to create our own Timer class, and use thread.sleep() to have the timer decrement
on the terminal while the user is playing the game. What we had realized is that it stops the thread that has the user playing the game,
and starts running the thread that has the timer in it. Meaning, when the timer is decrementing, the user cannot play the game. Therefore,
we had to find an alternative way to create an effective timer. What we would have liked to have done is to have a closer oservation of
how to use threading when creating a timer, and how we can observe the decrementation of a timer on the terminal without obscuring the
actual gameplay.

Another challenging aspect of this project was creating the AI (Cheater class), specifically in designing the functionalities of the
Cheater class and how far it should help the user in guessing the password. We were thinking about synchronizing the doCheat() method
in the Cheater class so that it will momentarily pause the user's input gameplay (locking the resources for Cheater to use), then resume
the gameplay once Cheater has returned a guessed password to the user. However, it seems like Java is already handling it, so we are disappointed to
see that we did not have to put in more effort into making this happen (and thus try to have a deeper understanding of how threading and sychnronizing works).

For our final draft of the project, we are planning on creating a GUI system to visualize the gameplay, and possible work with threading
with the Swing library in Java.

