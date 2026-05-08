import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{
	private static final int WIDTH =1800;
	private static final int HEIGHT=1600;
	
	public Main () {
		super("Cavern of Hostility");
		setSize(WIDTH, HEIGHT);
		Game play = new Game();
		((Component) play).setFocusable(true);
		
		//Color RoyalBlue = new Color(22,13,193);
		
		
		setBackground(Color.BLACK);
		
		
		getContentPane().add(play);
		
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				play.writeToFile();
				System.exit(0);
			}
			public void windowOpened(WindowEvent e) {
				play.createFile();
				play.readFile();
			}
			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});

	}
	

	public static void main(String[] args) {
		Main run = new Main();
		

	}


}