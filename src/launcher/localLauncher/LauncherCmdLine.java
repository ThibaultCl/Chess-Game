package launcher.localLauncher;

import java.util.Observer;

import model.Coord;
import model.observable.ChessGame;
import vue.ChessGameCmdLine;
import controler.AbstractChessGameControler;
import controler.controlerLocal.ChessGameControler;


/**
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
	public static void main(String[] args) {		
		
		ChessGame model;
		AbstractChessGameControler controler;	
		ChessGameCmdLine vue;
		
		model = new ChessGame();	
		controler = new ChessGameControler(model);
		//controler.move(new Coord(3, 6), new Coord(3,5));

		new ChessGameCmdLine(controler);	

		vue = new ChessGameCmdLine(controler);
		model.addObserver((Observer) vue);
		vue.go();
	}

}
