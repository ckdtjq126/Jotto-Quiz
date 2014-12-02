package view;

import model.IView;
import model.JottoModel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.text.NumberFormat;


public class TextView extends JPanel {
	
	private JottoModel model;	
	
	private JLabel livesTitle = new JLabel("Lives Left: ");
	private JLabel lives = new JLabel();
	private JLabel blank2 = new JLabel(" ");
	private JLabel letterTFTitle = new JLabel("Guess the Word");
	
	private JTextField letterTF = new JTextField(5);	
	
	// To format numbers consistently in the text fields.
	private static final NumberFormat formatter = NumberFormat
			.getNumberInstance();

	public TextView(JottoModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		
		// There's no need to do this for such a simple view, but for a complex
		// view you might have several ViewUpdate objects registered with
		// the model.
		
		this.model.addView(new IView() {
			public void updateView() {
				letterTF.setText(model.getwordTyped());
				lives.setText(formatter.format(model.getLives()));
			}
		});	
		
	}
	

	private void layoutView() {

	this.setLayout(new GridBagLayout());

	livesTitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
	lives.setFont(new Font("SansSerif", Font.PLAIN, 16));
	lives.setForeground(Color.RED);

	letterTFTitle.setFont(new Font("SansSerif", Font.BOLD, 20));
	letterTF.setFont(new Font("SansSerif", Font.BOLD, 20));

	// create a constraints object
	GridBagConstraints gc = new GridBagConstraints();
	
	gc.fill = GridBagConstraints.HORIZONTAL;
	gc.gridy = 0;
	
	this.add(this.groupComponents(livesTitle, lives), gc);
	gc.gridx = 0;
	gc.gridy = GridBagConstraints.RELATIVE;
	this.add(blank2,gc);
	this.add(this.letterTFTitle, gc);
	this.add(this.letterTF, gc);
	}

	private void registerControllers() {
		CombinedTextController tc = new CombinedTextController();
		this.letterTF.addActionListener(tc);
		this.letterTF.addFocusListener(tc);
	}

	private Box groupComponents(JLabel a, JLabel b) {
		Box group = Box.createHorizontalBox();
		group.add(a);
		group.add(b);

		Dimension d = a.getPreferredSize();

		d.width = Math.max(a.getPreferredSize().width,
				b.getPreferredSize().width);

		a.setPreferredSize(d);
		b.setPreferredSize(d);
		
		return group;

	}

	
	
	private class CombinedTextController implements ActionListener,	FocusListener {
		public void actionPerformed(ActionEvent evt) {
			boolean allChar = true;
			//System.out.println("actionPerformed");
			String letter = letterTF.getText();
			letter = letter.toUpperCase();
			if(letter.equals("HINTS")){
				if(model.hintAvailable()){
					int i = (int)((Math.random() * 10) % 5);
					model.setHintAvailable(false);
					model.setwordTyped(letter);
					JOptionPane.showMessageDialog(null, i+1+"-th character is "+model.getTarget().charAt(i));
				}
				else{
					JOptionPane.showMessageDialog(null, "You already used hint!");
				}
			}
			else if(letter.equals("+LIFE")){
				if(model.plusAvailable() && model.getDiff() != 2){
					model.setPlusLife(false);
					model.setLives(model.getLives() + 5);
					model.setwordTyped(letter);
					JOptionPane.showMessageDialog(null, "You just used +LIFE!");
				}
				else if(model.getDiff() != 2){
					JOptionPane.showMessageDialog(null, "You already used +LIFE!");
				}
				else{
					JOptionPane.showMessageDialog(null, "You cannot use +LIFE in Hard mode");
				}
			}
			else if(letter.length() == 5){
				// checks if char is included
				for(int i = 0; i < 5; i++){
					if(!Character.isLetter(letter.charAt(i))){
						JOptionPane.showMessageDialog(null, "Non-char is included in the word");
						allChar = false;
						break;
					}
				}
				if(allChar && model.checkCorrect(letter)){
					JOptionPane.showMessageDialog(null, "You got it right! \nTarget Word is "+model.getTarget()+"\nPress OK to exit");
					System.exit(0);
				}
				else if(allChar){
					model.lostLife();
					model.setwordTyped(letter);
					model.setResult(letter);
					model.addTried(letter);
				}
				else{
					model.setwordTyped("");
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Type The Word of Length = 5");
				model.setwordTyped("");
			}
			
			if(model.getLives() == 0){
				JOptionPane.showMessageDialog(null, "You lost all lives. Game Over!");
				System.exit(0);
				/*
				String []choices = {"Retry", "Quit"};
			int selected = JOptionPane.showOptionDialog(null, "Do you want to try one more time?", "Game Over",
					  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
					if(selected == JOptionPane.CLOSED_OPTION){
					 JOptionPane.showMessageDialog(null, "Exiting...");
					 System.exit(0);
					}
					else if(selected == JOptionPane.YES_OPTION){
						model.retry();
					}
					else
						System.exit(0);
			*/
			}
		}

		public void focusGained(FocusEvent evt) {
		}

		public void focusLost(FocusEvent evt) {
		}
	}
	
	
}

