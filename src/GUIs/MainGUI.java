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
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainGUI {
	
	private static String clue;
	private static boolean spymasterTurn = true;
	private static ArrayList<JButton> buttonList = new ArrayList<>();
	
	@SuppressWarnings("serial")
	public static void run() {
		
		//this try catch block just makes it look less ugly
		try {
    	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if ("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch (Exception e) {
    	}
		
		    
	    Board board = new Board();
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
		
		Action newGameAction = new AbstractAction() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
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
    		    /*	buttonToAdd.addActionListener(new ActionListener(){
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
    			    			if(board.isRedTeamTurn()) {
    			    				board.setRedTeamTurn(false);
    			    			}
    			    			else {
    			    				board.setRedTeamTurn(true);
    			    			}
    			    		}
				    		if(buttonToAdd.getText() == " ") {
				    			board.setCount(board.getCount() + 1);
				    		}
    			    		countShower.setText("Count: " + board.getCount());
    			    		board.updateTurn();
    			    		if(board.isRedTeamTurn()) {
    			    	    	turn.setText("Current Turn: RED");
    			    	    	turn.setForeground(Color.red);
    			    	    }
    			    	    else{
    			    	    	turn.setText("Current Turn: BLUE");
    			    	    	turn.setForeground(Color.blue);
    			    	    }
    			    	}
    		    	}
    		    	}); */
    		    	if(board.getBoard()[i].getPerson().equals("red")) {
    		    		buttonToAdd.setBackground(Color.red);
    		    	}
    		    	if(board.getBoard()[i].getPerson().equals("blue")) {
    		    		buttonToAdd.setBackground(Color.cyan);
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
	    	newGameAction.putValue(Action.NAME, "New Game");
	    	
	    	JMenuItem newGame = new JMenuItem(newGameAction);
		
		submitClue.addActionListener(new ActionListener(){
	    	@Override
	        public void actionPerformed(ActionEvent e){
	    		if(spymasterTurn) {
	    		Object[] possibilities = null;
	    		String s = (String)JOptionPane.showInputDialog(
	    		                    frame,
	    		                    "Type your clue below:\n",
	    		                    "Customized Dialog",
	    		                    JOptionPane.PLAIN_MESSAGE,
	    		                    null,
	    		                    possibilities,
	    		                    "");
	    		while(!(s == null) && board.checkIllegalClue(s)) {
	    			possibilities = null;
		    		s = (String)JOptionPane.showInputDialog(
		    		                    frame,
		    		                    "Illegal Clue, please choose another:\n",
		    		                    "Customized Dialog",
		    		                    JOptionPane.PLAIN_MESSAGE,
		    		                    null,
		    		                    possibilities,
		    		                    "");
	    		}
	    		clue = s;
	    		clue = clue.toUpperCase();
	    		clueShower.setText("Clue: " + clue);
	    		Object[] possibilities2 = {"1","2","3","4","5","6","7","8","9"};
	    		if(!board.isRedTeamTurn()) {
	    			possibilities2[8] = null;
	    		}
				s = (String)JOptionPane.showInputDialog(
	    		                    frame,
	    		                    "What is the count?\n",
	    		                    "Customized Dialog",
	    		                    JOptionPane.PLAIN_MESSAGE,
	    		                    null,
	    		                    possibilities2,
	    		                    "");
				board.setCount(Integer.parseInt(s));
	    		countShower.setText("Count: " + s);
	    		spymasterTurn = false;
	    		gameWindow.removeAll();
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
			    					else {
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
			    			}
			    		}
			    		if(!board.checkGuess(buttonToAdd.getText())) {
			    			/*if(board.isRedTeamTurn()) {
			    				board.setRedTeamTurn(false);
			    			}
			    			else {
			    				board.setRedTeamTurn(true);
			    			}*/
			    			board.setCount(0);
			    		}
			    		if(buttonToAdd.getText() == " ") {
			    			board.setCount(board.getCount() + 1);
			    		}
			    		countShower.setText("Count: " + board.getCount());
			    		board.updateTurn();
			    		if(board.isRedTeamTurn()) {
			    	    	turn.setText("Current Turn: RED");
			    	    	turn.setForeground(Color.red);
			    	    }
			    	    else{
			    	    	turn.setText("Current Turn: BLUE");
			    	    	turn.setForeground(Color.blue);
			    	    }
			    		for(int i = 0; i < 25; i ++) {
			    			if(buttonToAdd.getText().equals(board.getBoard()[i].getCodename())) {
			    				if(board.getBoard()[i].getPerson().equals("red")) {
			    		    		buttonToAdd.setBackground(Color.red);
			    		    	}
			    		    	if(board.getBoard()[i].getPerson().equals("blue")) {
			    		    		buttonToAdd.setBackground(Color.cyan);
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
			    		board.updateTurn();
			    		if(board.isRedTeamTurn()) {
			    	    	turn.setText("Current Turn: RED");
			    	    	turn.setForeground(Color.red);
			    	    }
			    	    else{
			    	    	turn.setText("Current Turn: BLUE");
			    	    	turn.setForeground(Color.blue);
			    	    }		    		
			    		if (board.getCount() == 0 || board.getBluesLeft() == 0 || board.getRedsLeft() == 0) {
			    			if(!board.gameWon()) {
			    				if(board.getBluesLeft() == 0) {
					    			JOptionPane.showMessageDialog(frame,
					    				    "The blue team won!",
					    				    "GAME OVER",
					    				    JOptionPane.PLAIN_MESSAGE);
			    				}
			    				if(board.getRedsLeft() == 0) {
					    			JOptionPane.showMessageDialog(frame,
					    				    "The red team won!",
					    				    "GAME OVER",
					    				    JOptionPane.PLAIN_MESSAGE);
			    				}
			    			}
			    			board.updateTurn();
				    		if(board.isRedTeamTurn()) {
				    	    	turn.setText("Current Turn: RED");
				    	    	turn.setForeground(Color.red);
				    	    }
				    	    else{
				    	    	turn.setText("Current Turn: BLUE");
				    	    	turn.setForeground(Color.blue);
				    	    }
				    		if(!board.gameWon()) {
			    			JOptionPane.showMessageDialog(frame,
			    				    "<html>Please return the computer to the Spymasters<br> If you are the Spymaster, please press 'OK'.</html>",
			    				    "TURN OVER",
			    				    JOptionPane.PLAIN_MESSAGE);
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
							    			if(board.isRedTeamTurn()) {
							    				board.setRedTeamTurn(false);
							    			}
							    			else {
							    				board.setRedTeamTurn(true);
							    			}
							    		}
							    		if(buttonToAdd.getText() == " ") {
							    			board.setCount(board.getCount() + 1);
							    		}
			    			    		countShower.setText("Count: " + board.getCount());
			    			    		board.updateTurn();
			    			    		if(board.isRedTeamTurn()) {
			    			    	    	turn.setText("Current Turn: RED");
			    			    	    	turn.setForeground(Color.red);
			    			    	    }
			    			    	    else{
			    			    	    	turn.setText("Current Turn: BLUE");
			    			    	    	turn.setForeground(Color.blue);
			    			    	    }
							    	}
						    	}
						    	});
						    		buttonToAdd.setBackground(Color.lightGray);
						    		if(board.getBoard()[i].getPerson().equals("red")) {
				    		    		buttonToAdd.setBackground(Color.red);
				    		    	}
				    		    	if(board.getBoard()[i].getPerson().equals("blue")) {
				    		    		buttonToAdd.setBackground(Color.cyan);
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
		
		spymaster.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,
				    "<html>Please return the computer to the Spymasters<br> If you are the Spymaster, please press 'OK'.</html>",
				    "TURN OVER",
				    JOptionPane.PLAIN_MESSAGE);
				board.setCount(0);
				board.updateTurn();
				if(board.isRedTeamTurn()) {
	    	    	turn.setText("Current Turn: RED");
	    	    	turn.setForeground(Color.red);
	    	    }
	    	    else{
	    	    	turn.setText("Current Turn: BLUE");
	    	    	turn.setForeground(Color.blue);
	    	    }
				gameWindow.removeAll();
				clueShower.setText("");
    		    countShower.setText("");
				for(int i = 0; i < 25; i++) {
			    	JButton buttonToAdd = new JButton();
			    	for(int j = 0; j < 25; j++) {
		    			if(buttonToAdd.getText().equals(board.getBoard()[j].getCodename())) {
		    				if(board.getBoard()[j].getPerson().equals("assassin")) {
		    					String whichTeamWon = board.whichTeamWonAssassin();
		    					if(whichTeamWon.equals("red")) {
					    			JOptionPane.showMessageDialog(frame,
					    				    "The red team won!",
					    				    "YOU CHOSE THE ASSASSIN",
					    				    JOptionPane.PLAIN_MESSAGE);
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
				    			if(board.isRedTeamTurn()) {
				    				board.setRedTeamTurn(false);
				    			}
				    			else {
				    				board.setRedTeamTurn(true);
				    			}
				    		}
				    		if(buttonToAdd.getText() == " ") {
				    			board.setCount(board.getCount() + 1);
				    		}
    			    		countShower.setText("Count: " + board.getCount());
    			    		board.updateTurn();
    			    		if(board.isRedTeamTurn()) {
    			    	    	turn.setText("Current Turn: RED");
    			    	    	turn.setForeground(Color.red);
    			    	    }
    			    	    else{
    			    	    	turn.setText("Current Turn: BLUE");
    			    	    	turn.setForeground(Color.blue);
    			    	    }
				    	}
			    	}
			    	});
			    		buttonToAdd.setBackground(Color.lightGray);
			    		if(board.getBoard()[i].getPerson().equals("red")) {
	    		    		buttonToAdd.setBackground(Color.red);
	    		    	}
	    		    	if(board.getBoard()[i].getPerson().equals("blue")) {
	    		    		buttonToAdd.setBackground(Color.cyan);
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
		});
		
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu = new JMenu("File");
	    if(board.isRedTeamTurn()) {
	    	turn.setText("Current Turn: RED");
	    	turn.setForeground(Color.red);
	    }
	    else{
	    	turn.setText("Current Turn: BLUE");
	    	turn.setForeground(Color.blue);
	    }
	    	    
	    
	    
	    JMenuItem exit = new JMenuItem(new AbstractAction("Exit"){
	    	 public void actionPerformed(ActionEvent e) {
	    		 System.exit(0);
	    	    }
	    	});
	    
	    menu.add(newGame);
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
		    			if(board.isRedTeamTurn()) {
		    				board.setRedTeamTurn(false);
		    			}
		    			else {
		    				board.setRedTeamTurn(true);
		    			}
		    		}
		    		if(buttonToAdd.getText() == " ") {
		    			board.setCount(board.getCount() + 1);
		    		}
		    		countShower.setText("Count: " + board.getCount());
		    		board.updateTurn();
		    		if(board.isRedTeamTurn()) {
		    	    	turn.setText("Current Turn: RED");
		    	    	turn.setForeground(Color.red);
		    	    }
		    	    else{
		    	    	turn.setText("Current Turn: BLUE");
		    	    	turn.setForeground(Color.blue);
		    	    }
		    	}
	    	}
	    	});
	    	if(board.getBoard()[i].getPerson().equals("red")) {
	    		buttonToAdd.setBackground(Color.red);
	    	}
	    	if(board.getBoard()[i].getPerson().equals("blue")) {
	    		buttonToAdd.setBackground(Color.cyan);
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