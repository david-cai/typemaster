import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;

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
	
	public void createWordField() {
		Font font1 = new Font("SansSerif", Font.PLAIN, 16);
		wordField = new JTextField(chosenWord);
		wordField.setLocation(xVal, yVal);
		wordField.setBackground(Color.GREEN);
		wordField.setSize(7 * chosenWord.length() + 50, 50);
		wordField.setFont(font1);
		wordField.setHorizontalAlignment(JTextField.CENTER);
		frame.add(wordField);
	}
	
	public boolean bottomBoard() {
		if(yVal >= 477) {
			return true;
		} else {
			return false;
		}
	}

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
