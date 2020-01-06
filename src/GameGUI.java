import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

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
	private Timer time;
	private int currentTime;
	private int difficulty;
	private int levelCounter = 1;
	private int selectDiff;
	
	public GameGUI() throws FileNotFoundException {
		setLayout(null);
		setBackground(Color.BLACK);
		currentWord = new JTextField("");
		titleScreen();
	}
	
	public void titleScreen() {
		removeAll();
		JButton startBtn = new JButton("Start");
		startBtn.setSize(100,50);
		startBtn.setLocation(250, 275);
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
		
		Font font1 = new Font("SansSerif", Font.PLAIN, 32);
		Font font2 = new Font("SansSerif", Font.PLAIN, 20);
		
		classicBtn.setSize(100,50);
		classicBtn.setLocation(175, 250);
		classicBtn.setFont(font2);
		
		endlessBtn.setSize(100,50);
		endlessBtn.setLocation(325, 250);
		endlessBtn.setFont(font2);
		
		speedBtn.setSize(300,50);
		speedBtn.setLocation(150, 325);
		speedBtn.setFont(font1);
		
		JLabel mode = new JLabel("Choose Mode:");
		mode.setSize(300,30);
		mode.setLocation(150, 200);
		mode.setForeground(Color.WHITE);
		mode.setFont(font1);
		mode.setHorizontalAlignment(JTextField.CENTER);
		
		add(mode);
		add(classicBtn);	
		add(endlessBtn);
		add(speedBtn);
		setVisible(true);
		classicBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				difficultySelect();
		    }          
		});
		//need to add functionality for other 2
		
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
		//typeHere.setBackground(Color.WHITE);
		
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

		
		add(score);
		add(pointCount);
		add(divider);
		add(typeHere);
		add(currentWord);
		add(diffDisp);
		add(level);
		add(levelCount);
		setVisible(true);
		time = new Timer(100, this);
		time.setInitialDelay(0);

		
		points = 0;
		currentTime = 0;
		difficulty = 210;
		
		time.start();
		
	}
	
	private void endGame() {
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
		
		
		time.stop();
	}
	
	public void setSelectDiff(int i) {
		this.selectDiff = i;
	}
	
	public int getSelectDiff() {
		return selectDiff;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
