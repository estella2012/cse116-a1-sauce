package GUIs;

import code.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class MainGUI {
	
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
		JPanel gameWindow = new JPanel();	
	    JMenuBar menuBar = new JMenuBar();
	    JMenu menu = new JMenu("File");
	    JMenu turn = new JMenu();
	    if(board.isRedTeamTurn()) {
	    	turn.setText("Current Turn: RED");
	    	turn.setForeground(Color.red);
	    }
	    else{
	    	turn.setText("Current Turn: BLUE");
	    	turn.setForeground(Color.blue);
	    }
	    
	    JMenuItem newGame = new JMenuItem(new AbstractAction("New Game") {
	    	 public void actionPerformed(ActionEvent e) {
	    		 	board.createBoard();
	    		    board.gameStart("src/GameWords.txt");
	    		    gameWindow.removeAll();
	    		    for(int i = 0; i < 25; i++) {
	    		    	JButton buttonToAdd = new JButton();
	    		    	buttonToAdd.setText(board.getBoard()[i].getCodename());
	    		    	buttonToAdd.setFocusable(false);
	    		    	buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
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
	    	    }
	    	});
	    
	    JMenuItem exit = new JMenuItem(new AbstractAction("Exit"){
	    	 public void actionPerformed(ActionEvent e) {
	    		 System.exit(0);
	    	    }
	    	});
	    
	    menu.add(newGame);
	    menu.add(exit);
	    menuBar.add(menu);
	    menuBar.add(Box.createHorizontalGlue());
	    menuBar.add(turn);
	    frame.add(menuBar);
	    frame.setJMenuBar(menuBar);	    
		GridLayout grid = new GridLayout(5,5);
		gameWindow.setLayout(grid);
		
		for(int i = 0; i < 25; i++) {
	    	JButton buttonToAdd = new JButton();
	    	buttonToAdd.setText(board.getBoard()[i].getCodename());
	    	buttonToAdd.setFocusable(false);
	    	buttonToAdd.setFont(new Font(null, Font.BOLD, 12));
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
		
		frame.add(gameWindow);	    
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.pack();
	    frame.setSize(600, 500);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2); //this makes the window open in the middle of the screen
	}
}