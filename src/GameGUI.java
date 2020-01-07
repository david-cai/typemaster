import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.Timer;

import java.util.TimerTask;
//import java.util.Timer;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class GameGUI extends JPanel implements KeyListener, ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField currentWord;
	JLabel pointCount;
	JLabel levelCount;
	private JTextField gameOverField;
	private int points;
	private javax.swing.Timer time;
	private java.util.Timer timer2;
	private int currentTime;
	private int difficulty;
	private int levelCounter = 1;
	private int selectDiff;
	private ArrayList<Word> wordsInGame;
	private ArrayList<String> wordOptions;
	private int modeSelect;
	
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
	
	public GameGUI() throws FileNotFoundException {
		setLayout(null);
		setBackground(Color.BLACK);
		wordOptions = getWords("medium_length.txt");
		currentWord = new JTextField("");
		currentWord.addActionListener(new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				enterUserWord();
			}
			
		});
		titleScreen();
	}
	
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
		
		musicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				startMusic();
		    }          
		});
		
	}
	
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
	
	private void endGame(int game) {
		removeAll();
		
		Font font1 = new Font("SansSerif", Font.BOLD, 40);
		gameOverField = new JTextField("Game Over");
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
			player.stop();
		}
	}
	
	public void enterUserWord() {
		String userInput = currentWord.getText();
		currentWord.setText("");
		if(wordIsInGame(userInput)) {
			points = points + userInput.length();
			pointCount.setText(""+points);
			removeWord(userInput);
			updateUI();
		}
	}
	
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
	

	private void makeNewWord() {
			String randomWord = getRandomWord();
			Random r = new Random();
			int randNum = r.nextInt(5) + levelCounter + getSelectDiff() + 1;
			Word newWord = new Word(randomWord, randNum, this);
			wordsInGame.add(newWord);
	}
	
	private String getRandomWord() {
		Random r = new Random();
		int index = r.nextInt(wordOptions.size());
		return wordOptions.get(index);
	}
	

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


	private void moveDown() {
		java.util.Iterator<Word> wordIterator = wordsInGame.iterator();
		while(wordIterator.hasNext()) {
			Word thisWord = wordIterator.next();
			thisWord.updateWordField();
		}
		updateUI();
	}

	private void adjustDifficulty() {

		if (currentTime == 0 || currentTime % (difficulty / 10) == 0) {
			difficulty--;
			int diffMod;
			if (getSelectDiff() == 0) {
				diffMod = 25;
			} else if (getSelectDiff() == 1) {
				diffMod = 20;
			} else {
				diffMod = 15;
			}
			if (difficulty % diffMod == 0) {
				levelCounter++;
				levelCount.setText(""+levelCounter);
			}
			makeNewWord();
			
		}
	}
	
    public static ArrayList<String> getWords(String inputFile) throws FileNotFoundException {
        
    	File f = new File(inputFile);
        Scanner input =  new Scanner(f);
       
        ArrayList<String> wordOptions = new ArrayList<String>();
        while(input.hasNext()) {
            wordOptions.add(input.next());
        }
        input.close();
        return wordOptions;
    }
    
    ///////Music Mode (maybe separate to diff class)
    
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
					scoreCount--;
					strikeCount++;
					strike.setText("Strike:" + strikeCount);
					score.setText("Score: " + scoreCount);
					if (strikeCount == 5) {
						//setVisible(false);
						//endGame(1); temporarily off for testing
					}
				} else {
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

	
	public void setSelectDiff(int i) {
		this.selectDiff = i;
	}
	
	public int getSelectDiff() {
		return selectDiff;
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		currentTime++;
		moveDown();
		if(collision() && modeSelect == 0) {
			endGame(0);
		}
		adjustDifficulty();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
