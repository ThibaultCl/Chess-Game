package model;

import java.util.ArrayList;
import java.util.List;

public class Echiquier implements BoardGames{
	
	private Jeu jeuBlanc;
	private Jeu jeuNoir;
	private Couleur joueur;
	private boolean fin;

	public Echiquier(){
		this.jeuBlanc=new Jeu(Couleur.BLANC); 
		this.jeuNoir=new Jeu(Couleur.NOIR); 
		this.joueur=Couleur.BLANC;   
		this.fin=false;
	}
	
	/**
	 * 
	 * @return     une liste de PieceIHM qui pourra Ãªtre exploitÃ©e par une IHM
	 */
	public List<PieceIHM>  getPiecesIHM() {
		List<PieceIHM> listPieceIHM=new ArrayList<PieceIHM>();
		listPieceIHM.addAll(jeuNoir.getPiecesIHM());
		listPieceIHM.addAll(jeuBlanc.getPiecesIHM());
		return listPieceIHM;
	}

	public String getMessage() {


		
		return "on est dans get message echiquier";
	}
/**
 * 
 * @param xInit
 * @param yInit
 * @param xFinal
 * @param yFinal
 * @return s'il n'existe pas de piece du jeu courant aux coordonnées initiales --> false,
si les coordonnées finales ne sont pas valides ou égales aux initiales --> false,
si position finale ne correspond pas Ã  algo de déplacement piece --> false,
s'il existe une piéce intermédiaire sur la trajectoire --> false (sauf cavalier),
s'il existe une piéce positionnées aux coordonnées finales :
si elle est de la même couleur --> false ou tentative roque du roi,
sinon : prendre la piéce intermédiaire (vigilance pour le cas du pion) et déplacer la pièce -->true,
sinon déplacer la pièce -->true
 */
	public boolean isMoveOk(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret=false;
		//si pièce sur case initiale
		if (jeuBlanc.isPieceHere(xInit, yInit)||jeuNoir.isPieceHere(xInit, yInit)){
			//si les coordonnées finales valides
			if((xInit!=xFinal|| yInit!=yFinal)&& (xFinal<8 && xFinal>=0 && yFinal<=7 && yFinal>=0)){
				//si mouvement correspond à mouvement de pièce possible
				if(jeuBlanc.isMoveOk(xInit, yInit, xFinal, yFinal) || jeuNoir.isMoveOk(xInit, yInit, xFinal, yFinal)){
					//TODO s'il existe une piÃ©ce intermÃ©diaire sur la trajectoire --> false (sauf cavalier)
					
					//si pièce sur case finale
					if(jeuBlanc.isPieceHere(xFinal, yFinal) || jeuNoir.isPieceHere(xFinal, yFinal)){
						//si pièce case final blanche
						if(jeuBlanc.isPieceHere(xInit, yInit) && jeuBlanc.isPieceHere(xFinal, yFinal)){
							//si mouvement est un roque
							if(jeuBlanc.getPieceType(xInit, yInit)=="Tour" && jeuBlanc.getKingCoord().equals(new Coord(xFinal,yFinal))){
								jeuBlanc.setCastling();//roque autorisé
								ret=true;
							}
						}
						//si pièce case final noire
						else if(jeuNoir.isPieceHere(xInit, yInit) && jeuNoir.isPieceHere(xFinal, yFinal)) {
							//si mouvement est un roque
							if(jeuNoir.getPieceType(xInit, yInit)=="Tour" && jeuNoir.getKingCoord().equals(new Coord(xFinal,yFinal))){
								jeuNoir.setCastling();//roque autorisé
								ret=true;
							}
						}
					}
					//pas de pièce sur la case finale
					else {
						ret=true;
					}
				}
			}			
		}
		return ret;
	}		
		

/**
 *  
 * @param xInit
 * @param yInit
 * @param xFinal
 * @param yFinal
 * @return Permet de déplacer une piÃ©ce connaissant ses coordonnÃ©es initiales vers ses coordonnÃ©es finales. 
 * l'algo vÃ©rifie que le dÃ©placement est légal, effectue ce déplacement avec l'Ã©ventuelle capture, rembobine si le déplacement et la capture ont mis le roi en échec 
 */
	public boolean move(int xInit, int yInit, int xFinal, int yFinal) {
		boolean ret=false;
		if(jeuBlanc.isPieceHere(xInit, yInit) && isMoveOk(xInit, yInit, xFinal, yFinal)){
			jeuBlanc.move(xInit, yInit, xFinal, yFinal);
			ret=true;
		}
		else if(jeuNoir.isPieceHere(xInit, yInit)&& isMoveOk(xInit, yInit, xFinal, yFinal)){
			jeuNoir.move(xInit, yInit, xFinal, yFinal);
			ret=true;
		}
	//TO DO rembobine si le déplacement et la capture ont mis le roi en échec 
		
		return ret;
	}
	
	/**
	 * Permet de changer le joueur courant. 
	 */
	public void switchJoueur() {
		
	if (this.joueur==Couleur.BLANC)
			joueur=Couleur.NOIR;
	else
		joueur=Couleur.BLANC;
	}

	/**
	 *     true si c'est la fin du jeu
	 */
	public boolean isEnd() {
		return fin;
	}

	/**
	 * @return la Couleur du joueur courrant
	 */
	public Couleur getColorCurrentPlayer() {
		return joueur;
	}

	public Couleur getPieceColor(int x, int y) {
		//TODO
		Couleur ret=Couleur.NOIR;
		if(jeuBlanc.isPieceHere(x, y)){ret=Couleur.BLANC;}
		return ret;
	}	
	
	@Override
	public String toString() {
		
		return this.jeuBlanc.toString()+this.jeuNoir.toString();
	}

	public static void main(String[] args){
	Echiquier echiquier=new Echiquier();
	System.out.println(echiquier);
	echiquier.switchJoueur();
	System.out.println(echiquier.joueur);
	System.out.println(echiquier.move(3, 6, 3, 5));

	}
}

