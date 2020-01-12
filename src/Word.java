import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * @author David Cai
 * The Word class defines the Word object that the user must type for Classic/Endless/Training modes
 */
public class Word extends JFrame {

	private String chosenWord;
	protected JTextField wordField;
	private int fallSpeed;
	private int xVal;
	private int yVal;
	private GameGUI frame;
	
	public Word(String chosenWord, int fallSpeed, GameGUI frame) {
		this.chosenWord = chosenWord;
		this.fallSpeed = fallSpeed;
		this.frame = frame;
		Random r = new Random();
		xVal = r.nextInt(500);
		yVal = 50;
		createWordField();
	}
	
	/**
	 * This method updates the word as it falls to different colors
	 */
	public void updateWordField() {
		yVal = yVal + fallSpeed;
		wordField.setLocation(xVal, yVal);
		if(yVal > 330) {
			wordField.setForeground(Color.white);
			wordField.setBackground(Color.red);
		} else if(yVal > 185) {
			wordField.setBackground(Color.yellow);
		}
	}
	
	/**
	 * This class creates the words for each mode
	 */
	public void createWordField() {
		Font font1 = new Font("SansSerif", Font.PLAIN, 16);
		Font font2 = new Font("SansSerif", Font.PLAIN, 48);
		wordField = new JTextField(chosenWord);
		if (fallSpeed == 0) { //Training mode
			wordField.setLocation(125, 230);
			wordField.setSize(350, 80);
			wordField.setFont(font2);
		} else { //Classic and endless modes
			wordField.setLocation(xVal, yVal);
			wordField.setBackground(Color.GREEN);
			wordField.setSize(7 * chosenWord.length() + 50, 50);
			wordField.setFont(font1);
		}
		
		wordField.setHorizontalAlignment(JTextField.CENTER);
		frame.add(wordField);
	}
	
	/**
	 * This class checks if the word is at the bottom of the board
	 * @return Boolean isBottom
	 */
	public boolean bottomBoard() {
		if(yVal >= 477) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method checks word equality
	 */
	@Override
	public boolean equals(Object diffWord) {
		if (diffWord instanceof String) {
			String compWord = (String) diffWord;
			return this.chosenWord.equals(compWord);
		} else {
			return false;
		}
	}
}
