import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGUI extends JPanel implements KeyListener, ActionListener{
	
	private JTextField gameOverField;
	
	public GameGUI() throws FileNotFoundException {
		setLayout(null);
		setBackground(Color.BLACK);
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
				//setSelectDiff(0);
				//startNewGame();
			}        
		});
		mediumBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setSelectDiff(3);
				//startNewGame();
			}        
		});
		hardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//setSelectDiff(5);
				//startNewGame();
			}        
		});
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
		
		
		//time.stop();
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
