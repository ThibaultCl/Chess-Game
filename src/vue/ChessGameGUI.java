package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import model.Couleur;
import tools.ChessImageProvider;
import tools.ChessPiecePos;
import controler.ChessGameControlers;
import controler.controlerLocal.ChessGameControler;

public class ChessGameGUI extends JFrame implements Observer,MouseListener,MouseMotionListener {

	private ChessGameControler chessGameControler;
	
	public ChessGameGUI(String string, ChessGameControlers chessGameControler,
			Dimension dim) {
		// TODO Auto-generated constructor stub
		
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}


	
	
	
	 JLayeredPane layeredPane;
	  JPanel chessBoard;
	  JLabel chessPiece;
	  int xAdjustment;
	  int yAdjustment;
	 
	  public ChessGameGUI(){
	  Dimension boardSize = new Dimension(600, 600);
	 
	  //  Use a Layered Pane for this this application
	 layeredPane = new JLayeredPane();
	  getContentPane().add(layeredPane);
	  layeredPane.setPreferredSize(boardSize);
	  layeredPane.addMouseListener(this);
	  layeredPane.addMouseMotionListener(this);

	  //Add a chess board to the Layered Pane 
	 
	  chessBoard = new JPanel();
	  layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
	  chessBoard.setLayout( new GridLayout(8, 8) );
	  chessBoard.setPreferredSize( boardSize );
	  chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	 
	  for (int i = 0; i < 64; i++) {
	  JPanel square = new JPanel( new BorderLayout() );
	  chessBoard.add( square );
	 
	  int row = (i / 8) % 2;
	  if (row == 0)
	  square.setBackground( i % 2 == 0 ? Color.black : Color.white );
	  else
	  square.setBackground( i % 2 == 0 ? Color.white : Color.black );
	  }
	 
	  //Add a few pieces to the board
	/* for (int i=0;i<8; i++){
	  JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile("Pion", Couleur.NOIR)) );
	  JPanel panel = (JPanel)chessBoard.getComponent(8+i);
	  panel.add(piece);
	  }*/
	  
	  for (int i = 0; i < ChessPiecePos.values().length; i++) {
		  
		  JLabel piece = new JLabel( new ImageIcon(ChessImageProvider.getImageFile(ChessPiecePos.values()[i].nom, Couleur.NOIR)) );
		  for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) {
			  JPanel panel = (JPanel)chessBoard.getComponent(ChessPiecePos.values()[i].coords[j].x+ChessPiecePos.values()[i].coords[j].y+1 );
			  System.out.println(ChessPiecePos.values()[i].coords[j].x+ChessPiecePos.values()[i].coords[j].y );
			  panel.add(piece);
		}
		 	  
	  }
			

	  
	  /*
	  JLabel piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile("Fou", Couleur.NOIR)) );
	  JPanel panel = (JPanel)chessBoard.getComponent(17);
	  panel.add(piece);
	  piece = new JLabel(new ImageIcon("~/home/workspace/JeuEchec/images/pionBlancS.png"));
	  panel = (JPanel)chessBoard.getComponent(16);
	  panel.add(piece);
	  piece = new JLabel(new ImageIcon("/fs03/share/users/thibault.caussanel/home/workspace/JeuEchec/images/roiBlancS.png"));
	  panel = (JPanel)chessBoard.getComponent(63);
	  panel.add(piece);*/

	  }
	 
	  public void mousePressed(MouseEvent e){
	  chessPiece = null;
	  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	 
	  if (c instanceof JPanel) 
	  return;
	 
	  Point parentLocation = c.getParent().getLocation();
	  xAdjustment = parentLocation.x - e.getX();
	  yAdjustment = parentLocation.y - e.getY();
	  chessPiece = (JLabel)c;
	  chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
	  chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
	  layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
	  }
	 
	  //Move the chess piece around
	  
	  public void mouseDragged(MouseEvent me) {
	  if (chessPiece == null) return;
	 chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
	 }
	 
	  //Drop the chess piece back onto the chess board
	 
	  public void mouseReleased(MouseEvent e) {
	  if(chessPiece == null) return;
	 
	  chessPiece.setVisible(false);
	  Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
	 
	  if (c instanceof JLabel){
	  Container parent = c.getParent();
	  parent.remove(0);
	  parent.add( chessPiece );
	  }
	  else {
	  Container parent = (Container)c;
	  parent.add( chessPiece );
	  }
	 
	  chessPiece.setVisible(true);
	  }
	 
	  public void mouseClicked(MouseEvent e) {
	  
	  }
	  public void mouseMoved(MouseEvent e) {
	 }
	  public void mouseEntered(MouseEvent e){
	  
	  }
	  public void mouseExited(MouseEvent e) {
	  
	  }
	 
	
	 


public static void main(String[] args) {
	  JFrame frame = new ChessGameGUI();
	  frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
	  frame.pack();
	  frame.setResizable(true);
	  frame.setLocationRelativeTo( null );
	  frame.setVisible(true);
	  
  for (int i = 0; i < ChessPiecePos.values().length; i++) {
		  
		  
		 //System.out.println(ChessPiecePos.values()[i].nom);
		  for (int j = 0; j < (ChessPiecePos.values()[i].coords).length; j++) {
			  //System.out.println(ChessPiecePos.values()[i].coords[j].x);
	}
		 	  
	  }
			
}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


