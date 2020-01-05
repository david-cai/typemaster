import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class Runner extends JFrame {

	private GameGUI game;
	
	public Runner() throws FileNotFoundException {
		setSize(600,600);

		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game = new GameGUI();
		setContentPane(game);
		setVisible(true);
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		new Runner();
	}

}
