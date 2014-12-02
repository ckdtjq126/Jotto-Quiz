CS349 Assignment 3

How to run:
	type 'make run'

How to remove .class files
	type 'make clean'


* this is runnable in VM (not in Windows)


Requirements for CS349 A3 from Webpage

1. my game loads words.txt in a3 folder and plays with it (requirement 1).
2. I used JottoModel.java as a model and view.java as view-controller combined (requirement 2).
3. Every view files and JottoModel.java used Swing Widget Toolkit (Requirement 3).
4. I used JTable to list the words that I have typed (requirement 4).
5. My A3 contains 3 views: IntroView.java, ProgressView.java and TextView.java (Requirement 5).
6. I used GridLayout() in Main.java,
	then used GridBagLayout() and BorderLayout() in view files (requirement 6).

Game Basics:
- Different lives are given for different difficulties.
	easy = 20, medium = 15, hard = 10
- Typing string containing non-char or string with length != 5 will not lost life.
- % matched showed that how close your word is to the targetWord 
	based on # of letters which are exactly matched and partially matched.
- You can use scroll to search the word you typed in JTable.

Enhancements - Design:
- Used Radio buttons to make it visually easy and clear when selecting difficulty desired.
- Used two images namely "start" and "exit" representing start button and exit Button.

Enhancements - Game:
These are mostly cheats.
- You can use hint (only once).
- If you type 'hints' or 'HINTS', it will show you a character of targetWord.
	the position of the character is random.
- To get more lives, type '+life' or '+LIFE'. It will increase the number of lives + 5 (only once as well).
	You cannot use this cheat on Hard Mode (only available in Easy and Medium Mode).
