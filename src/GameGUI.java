import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author David Cai
 * This class allows for the game to be run and contains all functionality and visuals related to the game
 */
public class GameGUI extends JPanel implements KeyListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	JTextField currentWord;
	JLabel pointCount;
	JLabel levelCount;
	private JTextField gameOverField;
	private int points;
	private javax.swing.Timer time;
	private java.util.Timer timer2;
	private java.util.Timer timer3;
	private int currentTime;
	private int difficulty;
	private int levelCounter = 1;
	private int selectDiff;
	private ArrayList<Word> wordsInGame;
	private ArrayList<String> wordOptions;
	private ArrayList<Integer> classicHighScores;
	private ArrayList<Integer> musicHighScores;
	private ArrayList<Integer> benchmarkScores;
	private int modeSelect;
	private int benchmarkCount = 0;
	private int runCount;
	
	private char c;
	private char nextC;
	private char thirdC;
	private char fourthC;
	private char fifthC;
	private JLabel letter;
	private JLabel second;
	private JLabel third;
	private JLabel fourth;
	private JLabel fifth;
	private JLabel beat;
	private JLabel statusCheck;
	private JLabel status;
	private JLabel score;
	private JLabel strike;
	private JLabel countdown;
	private MediaPlayer player;
	private int scoreCount = 0;
	private int strikeCount = 0;
	private int x = 150;
	private int startCount = 5;
	private int timeLeft;
	private int timeUsed;
	
	private JLabel scoreOne;
	private JLabel scoreTwo;
	private JLabel scoreThree;
	private JLabel scoreFour;
	private JLabel scoreFive;
	

	
	/**
	 * Constructor launches the game and sets up the appropriate files and variables
	 * @throws FileNotFoundException
	 */
	public GameGUI() throws FileNotFoundException {
		setLayout(null);
		setBackground(Color.BLACK);
		wordOptions = getWords("medium_length.txt");
		classicHighScores = getScores("classicHighScores.txt");
		musicHighScores = getScores("musicHighScores.txt");
		benchmarkScores = getScores("trainingBenchmarks.txt");
		runCount = benchmarkScores.get(1); //this is the training run number 
		if (runCount == 0) {		
			timeLeft = benchmarkScores.get(2);
		} else {
			if (benchmarkScores.get(2) == 61) {
				timeLeft = benchmarkScores.get(2) - 3; //otherwise we miss a second
			} else {
				timeLeft = benchmarkScores.get(2) - 2;
			}
			
		}
		
		currentWord = new JTextField("");
		currentWord.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				enterUserWord(modeSelect);
			}
			
		});
		titleScreen();
	}
	
	/**
	 * This method populates the title screen
	 */
	public void titleScreen() {
		removeAll();
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 32);
		Font font2 = new Font("SansSerif", Font.PLAIN, 20);
		
		JLabel title = new JLabel("Welcome to Typemaster");
		title.setSize(500,50);
		title.setLocation(50, 200);
		title.setFont(font1);
		title.setForeground(Color.WHITE);
		title.setHorizontalAlignment(JTextField.CENTER);
		
		JButton startBtn = new JButton("Start");
		startBtn.setSize(80,50);
		startBtn.setLocation(260, 275);
		startBtn.setFont(font2);
		
		add(title);
		add(startBtn);
		
		setVisible(true);
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				chooseMode();
		    }          
		});
		
	}
	
	/**
	 * This method populates the choose screen method
	 */
	public void chooseMode() {
		removeAll();
		JButton classicBtn = new JButton("Classic");
		JButton endlessBtn = new JButton("Endless");
		JButton speedBtn = new JButton("Speed Training");
		JButton musicBtn = new JButton("Beat Typer");
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 32);
		//Font font2 = new Font("SansSerif", Font.PLAIN, 20);
		
		classicBtn.setSize(140,50);
		classicBtn.setLocation(150, 200);
		classicBtn.setFont(font1);
		
		endlessBtn.setSize(140,50);
		endlessBtn.setLocation(310, 200);
		endlessBtn.setFont(font1);
		
		speedBtn.setSize(300,50);
		speedBtn.setLocation(150, 275);
		speedBtn.setFont(font1);
		
		musicBtn.setSize(300,50);
		musicBtn.setLocation(150, 350);
		musicBtn.setFont(font1);
		
		JLabel mode = new JLabel("Choose Mode:");
		mode.setSize(300,30);
		mode.setLocation(150, 150);
		mode.setForeground(Color.WHITE);
		mode.setFont(font1);
		mode.setHorizontalAlignment(JTextField.CENTER);
		
		add(mode);
		add(classicBtn);	
		add(endlessBtn);
		add(speedBtn);
		add(musicBtn);
		setVisible(true);
		classicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeSelect = 0;
				setVisible(false);
				difficultySelect();
		    }          
		});
		endlessBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeSelect = 1;
				setVisible(false);
				difficultySelect();
		    }          
		});
		speedBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeSelect = 2;
				setVisible(false);
				startTraining();
		    }          
		});
		musicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeSelect = 3;
				setVisible(false);
				startMusic();
		    }          
		});
		
	}
	
	/**
	 * This method populates the difficulty select method
	 */
	public void difficultySelect() {
		removeAll();
		JButton easyBtn = new JButton("Easy");
		JButton mediumBtn = new JButton("Medium");
		JButton hardBtn = new JButton("Hard");
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 20);
		
		easyBtn.setSize(100,50);
		easyBtn.setLocation(250, 150);
		easyBtn.setFont(font1);
		
		mediumBtn.setSize(100,50);
		mediumBtn.setLocation(250, 250);
		mediumBtn.setFont(font1);
		
		hardBtn.setSize(100,50);
		hardBtn.setLocation(250, 350);
		hardBtn.setFont(font1);
		
		add(easyBtn);	
		add(mediumBtn);
		add(hardBtn);
		setVisible(true);
		easyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectDiff(0);
				setVisible(false);
				startClassic();
			}        
		});
		mediumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectDiff(3);
				setVisible(false);
				startClassic();
			}        
		});
		hardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSelectDiff(5);
				setVisible(false);
				startClassic();
			}        
		});
	}
	
	/**
	 * This method creates an instance of the Classic mode and populates it appropriately
	 */
	public void startClassic() {
		removeAll();
		Font font1 = new Font("SansSerif", Font.PLAIN, 16);
		JLabel divider = new JLabel("");
		divider.setSize(600, 1);
		divider.setLocation(0, 520);
		divider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

		JLabel typeHere = new JLabel("Type Here:");
		typeHere.setSize(100,30);
		typeHere.setLocation(100, 535);
		typeHere.setFont(font1);
		typeHere.setForeground(Color.WHITE);
		
		currentWord.setSize(280, 30);
		currentWord.setLocation(200, 535);
		currentWord.setForeground(Color.WHITE);
		currentWord.setBackground(Color.BLACK);
		currentWord.setFont(font1);
		currentWord.setEditable(true);
		currentWord.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		JLabel score = new JLabel("Score:");
		score.setSize(60,30);
		score.setLocation(20, 10);
		score.setFont(font1);
		score.setForeground(Color.WHITE);
		score.setHorizontalAlignment(JTextField.CENTER);
		
		String diff = "";
		if (getSelectDiff() == 0) {
			diff = "Easy";
		} else if (getSelectDiff() == 3) {
			diff = "Medium";
		} else {
			diff = "Hard";
		}
		
		JLabel diffDisp = new JLabel("Difficulty: " + diff);
		diffDisp.setSize(150,30);
		diffDisp.setLocation(225, 10);
		diffDisp.setFont(font1);
		diffDisp.setForeground(Color.WHITE);
		diffDisp.setHorizontalAlignment(JTextField.CENTER);
			
		pointCount = new JLabel("0");
		pointCount.setSize(60,30);
		pointCount.setLocation(80, 10);
		pointCount.setForeground(Color.WHITE);
		pointCount.setFont(font1);
		
		JLabel level = new JLabel("Level:");
		level.setSize(60,30);
		level.setForeground(Color.WHITE);
		level.setLocation(510, 10);
		level.setFont(font1);
		
		
		levelCount = new JLabel("");
		levelCount.setSize(10,30);
		levelCount.setLocation(560, 10);
		levelCount.setText(""+levelCounter);
		levelCount.setForeground(Color.WHITE);
		levelCount.setFont(font1);

		JButton exitBtn = new JButton("Exit");
		exitBtn.setSize(50,35);
		exitBtn.setLocation(515, 35);
		exitBtn.setFont(font1);
		
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelCounter = 1;
				endGame(0);
				setVisible(false);
				titleScreen();
		    }          
		});
		
		add(score);
		add(pointCount);
		add(divider);
		add(typeHere);
		add(currentWord);
		add(diffDisp);
		add(level);
		add(levelCount);
		add(exitBtn);
		setVisible(true);
		time = new javax.swing.Timer(100, this);
		time.setInitialDelay(0);

		wordsInGame = new ArrayList<Word>();
		points = 0;
		currentTime = 0;
		difficulty = 210;
		
		time.start();
		
	}

	
	/**
	 * This method prompts the Game Over or Run Complete screen at the end of each mode.
	 * It then allows the user to restart back at the title screen if they choose so.
	 * @param game The int variable to control for which mode is played
	 */
	private void endGame(int game) {
		removeAll();

		Font font1 = new Font("SansSerif", Font.BOLD, 40);
		if (game == 2) {
			timeUsed = 0;
			gameOverField = new JTextField("Run Complete");
			System.out.println(runCount);
			if (runCount != 5) {
				timeLeft = benchmarkScores.get(2) - 2;
			} else {
				timeLeft = 61;
				benchmarkScores.set(2, timeUsed);
			}
			try {
				updateScores("trainingBenchmarks.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			points = 0;
		} else {
			gameOverField = new JTextField("Game Over");
		}
		gameOverField.setEditable(false);
		gameOverField.setSize(300,50);
		gameOverField.setFont(font1);
		gameOverField.setBackground(Color.BLACK);
		gameOverField.setForeground(Color.WHITE);
		gameOverField.setLocation(150, 245);
		gameOverField.setHorizontalAlignment(JTextField.CENTER);
		add(gameOverField);
		gameOverField.setVisible(true);
		
		JButton b1 = new JButton("Play Again");
		b1.setSize(100,50);
		b1.setLocation(250, 300);
		add(b1);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				titleScreen();
		    }          
		});
		
		setVisible(true);
		
		if (game == 0) { //classic mode only
			time.stop();
		} else if (game == 1) {
			startCount = 5;
			scoreCount = 0;
			strikeCount = 0;
			strike.setText("Strike:" + strikeCount);
			score.setText("Score: " + scoreCount);
			timer2.cancel();
			player.stop();
		}
	}
	
	/**
	 * This mode allows the user to enter the typed word and send it for comparison.
	 * @param modeSelect This int selects which mode is being played
	 */
	public void enterUserWord(int modeSelect) {
		String userInput = currentWord.getText();
		currentWord.setText("");
		if (wordIsInGame(userInput)) {
			int addPts = 0;
			if (getSelectDiff() == 0) { //easy
				addPts = (userInput.length() * 1);
			} else if (getSelectDiff() == 3) { //medium
				addPts = (userInput.length() * 2);
			} else { //hard
				addPts = (userInput.length() * 3);
			}
			points = points + addPts;
			pointCount.setText(""+points);
			removeWord(userInput);
			updateUI();
			if (modeSelect == 2) {
				makeNewWord(0);
			}
		}
	}
	
	/**
	 * This method checks that the user typed word is on the board currently
	 * @param userInput User entered string
	 * @return Boolean for if word is correct
	 */
	public boolean wordIsInGame(String userInput) {
		java.util.Iterator<Word> wordIterator = wordsInGame.iterator();
		while (wordIterator.hasNext()) {
			Word thisWord = wordIterator.next();
			if (thisWord.equals(userInput)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This removes the word from the board if the user entered word is correct
	 * @param userInput User entered string
	 */
	private void removeWord(String userInput) {
		java.util.Iterator<Word> wordIterator = wordsInGame.iterator();
		while(wordIterator.hasNext()) {
			Word thisWord = wordIterator.next();
			if(thisWord.equals(userInput)) {
				remove(thisWord.wordField);
				wordIterator.remove();
			}
		}
	}
	

	/**
	 * This method makes a new word to be dropped when prompted
	 * @param mode This int controls which mode we are in (classic vs endless)
	 */
	private void makeNewWord(int mode) {
			String randomWord = getRandomWord();
			Word newWord;
			if (mode == 0) {
				newWord = new Word(randomWord, 0, this);
			} else {
				Random r = new Random();
				int randNum = r.nextInt(3) + levelCounter + (getSelectDiff() * 2);
				newWord = new Word(randomWord, randNum, this);
			}
			if (newWord != null) {
				wordsInGame.add(newWord);
			}
	}
	
	/**
	 * This method takes a random word from the word bank
	 * @return String of random word
	 */
	private String getRandomWord() {
		Random r = new Random();
		int index = r.nextInt(wordOptions.size());
		return wordOptions.get(index);
	}
	
	/**
	 * This method detects if the word reaches the bottom or not
	 * @return
	 */
	public boolean collision() {
		java.util.Iterator<Word> wordIterator = wordsInGame.iterator();
		while(wordIterator.hasNext()) {
			Word current = wordIterator.next();
			if(current.bottomBoard()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * This method moves all words down as time passes
	 */
	private void moveDown() {
		java.util.Iterator<Word> wordIterator = wordsInGame.iterator();
		while(wordIterator.hasNext()) {
			Word thisWord = wordIterator.next();
			thisWord.updateWordField();
		}
		updateUI();
	}

	/**
	 * This method adjusts the difficulty of the game as time progresses
	 */
	private void adjustDifficulty() {
		if (currentTime == 0 || currentTime % (difficulty / 10) == 0) {
			difficulty--;
			int diffMod;
			if (getSelectDiff() == 0) {
				diffMod = 25;
			} else if (getSelectDiff() == 3) {
				diffMod = 15;
			} else {
				diffMod = 5;
			}
			if (difficulty % diffMod == 0) {
				levelCounter++;
				levelCount.setText(""+levelCounter);
			}
			makeNewWord(1);
			
		}
	}
	
    /**
     * This method gets the lists of words from the input file
     * @param inputFile File of words
     * @return ArrayList full of words
     * @throws FileNotFoundException
     */
    public ArrayList<String> getWords(String inputFile) throws FileNotFoundException {
        
    	File f = new File(inputFile);
        Scanner input =  new Scanner(f);
       
        ArrayList<String> wordOptions = new ArrayList<String>();
        while(input.hasNext()) {
            wordOptions.add(input.next());
        }
        input.close();
        return wordOptions;
    }
       
    ///////Speed training mode
    /**
     * This method launches the speed training mode and populates it appropriately
     */
    public void startTraining() {
    	removeAll();
    	Font font1 = new Font("SansSerif", Font.PLAIN, 16);
		JLabel divider = new JLabel("");
		divider.setSize(600, 1);
		divider.setLocation(0, 520);
		divider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));

		JLabel typeHere = new JLabel("Type Here:");
		typeHere.setSize(100,30);
		typeHere.setLocation(100, 535);
		typeHere.setFont(font1);
		typeHere.setForeground(Color.WHITE);
		
		currentWord.setSize(280, 30);
		currentWord.setLocation(200, 535);
		currentWord.setForeground(Color.WHITE);
		currentWord.setBackground(Color.BLACK);
		currentWord.setFont(font1);
		currentWord.setEditable(true);
		currentWord.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		
		JLabel score = new JLabel("Score:");
		score.setSize(60,30);
		score.setLocation(20, 10);
		score.setFont(font1);
		score.setForeground(Color.WHITE);
		score.setHorizontalAlignment(JTextField.CENTER);
		
		pointCount = new JLabel("0");
		pointCount.setSize(60,30);
		pointCount.setLocation(80, 10);
		pointCount.setForeground(Color.WHITE);
		pointCount.setFont(font1);
		
		JLabel run;
		if (runCount != 0) {
			run = new JLabel("Run: " + runCount + "/5");
		} else {
			run = new JLabel("Benchmark Run");
		}
		run.setSize(140,30);
		run.setLocation(230, 10);
		run.setFont(font1);
		run.setForeground(Color.WHITE);
		run.setHorizontalAlignment(JTextField.CENTER);
		
		if (runCount != 0) {
			JLabel targetScore = new JLabel("Target: " + benchmarkScores.get(0));
			targetScore.setSize(140,30);
			targetScore.setLocation(230, 35);
			targetScore.setFont(font1);
			targetScore.setForeground(Color.WHITE);
			targetScore.setHorizontalAlignment(JTextField.CENTER);
			add(targetScore);
		}
		
		JLabel timeCount = new JLabel("Time: " + timeLeft);
		timeCount.setSize(80,30);
		timeCount.setLocation(505, 10);
		timeCount.setForeground(Color.WHITE);
		timeCount.setFont(font1);
		
		add(score);
		add(pointCount);
		add(divider);
		add(typeHere);
		add(run);
		add(timeCount);
		add(currentWord);
		//add(exitBtn);
		wordsInGame = new ArrayList<Word>();
		makeNewWord(0);
		
		TimerTask timeCountdown = new TimerTask() {

			@Override
			public void run() {
				if (timeLeft > 0) {
					timeUsed++;
					timeLeft--;
					timeCount.setText("Time: " + timeLeft);
				} else {
					if (runCount != 0) {
						benchmarkScores.set(2, timeUsed + 7); //adding 5 seconds back (-2 for taking off "3" seconds)
					}
					timer3.cancel();
					setVisible(false);
					endGame(2);
				}
				if (runCount != 0) {
					if (points != 0 && points >= benchmarkScores.get(0)) {
						benchmarkScores.set(2, timeUsed);
						timer3.cancel();
						setVisible(false);
						endGame(2);
					}
				}
			}
			
		};
		
		timer3 = new java.util.Timer();
		timer3.scheduleAtFixedRate(timeCountdown, 0, 1000);

		setVisible(true);
    }
    
    ///////Music Mode
    /**
     * This method launches the music mode and populates it appropriately
     */
    public void startMusic() {
		removeAll();
		
		new JFXPanel();
		//String musicFile = "./src/flight.mp3";
		URL fileUrl = GameGUI.class.getResource("flight.mp3");
		Media sound = new Media(fileUrl.toString());
		player = new MediaPlayer(sound);
		player.play();
		player.setVolume(.5);
		
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 32);
		Font font2 = new Font("SansSerif", Font.PLAIN, 64);
		
		beat = new JLabel("");
		beat.setSize(100,40);
		beat.setLocation(100, 190);
		beat.setForeground(Color.WHITE);
		beat.setFont(font1);
		beat.setHorizontalAlignment(JTextField.CENTER);
		
		statusCheck = new JLabel("");
		statusCheck.setVisible(false);
		
		status = new JLabel("");
		status.setSize(200,40);
		status.setLocation(200, 400);
		status.setForeground(Color.WHITE);
		status.setFont(font1);
		status.setHorizontalAlignment(JTextField.CENTER);
		
		countdown = new JLabel(""+startCount);
		countdown.setSize(200,40);
		countdown.setLocation(200, 150);
		countdown.setForeground(Color.WHITE);
		countdown.setFont(font1);
		countdown.setHorizontalAlignment(JTextField.CENTER);
		
		TimerTask startTask = new TimerTask() {

			@Override
			public void run() {
				if (startCount > 0) {

					countdown.setText(""+startCount);
					startCount--;
				} else if (startCount >= -3) {
					startCount--;
					countdown.setText("Go!");
				} else {
					countdown.setText("");
				}
			}
			
		};
		
		TimerTask checkHit = new TimerTask() {

			@Override
			public void run() {
				if (statusCheck.getText() != "") {
					status.setText("MISSED!");
					strikeCount++;
					strike.setText("Strike:" + strikeCount);
					if (strikeCount == 3) {
						setVisible(false);
						try {
							updateScores("musicHighScores.txt");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						showHighScores();
					}
				} else {
					strikeCount = 0;
					strike.setText("Strike:" + strikeCount);
					Random r = new Random();
					int ran = r.nextInt(3);
					if (ran == 0) {
						status.setText("Nice!");
					} else if (ran == 1) {
						status.setText("Great!");
					} else {
						status.setText("Amazing!");
					}
				}
				beat.setText("Hit!");
				x = 220;

				statusCheck.setText("Hit");
				
			}
			
		};

		TimerTask task2 = new TimerTask() {

			@Override
			public void run() {
				if (x != 180) {
					beat.setLocation(100,x--);
				}
			}
		};
		
		timer2 = new java.util.Timer();
		timer2.scheduleAtFixedRate(startTask, 1000, 950);
		//timer2.scheduleAtFixedRate(task, 5800, 300);
		timer2.scheduleAtFixedRate(task2, 5800, 2);
		timer2.scheduleAtFixedRate(checkHit, 5800, 295);
		
		
		Random r = new Random();
		c = (char) (r.nextInt(26) + 'a');
		nextC = (char) (r.nextInt(26) + 'a');
		thirdC = (char) (r.nextInt(26) + 'a');
		fourthC = (char) (r.nextInt(26) + 'a');
		fifthC = (char) (r.nextInt(26) + 'a');

		
		letter = new JLabel(""+c);
		letter.setSize(80,80);
		letter.setLocation(110, 250);
		letter.setForeground(Color.WHITE);
		letter.setFont(font2);
		letter.setHorizontalAlignment(JTextField.CENTER);
		
		second = new JLabel(""+nextC);
		second.setSize(40,40);
		second.setLocation(220, 280);
		second.setForeground(Color.WHITE);
		second.setFont(font1);
		second.setHorizontalAlignment(JTextField.CENTER);
		
		third = new JLabel(""+thirdC);
		third.setSize(40,40);
		third.setLocation(280, 280);
		third.setForeground(Color.WHITE);
		third.setFont(font1);
		third.setHorizontalAlignment(JTextField.CENTER);
		
		fourth = new JLabel(""+fourthC);
		fourth.setSize(40,40);
		fourth.setLocation(340, 280);
		fourth.setForeground(Color.WHITE);
		fourth.setFont(font1);
		fourth.setHorizontalAlignment(JTextField.CENTER);
		
		fifth = new JLabel(""+fifthC);
		fifth.setSize(40,40);
		fifth.setLocation(400, 280);
		fifth.setForeground(Color.WHITE);
		fifth.setFont(font1);
		fifth.setHorizontalAlignment(JTextField.CENTER);
		
		score = new JLabel("Score: "+ scoreCount);
		score.setSize(200,40);
		score.setLocation(50, 80);
		score.setForeground(Color.WHITE);
		score.setFont(font1);
		score.setHorizontalAlignment(JTextField.CENTER);
		
		strike = new JLabel("Strike: "+ strikeCount);
		strike.setSize(200,40);
		strike.setLocation(350, 80);
		strike.setForeground(Color.WHITE);
		strike.setFont(font1);
		strike.setHorizontalAlignment(JTextField.CENTER);
		
		JLabel divider = new JLabel("");
		divider.setSize(100, 1);
		divider.setLocation(100, 330);
		divider.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		JLabel beatStrike = new JLabel("");
		beatStrike.setSize(50, 1);
		beatStrike.setLocation(125, 253);
		beatStrike.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE));
		
		add(countdown);
		add(beat);
		add(letter);
		add(second);
		add(third);
		add(fourth);
		add(fifth);
		add(status);
		add(statusCheck);
		add(score);
		add(strike);
		add(divider);
		add(beatStrike);
		setVisible(true);
		setFocusable(true);
		requestFocus();
		this.addKeyListener(this);
	}

	
	/**
	 * Setter method for difficulty selected
	 * @param i
	 */
	public void setSelectDiff(int i) {
		this.selectDiff = i;
	}
	
	/**
	 * Getter method for difficulty selected
	 * @return difficulty selected
	 */
	public int getSelectDiff() {
		return selectDiff;
	}
	
	/**
	 * This method displays the top five highest scores for the mode being played
	 */
	public void showHighScores() {
		removeAll();
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 32);
		
		if (modeSelect == 0) {
			scoreOne = new JLabel("1: " + classicHighScores.get(0));
			scoreTwo = new JLabel("2: " + classicHighScores.get(1));
			scoreThree = new JLabel("3: " + classicHighScores.get(2));
			scoreFour = new JLabel("4: " + classicHighScores.get(3));
			scoreFive = new JLabel("5: " + classicHighScores.get(4));
		} else if (modeSelect == 3) {
			scoreOne = new JLabel("1: " + musicHighScores.get(0));
			scoreTwo = new JLabel("2: " + musicHighScores.get(1));
			scoreThree = new JLabel("3: " + musicHighScores.get(2));
			scoreFour = new JLabel("4: " + musicHighScores.get(3));
			scoreFive = new JLabel("5: " + musicHighScores.get(4));
		}
		
		JLabel scoreTitle = new JLabel("High Scores:");
		scoreTitle.setSize(200,80);
		scoreTitle.setLocation(200, 75);
		scoreTitle.setFont(font1);
		scoreTitle.setForeground(Color.WHITE);	
		
		scoreOne.setSize(200,30);
		scoreOne.setLocation(250, 150);
		scoreOne.setFont(font1);
		scoreOne.setForeground(Color.WHITE);
			
		scoreTwo.setSize(200,30);
		scoreTwo.setLocation(250, 200);
		scoreTwo.setFont(font1);
		scoreTwo.setForeground(Color.WHITE);
				
		scoreThree.setSize(200,30);
		scoreThree.setLocation(250, 250);
		scoreThree.setFont(font1);
		scoreThree.setForeground(Color.WHITE);	
		
		scoreFour.setSize(200,30);
		scoreFour.setLocation(250, 300);
		scoreFour.setFont(font1);
		scoreFour.setForeground(Color.WHITE);
				
		scoreFive.setSize(200,30);
		scoreFive.setLocation(250, 350);
		scoreFive.setFont(font1);
		scoreFive.setForeground(Color.WHITE);
		
		JButton nextBtn = new JButton("Continue");
		nextBtn.setSize(250,50);
		nextBtn.setLocation(175, 400);
		nextBtn.setFont(font1);
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if (modeSelect == 0) {
					endGame(0);
				} else if (modeSelect == 3) {
					endGame(1);
				}
		    }          
		});
		
		add(scoreTitle);
		add(scoreOne);
		add(scoreTwo);
		add(scoreThree);
		add(scoreFour);
		add(scoreFive);
		add(nextBtn);
		setVisible(true);
	}
	
    /**
     * This method gets scores from input files
     * @param inputFile The file containing scores
     * @return ArrayList full of scores
     * @throws FileNotFoundException
     */
    public ArrayList<Integer> getScores(String inputFile) throws FileNotFoundException {
        
    	File f = new File(inputFile);
        Scanner input =  new Scanner(f);
       
        ArrayList<Integer> highScores = new ArrayList<Integer>();
        while(input.hasNextInt()) {
        	highScores.add(input.nextInt());
        }
        input.close();
        if (inputFile != "trainingBenchmarks.txt") {
        	Collections.sort(highScores, Collections.reverseOrder());
        }
        return highScores;
    }
    
    /**
     * This method updates the given file with new scores
     * @param inputFile File of scores
     * @throws IOException
     */
    public void updateScores(String inputFile) throws IOException  {
    	if (modeSelect == 0) {
	    	if (points > classicHighScores.get(4)) { //last score is lowest score
	    		classicHighScores.set(4, points);
	    		Collections.sort(classicHighScores, Collections.reverseOrder()); //only necessary for when original list has fewer than 5 scores
	        	FileWriter fw = new FileWriter(inputFile);
	        	PrintWriter pw = new PrintWriter(fw);
	        	for (int i = 0; i < classicHighScores.size(); i++) {
	        		pw.println(classicHighScores.get(i));
	        	};
	            pw.close();
	    	}
    	} else if (modeSelect == 2) {
    		if (runCount == 0) {
    			benchmarkScores.set(0, points);
    		}
    		if (runCount != 5) {
    			runCount++;
    			benchmarkScores.set(1, runCount);
    		} else {
				runCount = 0;
				benchmarkScores.set(1, runCount);
    		}
        	FileWriter fw = new FileWriter(inputFile);
        	PrintWriter pw = new PrintWriter(fw);
        	for (int i = 0; i < benchmarkScores.size(); i++) {
        		pw.println(benchmarkScores.get(i));
        	};
            pw.close();
    	} else if (modeSelect == 3) {
	    	if (scoreCount > musicHighScores.get(4)) { //last score is lowest score
	    		musicHighScores.set(4, scoreCount);
	    		Collections.sort(musicHighScores, Collections.reverseOrder()); //only necessary for when original list has fewer than 5 scores
	        	FileWriter fw = new FileWriter(inputFile);
	        	PrintWriter pw = new PrintWriter(fw);
	        	for (int i = 0; i < musicHighScores.size(); i++) {
	        		pw.println(musicHighScores.get(i));
	        	};
	            pw.close();
	    	}
    	}
    	
    }
	

	/**
	 * This method moves down words and ends game if collision is detected
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		currentTime++;
		moveDown();
		if(collision() && modeSelect == 0) {
			removeAll();
			endGame(0);
			setVisible(false);
			try {
				updateScores("classicHighScores.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			showHighScores();
		}
		adjustDifficulty();
	}
	
	/**
	 * Not Used 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method is for the music mode and detects correct key presses
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyChar() == c) {
			Random r = new Random();
			c = nextC;
			nextC = thirdC;
			thirdC = fourthC;
			fourthC = fifthC;
			fifthC = (char) (r.nextInt(26) + 'a');
			letter.setText(""+c);
			second.setText(""+nextC);
			third.setText(""+thirdC);
			fourth.setText(""+fourthC);
			fifth.setText(""+fifthC);
			statusCheck.setText("");
			scoreCount++;
			score.setText("Score: " + scoreCount);
		}

		
	}

	/**
	 * Not Used
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
