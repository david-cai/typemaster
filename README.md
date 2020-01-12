# MOSA Hackathon Submission: typemaster

# To Begin
Download the repository and run the "Runner" class in order to launch the program. To reset high scores, feel free to modify the included text files for the specific mode. For the training mode, the default text file values are 0 0 61.

# Inspiration
As a kid, I remember attending a computer skills class and playing typing games in order to learn how to type quickly. I wanted to make an improved version of the traditional typing game by adding unique modes and training mechanisms. I hope that this project can be useful to kids and adults alike who are learning to type for their first time or to improve their WPM gradually over time.

# What it does
Typemaster is a typing training/learning application that is appropriate for typists of all skills and ages. Upon starting the program, the user is prompted with four different modes: classic, endless, speed training, and beat typer.

Classic: This is the traditional waterfall typing game in which the user has to type words before they reach the bottom of the screen. Difficulty modifiers ensure that pacing is appropriate for all users. The difficulties will impact word frequency, drop speed, and difficulty level increase rates. Choose between easy, medium, or hard to find the right difficulty for you. At the end, the user's score is saved and if it is a top 5 score, it will be displayed.

Endless: This mode is identical to Classic in gameplay except that it does not end if a word is missed. Use this mode for warm up or to gradually train without worrying about losing.

Speed Training: This mode is the crux of the application's idea. When first starting, the user is prompted to take a benchmark test. The test gives the user 60 seconds to type as many words as possible. At the end, the user's benchmark score is saved. Then, the next time the user plays, the user must hit this benchmark score with 5 fewer seconds. For example, in the next run, the user hits the benchmark score in 52 seconds. The subsequent run will give the user 47 seconds. Failure to complete the run will give the user 5 extra seconds the run after. After 5 runs, the user is prompted to retake the benchmark test and set a new benchmark score to improve upon. The idea is that this mode would be played once every single day in order to reinforce typing skills and speed over time.

Beat Typer: This experimental, for fun, mode makes the user conduct a song by typing. Type to the rhythm of songs like Flight of the Bumblebee and try to accrue the highest score possible. Each correct note counts for 1 point and each missed note is -5 points. Miss 3 notes in a row and you fail. Like in Classic, this mode utilizes the same high score feature.

# How I built it
The entire project is coded in Java and uses Swing for the GUI elements.

# Accomplishments that I'm proud of
I'm proud of being able to put this entire project together in scratch with limited time. More importantly, I'm proud to put together a project that I genuinely think is useful for myself and anyone else interesting in improving typing. The end product ended up being more fun than I anticipated and there's plenty of room for additional features and improvements in the future.

# What I learned
I learned the importance of code management as it became more and more difficult to get my bearings as the project increased in complexity. Proper planning and thoughtful design would have helped a lot had I initially spent more time on those ideas rather than jumping in headfirst.

# What's next for Typemaster
I intend to expand the Speed Training mode into a full-stack web application. This application will have user accounts/authentication in order to store scores and progression in databases. Users would also be able to visit other users' profiles in order to see their progressions in a nice visual manner. Furthermore, making a web application like this would allow for a global high scores list. My hope is that such a feature can enforce more friendly competition and drive people to work on practicing typing with the mode every day.

Some other things I might look into in the future are creating a mobile version of the app and expanding the Beat Typer mode. The mobile version might be an interesting idea as learning how to type quickly on a phone is just as important as typing quickly on a keyboard nowadays. An expansion of Beat Typer can be a fun little side project that has the potential of morphing into its own full-fledged typing training game.
