package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.IView;
import model.JottoModel;

public class IntroView extends JPanel {
	
	private JottoModel model;	
	private JLabel welcome = new JLabel("Welcome to Jotto");
	private JLabel welcome2 = new JLabel("Select Difficulty");
	
	private JRadioButton easy = new JRadioButton("EASY");
	private JRadioButton medium = new JRadioButton("MEDIUM");
	private JRadioButton hard = new JRadioButton("HARD");
	private JRadioButton random = new JRadioButton("RANDOM");
	
	ImageIcon startIcon = new ImageIcon("start.jpg");
	ImageIcon exitIcon = new ImageIcon("exit.jpg");
	private JButton start = new JButton(startIcon);
	private JButton exit = new JButton(exitIcon);
	
	public IntroView(JottoModel aModel) {
		super();
		this.model = aModel;
		this.layoutView();
		this.registerControllers();
		
		this.model.addView(new IView() {
			public void updateView() {
				start.setEnabled(true);
				exit.setEnabled(true);
			}
		});			
	}
	

	private void layoutView() {
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);

		start.setBorder(null);
		exit.setBorder(null);
		
		easy.setBackground(Color.GREEN);
		medium.setBackground(Color.YELLOW);
		hard.setBackground(Color.RED);
		random.setBackground(Color.GRAY);
		random.setSelected(true);
		
		
		welcome.setAlignmentX(CENTER_ALIGNMENT);
		welcome.setFont(new Font("SansSerif", Font.BOLD, 25));
		welcome2.setAlignmentX(CENTER_ALIGNMENT);
		
		GridBagConstraints bagConst = new GridBagConstraints();
		bagConst.gridx = GridBagConstraints.CENTER;
		bagConst.gridy = GridBagConstraints.RELATIVE;
	    	bagConst.weighty = 1.0;
		bagConst.fill = GridBagConstraints.VERTICAL;

		this.add(welcome, bagConst);
		this.add(welcome2, bagConst);
		this.add(Box.createVerticalGlue());
		
		// setting difficulty
		ButtonGroup bg = new ButtonGroup();
		bg.add(easy);
		bg.add(medium);
		bg.add(hard);
		bg.add(random);
		this.add(this.groupComponents(easy, medium, hard, random), bagConst);
		
		this.add(this.groupComponents(start, exit), bagConst);
	}

	private void registerControllers() {
		this.start.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					if(random.isSelected()){
						model.setDiff(3);
					}
					model.selectWord(model.getDiff());
					model.startClicked = true;
				}});
		
		this.exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				//System.out.println("actionPerformed");
				System.exit(0);
			}});
		this.easy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				model.setDiff(0);
			}});
		this.medium.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				model.setDiff(1);
			}});
		this.hard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				model.setDiff(2);
			}});
		this.random.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				model.setDiff(3);
			}});
		
	}
	
	private Box groupComponents(JButton start, JButton exit) {
		Box group = Box.createHorizontalBox();
		group.add(start);
		group.add(exit);

		Dimension d = start.getPreferredSize();

		d.width = Math.max(start.getPreferredSize().width,
				exit.getPreferredSize().width);

		start.setPreferredSize(d);
		exit.setPreferredSize(d);
		
		return group;

	}

	private Box groupComponents(JRadioButton jb1, JRadioButton jb2,JRadioButton jb3,JRadioButton jb4) {
		Box group = Box.createHorizontalBox();
		group.add(jb1);
		group.add(jb2);
		group.add(jb3);
		group.add(jb4);

		Dimension d = start.getPreferredSize();

		d.width = Math.max(jb1.getPreferredSize().width,
				jb2.getPreferredSize().width);

		return group;

	}
}
