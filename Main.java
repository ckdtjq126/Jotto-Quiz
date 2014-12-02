
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import model.JottoModel;
import view.*;
import java.awt.GridLayout;


public class Main{
	
	public static void main(String[] args){
		final int GAME_WIDTH = 300;
		final int GAME_HEIGHT = 300;
		
		model.JottoModel model = new JottoModel();
		IntroView vIntro = new IntroView(model);
		TextView vText = new TextView(model);
		ProgressView vProgress = new ProgressView(model);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
		// using these points will bring the game
		// center of the window/
		int centerWidth = screenSize.width/2 -  GAME_WIDTH/2;
		int centerHeight = screenSize.height/2 - GAME_HEIGHT/2;
		
		JFrame intro = new JFrame("Jotto Intro");
		JFrame mainGame = new JFrame("Jotto Main");

		
		// INTRO
		intro.getContentPane().setLayout(new GridLayout(1,2));
		intro.getContentPane().add(vIntro);

		intro.pack();
		intro.setSize(GAME_WIDTH, GAME_HEIGHT);
		intro.setResizable(false);
		intro.setLocation(centerWidth, centerHeight);
		intro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intro.setVisible(true);

		while(true){
			if(model.startClicked){
				intro.dispose();
				break;
			}			
		}

		// MAINGAME
		mainGame.getContentPane().setLayout(new GridLayout(2,1));
		mainGame.getContentPane().add(vText);
		mainGame.getContentPane().add(vProgress);
		
		mainGame.pack();
		mainGame.setSize(GAME_WIDTH, GAME_HEIGHT);
		mainGame.setResizable(false);
		mainGame.setLocation(centerWidth, centerHeight);
		mainGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainGame.setVisible(true);
	}
}
