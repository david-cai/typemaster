import java.util.Random;

import javax.swing.JTextField;

public class Word {

	private String chosenWord;
	private JTextField wordField;
	private int fallSpeed;
	private int xVal;
	private int yVal;
	
	public Word(String chosenWord, int fallSpeed) {
		this.chosenWord = chosenWord;
		this.fallSpeed = fallSpeed;
		Random r = new Random();
		xVal = r.nextInt(500);
		yVal = 50;
	}
	
}
