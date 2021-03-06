package GUIs;

import code.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainGUI  {

	private static String clue;
	private static boolean spymasterTurn = true;
	private static ArrayList<JButton> buttonList = new ArrayList<>();

	@SuppressWarnings("serial")
	public static void run() {

		/*
		 * makes GUI less ugly
		 */
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		}

		/*
		 * sets up game through Board's code and sets up JFrame things
		 */

		Board board = new Board();
		board.setTwoPlayerGame(true);
		board.createBoard();
		board.gameStart("src/GameWords.txt");
		JFrame frame = new JFrame("CODENAMES");
		frame.setLayout(new BorderLayout());
		JPanel gameWindow = new JPanel();
		JPanel buttonPlace = new JPanel();

		JMenu clueShower = new JMenu("");
		JMenu countShower = new JMenu("");
		JMenu turn = new JMenu();

		JButton submitClue = new JButton("Submit Clue");
		JButton spymaster = new JButton("Back to Spymaster");
		buttonPlace.add(submitClue);
		buttonPlace.add(spymaster);

		/*
		 * code to make a new game
		 */

		Action newGameAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setTwoPlayerGame(true);
				board.createBoard();
				board.gameStart("src/GameWords.txt");
				gameWindow.removeAll();
				buttonList = new ArrayList<>();
				clueShower.setText("");
				countShower.setText("");
				turn.setText("Current Turn: RED");
				turn.setForeground(Color.red);
				for(int i = 0; i < 25; i++) {
					JButton buttonToAdd = new JButton();
					buttonList.add(buttonToAdd);
					if(board.getBoard()[i].isNotRevealed()) {
						buttonToAdd.setText(board.getBoard()[i].getCodename());
					}
					else {
						buttonToAdd.setText(" ");
					}
					buttonToAdd.setFocusable(false);
					buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
					if(board.getBoard()[i].getPerson().equals("red")) {
						buttonToAdd.setBackground(Color.red);
					}
					if(board.getBoard()[i].getPerson().equals("blue")) {
						buttonToAdd.setBackground(Color.cyan);
					}
					if(board.getBoard()[i].getPerson().equals("green")) {
						buttonToAdd.setBackground(Color.green);
					}
					if(board.getBoard()[i].getPerson().equals("innocent")) {
						buttonToAdd.setBackground(Color.yellow);
					}
					if(board.getBoard()[i].getPerson().equals("assassin")) {
						buttonToAdd.setBackground(Color.black);
						buttonToAdd.setForeground(Color.white);
					}
					gameWindow.add(buttonToAdd);
				}
				gameWindow.validate();
				spymasterTurn = true;
			}
		};
		newGameAction.putValue(Action.NAME, "New Two Team Game");

		JMenuItem newGame = new JMenuItem(newGameAction);
		
		Action newGameAction2 = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				board.setTwoPlayerGame(false);
				board.createBoard();
				board.gameStart("src/GameWords.txt");
				gameWindow.removeAll();
				buttonList = new ArrayList<>();
				clueShower.setText("");
				countShower.setText("");
				turn.setText("Current Turn: RED");
				turn.setForeground(Color.red);
				for(int i = 0; i < 25; i++) {
					JButton buttonToAdd = new JButton();
					buttonList.add(buttonToAdd);
					if(board.getBoard()[i].isNotRevealed()) {
						buttonToAdd.setText(board.getBoard()[i].getCodename());
					}
					else {
						buttonToAdd.setText(" ");
					}
					buttonToAdd.setFocusable(false);
					buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
					if(board.getBoard()[i].getPerson().equals("red")) {
						buttonToAdd.setBackground(Color.red);
					}
					if(board.getBoard()[i].getPerson().equals("blue")) {
						buttonToAdd.setBackground(Color.cyan);
					}
					if(board.getBoard()[i].getPerson().equals("green")) {
						buttonToAdd.setBackground(Color.green);
					}
					if(board.getBoard()[i].getPerson().equals("innocent")) {
						buttonToAdd.setBackground(Color.yellow);
					}
					if(board.getBoard()[i].getPerson().equals("assassin")) {
						buttonToAdd.setBackground(Color.black);
						buttonToAdd.setForeground(Color.white);
					}
					gameWindow.add(buttonToAdd);
				}
				gameWindow.validate();
				spymasterTurn = true;
			}
		};
		newGameAction2.putValue(Action.NAME, "New Three Team Game");

		JMenuItem newGame2 = new JMenuItem(newGameAction2);

		/*
		 * allows the spymaster to input a clue and count (also has secret memes)
		 */

		submitClue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(spymasterTurn) {
					Object[] possibilities = null;
					clue = (String)JOptionPane.showInputDialog(
							frame,
							"Type your clue below:\n",
							"Enter Clue",
							JOptionPane.PLAIN_MESSAGE,
							null,
							possibilities,
							"");
					while(board.checkIllegalClue(clue)) {
						clue = (String)JOptionPane.showInputDialog(
								frame,
								"Illegal Clue, please choose another:\n",
								"ILLEGAL CLUE",
								JOptionPane.PLAIN_MESSAGE,
								null,
								possibilities,
								"");
					}

					/*
					 * secret passwords for 4 easter egg memes
					 */

					if(clue.equals("HERTZ")) {   			
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic1 = new JLabel(); 
						ImageIcon a = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/hertz memes.png"));
							a.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic1.setIcon(a);

						mainPanel.add(pic1);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);

					}
					if(clue.equals("HAWAIIAN")) { 

						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic2 = new JLabel(); 
						ImageIcon b = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/Steal his look.png"));
							b.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic2.setIcon(b);

						mainPanel.add(pic2);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);
					}
					if(clue.equals("NEIGHBOR")) { 
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic3 = new JLabel(); 
						ImageIcon c = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/Vapowave.png"));
							c.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic3.setIcon(c);

						mainPanel.add(pic3);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);
					}
					if(clue.equals("TWITTER")) { 
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic4 = new JLabel(); 
						ImageIcon d = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/if you dont love me.png"));
							d.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic4.setIcon(d);

						mainPanel.add(pic4);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);
					}
					
					if(clue.equals("GOTG")) {   			
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic1 = new JLabel(); 
						ImageIcon a = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/GotG CSE meme smol.png"));
							a.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic1.setIcon(a);

						mainPanel.add(pic1);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);

					}
					
					if(clue.equals("JAVA")) {   			
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic1 = new JLabel(); 
						ImageIcon a = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/this looks hard.png"));
							a.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic1.setIcon(a);

						mainPanel.add(pic1);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);

					}
					
					if(clue.equals("HEXAGON")) {   			
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic1 = new JLabel(); 
						ImageIcon a = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/hexagon.png"));
							a.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic1.setIcon(a);

						mainPanel.add(pic1);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);

					}
					
					if(clue.equals("FIST")) {   			
						JFrame memeWindow = new JFrame("A Meme that Hertz");
						memeWindow.setLayout(new BorderLayout());
						JPanel mainPanel = new JPanel(); 
						JLabel pic1 = new JLabel(); 
						ImageIcon a = new ImageIcon(); 
						try {
							BufferedImage img = ImageIO.read(new File("memes/fist.png"));
							a.setImage(img);
						} catch (IOException e1) {
							e1.printStackTrace();
						}

						pic1.setIcon(a);

						mainPanel.add(pic1);
						memeWindow.add(mainPanel);

						memeWindow.setVisible(true);
						memeWindow.pack();
						Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
						memeWindow.setLocation(dim.width/2-memeWindow.getSize().width/2, dim.height/2-memeWindow.getSize().height/2);

					}


					clue = clue.toUpperCase();
					clueShower.setText("Clue: " + clue);

					/*
					 * makes a pop up window to select a count for the clue
					 */
					Object[] possibilities2 = {"1","2","3","4","5","6","7","8","9"};
					if(board.isTwoPlayerGame()) {
					if(board.getTurnCount() == 1) {
						possibilities2[8] = null;
					}
					}
					else {
							possibilities2[6] = null;
							possibilities2[7] = null;
							possibilities2[8] = null;
							if(!(board.getTurnCount() == 0)) {
								possibilities2[5] = null;
							}
						}
					
					clue = (String)JOptionPane.showInputDialog(
							frame,
							"What is the count?\n",
							"Select Count",
							JOptionPane.PLAIN_MESSAGE,
							null,
							possibilities2,
							"");
					board.setCount(Integer.parseInt(clue));
					countShower.setText("Count: " + clue);
					spymasterTurn = false;
					gameWindow.removeAll();

					/*
					 * adds new buttons to show for the guessers
					 */

					for(int i = 0; i < 25; i++) {
						JButton buttonToAdd = new JButton();
						if(board.getBoard()[i].isNotRevealed()) {
							buttonToAdd.setText(board.getBoard()[i].getCodename());
						}
						else {
							buttonToAdd.setText(" ");
						}
						buttonToAdd.setFocusable(false);
						buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
						buttonToAdd.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e){
								if(!spymasterTurn) {
									for(int i = 0; i < 25; i++) {
										if(buttonToAdd.getText().equals(board.getBoard()[i].getCodename())) {
											if(board.getBoard()[i].getPerson().equals("assassin")) {
												String whichTeamWon = board.whichTeamWonAssassin();
												Object [] options = {"Start New Game", "Quit"};
												if(board.isTwoPlayerGame()) {
												if(whichTeamWon.equals("red")) {
													int input = JOptionPane.showOptionDialog(frame,
															"<html>The red team won!<br> What would you like to do?</html>",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymasterTurn = true;
													}
													else {
														System.exit(0);
													}
												}								    					
												if(whichTeamWon.equals("blue")) {
													int input = JOptionPane.showOptionDialog(frame,
															"<html>The blue team won!<br> What would you like to do?</html>",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymasterTurn = true;
													}						    			
													else {
														System.exit(0);
													}
												}
											}
											else {
												if(whichTeamWon.equals("no one0")) {
													JOptionPane.showMessageDialog(frame,
															"The red team is out! The blue and green teams may continue to play.",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.PLAIN_MESSAGE);
												}
												if(whichTeamWon.equals("no one1")) {
													JOptionPane.showMessageDialog(frame,
															"The blue team is out! The red and green teams may continue to play.",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.PLAIN_MESSAGE);
												}
												if(whichTeamWon.equals("no one2")) {
													JOptionPane.showMessageDialog(frame,
															"The green team is out! The red and blue teams may continue to play.",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.PLAIN_MESSAGE);
												}
												if(whichTeamWon.equals("green")) {
													int input = JOptionPane.showOptionDialog(frame,
															"<html>The green team won!<br> What would you like to do?</html>",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymaster.doClick();
														spymasterTurn = true;
													}						    			
													else {
														System.exit(0);
													}
												}
												if(whichTeamWon.equals("blue")) {
													int input = JOptionPane.showOptionDialog(frame,
															"<html>The blue team won!<br> What would you like to do?</html>",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymaster.doClick();
														spymasterTurn = true;
													}						    			
													else {
														System.exit(0);
													}
												}
												if(whichTeamWon.equals("red")) {
													int input = JOptionPane.showOptionDialog(frame,
															"<html>The red team won!<br> What would you like to do?</html>",
															"YOU CHOSE THE ASSASSIN",
															JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymaster.doClick();
														spymasterTurn = true;
													}						    			
													else {
														System.exit(0);
													}
												}
											}
											}
										}
									}
									if(!board.checkGuess(buttonToAdd.getText())) {
										board.setCount(0);
									}
									if(buttonToAdd.getText() == " ") {
										board.setCount(board.getCount() + 1);
									}
									countShower.setText("Count: " + board.getCount());
									if(board.getTurnCount() == 0) {
										turn.setText("Current Turn: RED");
										turn.setForeground(Color.red);
									}
									else if (board.getTurnCount() == 1){
										turn.setText("Current Turn: BLUE");
										turn.setForeground(Color.blue);
									}
									else if(board.getTurnCount() == 2) {
										turn.setText("Current Turn: GREEN");
										turn.setForeground(Color.green);
									}
									
									for(int i = 0; i < 25; i ++) {
										if(buttonToAdd.getText().equals(board.getBoard()[i].getCodename())) {
											if(board.getBoard()[i].getPerson().equals("red")) {
												buttonToAdd.setBackground(Color.red);
											}
											if(board.getBoard()[i].getPerson().equals("blue")) {
												buttonToAdd.setBackground(Color.cyan);
											}
											if(board.getBoard()[i].getPerson().equals("green")) {
												buttonToAdd.setBackground(Color.green);
											}
											if(board.getBoard()[i].getPerson().equals("innocent")) {
												buttonToAdd.setBackground(Color.yellow);
											}
											if(board.getBoard()[i].getPerson().equals("assassin")) {
												buttonToAdd.setBackground(Color.black);
												buttonToAdd.setForeground(Color.white);
												board.whichTeamWonAssassin();
											}
										}
									}
									countShower.setText("Count: " + board.getCount());
									if(board.getTurnCount() == 0) {
										turn.setText("Current Turn: RED");
										turn.setForeground(Color.red);
									}
									else if (board.getTurnCount() == 1){
										turn.setText("Current Turn: BLUE");
										turn.setForeground(Color.blue);
									}
									else if(board.getTurnCount() == 2) {
										turn.setText("Current Turn: GREEN");
										turn.setForeground(Color.green);
									}
									if (board.getCount() == 0 || board.getBluesLeft() == 0 || board.getRedsLeft() == 0 || board.getGreensLeft() == 0) { //never touch this if statement if you value your sanity
										if(board.gameWon()) {
											Object [] options = {"New Game", "Quit"};
											if(board.getBluesLeft() == 0 && board.getTeamOut() != 1) {
												int input = JOptionPane.showOptionDialog(frame,
														"<html>The blue team won!<br> What would you like to do?</html>",
														"GAME OVER",
														JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
												if(board.isTwoPlayerGame()) {
												if(input == JOptionPane.YES_OPTION) {
													newGame.doClick();
													spymasterTurn = false;
													spymaster.doClick();
													spymasterTurn = true;
												}
												else {
													System.exit(0);
												}
												}
												else {
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymaster.doClick();
														spymasterTurn = true;
													}
													else {
														System.exit(0);
													}
												}
											}
										}
										else if(board.getRedsLeft() == 0 && board.getTeamOut() != 0) {
											Object [] options = {"New Game", "Quit"};
											int input = JOptionPane.showOptionDialog(frame,
													"<html>The red team won!<br> What would you like to do?</html>",
													"GAME OVER",
													JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
											if(board.isTwoPlayerGame()) {
												if(input == JOptionPane.YES_OPTION) {
													newGame.doClick();
													spymasterTurn = false;
													spymaster.doClick();
													spymasterTurn = true;
												}
												else {
													System.exit(0);
												}
												}
												else {
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														spymasterTurn = false;
														spymaster.doClick();
														spymaster.doClick();
														spymasterTurn = true;
													}
													else {
														System.exit(0);
													}
												}
										}
										else if(board.getGreensLeft() == 0 && board.getTeamOut() != 2 && !board.isTwoPlayerGame()) {
											Object [] options = {"New Game", "Quit"};
											int input = JOptionPane.showOptionDialog(frame,
													"<html>The green team won!<br> What would you like to do?</html>",
													"GAME OVER",
													JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
													if(input == JOptionPane.YES_OPTION) {
														newGame2.doClick();
														board.setTurnCount(0);
														if(board.getTurnCount() == 0) {
															turn.setText("Current Turn: RED");
															turn.setForeground(Color.red);
														}
													}
													else {
														System.exit(0);
													}
												
										}

										board.updateTurn();
										if(board.getTurnCount() == 0) {
											turn.setText("Current Turn: RED");
											turn.setForeground(Color.red);
										}
										else if (board.getTurnCount() == 1){
											turn.setText("Current Turn: BLUE");
											turn.setForeground(Color.blue);
										}
										else if(board.getTurnCount() == 2) {
											turn.setText("Current Turn: GREEN");
											turn.setForeground(Color.green);
										}
											JOptionPane.showMessageDialog(frame,
													"<html>Please return the computer to the Spymasters<br> If you are the Spymaster, please press 'OK'.</html>",
													"TURN OVER",
													JOptionPane.PLAIN_MESSAGE);
										gameWindow.removeAll();
										clueShower.setText("");
										countShower.setText("");
										for(int i = 0; i < 25; i++) {
											JButton buttonToAdd = new JButton();
											if(board.getBoard()[i].isNotRevealed()) {
												buttonToAdd.setText(board.getBoard()[i].getCodename());
											}
											else {
												buttonToAdd.setText(" ");
											}
											buttonToAdd.setFocusable(false);
											buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
											buttonToAdd.addActionListener(new ActionListener(){
												@Override
												public void actionPerformed(ActionEvent e){
													if(!spymasterTurn) {	
														for(int i = 0; i < 25; i++) {
															if(buttonToAdd.getText().equals(board.getBoard()[i].getCodename())) {
																if(board.getBoard()[i].getPerson().equals("assassin")) {
																	String whichTeamWon = board.whichTeamWonAssassin();
																	Object [] options = {"Yes", "No"};
																	if(whichTeamWon.equals("red")) {
																		JOptionPane.showOptionDialog(frame,
																				"The red team won!",
																				"YOU CHOSE THE ASSASSIN",
																				JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
																	}								    					
																	else {
																		JOptionPane.showMessageDialog(frame,
																				"The blue team won!",
																				"YOU CHOSE THE ASSASSIN",
																				JOptionPane.PLAIN_MESSAGE);
																	}
																}
															}
														}
														if(!board.checkGuess(buttonToAdd.getText())) {
															board.setCount(0);
														}
														if(buttonToAdd.getText() == " ") {
															board.setCount(board.getCount() + 1);
														}
														countShower.setText("Count: " + board.getCount());
														if(board.getTurnCount() == 0) {
															turn.setText("Current Turn: RED");
															turn.setForeground(Color.red);
														}
														else if (board.getTurnCount() == 1){
															turn.setText("Current Turn: BLUE");
															turn.setForeground(Color.blue);
														}
														else if(board.getTurnCount() == 2) {
															turn.setText("Current Turn: GREEN");
															turn.setForeground(Color.green);
														}
													}
												}
											});

											/*
											 * sets button colors for the spymaster section
											 */

											buttonToAdd.setBackground(Color.lightGray);
											if(board.getBoard()[i].getPerson().equals("red")) {
												buttonToAdd.setBackground(Color.red);
											}
											if(board.getBoard()[i].getPerson().equals("blue")) {
												buttonToAdd.setBackground(Color.cyan);
											}
											if(board.getBoard()[i].getPerson().equals("green")) {
												buttonToAdd.setBackground(Color.green);
											}
											if(board.getBoard()[i].getPerson().equals("innocent")) {
												buttonToAdd.setBackground(Color.yellow);
											}
											if(board.getBoard()[i].getPerson().equals("assassin")) {
												buttonToAdd.setBackground(Color.black);
												buttonToAdd.setForeground(Color.white);
											}
											gameWindow.add(buttonToAdd);
										}
										gameWindow.validate();
										spymasterTurn = true;
									}
								}
							}

						});

						/*
						 * sets button colors for the guessing section
						 */

						if(board.getBoard()[i].isNotRevealed()) {
							buttonToAdd.setBackground(Color.lightGray);
						}
						else {
							if(board.getBoard()[i].getPerson().equals("red")) {
								buttonToAdd.setBackground(Color.red);
							}
							if(board.getBoard()[i].getPerson().equals("blue")) {
								buttonToAdd.setBackground(Color.cyan);
							}
							if(board.getBoard()[i].getPerson().equals("green")) {
								buttonToAdd.setBackground(Color.green);
							}
							if(board.getBoard()[i].getPerson().equals("innocent")) {
								buttonToAdd.setBackground(Color.yellow);
							}
							if(board.getBoard()[i].getPerson().equals("assassin")) {
								buttonToAdd.setBackground(Color.black);
								buttonToAdd.setForeground(Color.white);
							}
						}
						if(board.getBoard()[i].getPerson().equals("assassin")) {
							buttonToAdd.setForeground(Color.black);
						}
						gameWindow.add(buttonToAdd);
					}

				}
			}
		});

		/*
		 * allows the guessers to forfeit their turn
		 */

		spymaster.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!spymasterTurn) {
				JOptionPane.showMessageDialog(frame,
						"<html>Please return the computer to the Spymasters<br> If you are the Spymaster, please press 'OK'.</html>",
						"TURN OVER",
						JOptionPane.PLAIN_MESSAGE);
				board.setCount(0);
				board.updateTurn();
				if(board.getTurnCount() == 0) {
					turn.setText("Current Turn: RED");
					turn.setForeground(Color.red);
				}
				else if (board.getTurnCount() == 1){
					turn.setText("Current Turn: BLUE");
					turn.setForeground(Color.blue);
				}
				else if(board.getTurnCount() == 2) {
					turn.setText("Current Turn: GREEN");
					turn.setForeground(Color.green);
				}
				gameWindow.removeAll();
				clueShower.setText("");
				countShower.setText("");
				for(int i = 0; i < 25; i++) {
					JButton buttonToAdd = new JButton();
					if(board.getBoard()[i].isNotRevealed()) {
						buttonToAdd.setText(board.getBoard()[i].getCodename());
					}
					else {
						buttonToAdd.setText(" ");
					}
					buttonToAdd.setFocusable(false);
					buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
					buttonToAdd.setBackground(Color.lightGray);
					if(board.getBoard()[i].getPerson().equals("red")) {
						buttonToAdd.setBackground(Color.red);
					}
					if(board.getBoard()[i].getPerson().equals("blue")) {
						buttonToAdd.setBackground(Color.cyan);
					}
					if(board.getBoard()[i].getPerson().equals("green")) {
						buttonToAdd.setBackground(Color.green);
					}
					if(board.getBoard()[i].getPerson().equals("innocent")) {
						buttonToAdd.setBackground(Color.yellow);
					}
					if(board.getBoard()[i].getPerson().equals("assassin")) {
						buttonToAdd.setBackground(Color.black);
						buttonToAdd.setForeground(Color.white);
					}
					gameWindow.add(buttonToAdd);
				}
				gameWindow.validate();
				spymasterTurn = true;
			}
			}
		});

		/*
		 * adds a file menu with "New Game" and "Exit" options
		 */

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		if(board.getTurnCount() == 0) {
			turn.setText("Current Turn: RED");
			turn.setForeground(Color.red);
		}
		else if (board.getTurnCount() == 1){
			turn.setText("Current Turn: BLUE");
			turn.setForeground(Color.blue);
		}
		else if(board.getTurnCount() == 2) {
			turn.setText("Current Turn: GREEN");
			turn.setForeground(Color.green);
		}

		/*
		 * makes "Exit" work
		 */

		JMenuItem exit = new JMenuItem(new AbstractAction("Exit"){
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		/*
		 * adds components to GUI
		 */

		menu.add(newGame);
		menu.add(newGame2);
		menu.add(exit);
		menuBar.add(menu);   
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(clueShower);
		menuBar.add(countShower);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(turn);
		frame.add(menuBar);
		frame.setJMenuBar(menuBar);	    
		GridLayout grid = new GridLayout(5,5);
		gameWindow.setLayout(grid);

		/*
		 * initial game start
		 */
		for(int i = 0; i < 25; i++) {
			JButton buttonToAdd = new JButton();
			buttonList.add(buttonToAdd);
			buttonToAdd.setText(board.getBoard()[i].getCodename());
			buttonToAdd.setFocusable(false);
			buttonToAdd.setFont(new Font(null, Font.BOLD, 12));	    
			if(board.getBoard()[i].getPerson().equals("red")) {
				buttonToAdd.setBackground(Color.red);
			}
			if(board.getBoard()[i].getPerson().equals("blue")) {
				buttonToAdd.setBackground(Color.cyan);
			}
			if(board.getBoard()[i].getPerson().equals("green")) {
				buttonToAdd.setBackground(Color.green);
			}
			if(board.getBoard()[i].getPerson().equals("innocent")) {
				buttonToAdd.setBackground(Color.yellow);
			}
			if(board.getBoard()[i].getPerson().equals("assassin")) {
				buttonToAdd.setBackground(Color.black);
				buttonToAdd.setForeground(Color.white);
			}
			gameWindow.add(buttonToAdd);
		}

		/*
		 * formats GUI
		 */

		frame.add(gameWindow, BorderLayout.CENTER);
		frame.add(buttonPlace, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(600, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); //this makes the window open in the middle of the screen
	}
}