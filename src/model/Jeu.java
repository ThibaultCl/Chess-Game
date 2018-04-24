package model;

import java.util.LinkedList;
import java.util.List;

import tools.ChessPiecesFactory;
/**
 * Class définissant et initialisant les pièces d'une couleur donnée
 * @author thibault.caussanel
 *
 */
public class Jeu {
	/**
	 * List de Pieces contenant toutes les pièces d'une couleur
	 */
	private List<Pieces> list_piece;
	/**
	 * Couleur des pièces de ce jeu
	 */
	private Couleur couleur;
	/**
	 * autorisation du roque
	 */
	private boolean castling;
	
	/**
	 * Constructeur de Couleur
	 * @param couleur
	 */
	public Jeu(Couleur couleur){
		this.list_piece=ChessPiecesFactory.newPieces(couleur);
		this.couleur=couleur;
		this.castling=false;
	}
	
	/**
	 * Cherche si une pièce est présente à la position (x,y) donnée
	 * @param x
	 * @param y
	 * @return la Pieces trouvée à la position x,y , une Pieces vide si elle n'existe pas
	 */
	private Pieces findPiece(int x, int y){
		Pieces ret=null;
		for(Pieces piece: list_piece){
			if(piece.getX()==x && piece.getY()==y)
				ret=piece;
		}
		return ret;
	}
	
	/**
	 * Cherche si une pièce est présente à ces coordonnées x,y
	 * @param x
	 * @param y
	 * @return true s'il y a une pièce, false sinon
	 */
	public boolean isPieceHere(int x,int y){
		return this.findPiece(x, y)!=null;
	}
	
	/**
	 * teste si le déplacement de la pièce en xInit,Yinit peut être déplacée en xFinal,yFinal
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true si la pièce est présente et peut être bougée la pièce, false sinon
	 */
	public boolean isMoveOk(int xInit,int yInit,int xFinal,int yFinal){
		boolean ret=false;
		if(this.isPieceHere(xInit, yInit))
			ret=this.findPiece(xInit, yInit).isMoveOk(xFinal, yFinal);
		return	ret;
	}
	
	/**
	 * Déplace la pièce en xInit,yInit vers xFinal,yFinal
	 * @param xInit
	 * @param yInit
	 * @param xFinal
	 * @param yFinal
	 * @return true si le déplacement est effectué, false sinon
	 */
	public boolean move(int xInit,int yInit,int xFinal,int yFinal){
		boolean ret=false;
		if(this.isMoveOk(xInit, yInit, xFinal, yFinal))
			ret=this.findPiece(xInit, yInit).move(xFinal, yFinal);
		return ret;
	}
	
	/**
	 * Si une capture d'une piece de l'autre jeu est possible met Ã  jour 1 boolÃ©en 
	 */
	public void setPossibleCapture(){
		//TODO
	}
	
	/**
	 * 
	 * @param xCatch
	 * @param yCatch
	 * @return true si la piece aux coordonnees finales a été capturée
	 */
	public boolean capture(int xCatch,int yCatch){
		//TODO à modifié pièce intermédiaire
		boolean ret=false;
		if(isPieceHere(xCatch, yCatch))
			ret=findPiece(xCatch, yCatch).capture();
		return ret;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return couleur de la piece aux coordonnées x, y
	 */
	public Couleur getPieceColor(int x,int y){
		Couleur color=null;
		if(isPieceHere(x, y))
			color=findPiece(x, y).getCouleur();
		return color;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return   type de la pièce aux coordonnées x,y c'est à dire le nom de la classe : maPiece.getClass().getSimpleName();
	 */
	public String getPieceType(int x, int y){
		String name=null;
		if(isPieceHere(x, y))
			name=findPiece(x, y).getClass().getSimpleName();
		return name;
		
	}
	
	/**
	 * 
	 * @return  couleur du jeu
	 */
	public Couleur getCouleur(){
		return this.couleur;
	}
	
	/**
	* @return une vue de la liste des pièces en cours
	* ne donnant que des accès en lecture sur des PieceIHM
	* (type piece + couleur + liste de coordonnées)
	*/
	public List<PieceIHM> getPiecesIHM(){
		PieceIHM newPieceIHM = null;
		List<PieceIHM> list = new LinkedList<PieceIHM>();
		for (Pieces piece : list_piece){
			boolean existe = false;
			// si le type de piece existe déjà dans la liste de PieceIHM
			// ajout des coordonnées de la pièce dans la liste de Coord de ce type
			// si elle est toujours en jeu (x et y != -1)
			for ( PieceIHM pieceIHM : list){
				if ((pieceIHM.getTypePiece()).equals(piece.getClass().getSimpleName())){
					existe = true;
					if (piece.getX() != -1){
						pieceIHM.add(new Coord(piece.getX(), piece.getY()));
					}
				}
			}
			// sinon, création d'une nouvelle PieceIHM si la pièce est toujours en jeu
			if (! existe) {
				if (piece.getX() != -1){
					newPieceIHM = new PieceIHM(piece.getClass().getSimpleName(),
					piece.getCouleur());
					newPieceIHM.add(new Coord(piece.getX(), piece.getY()));
					list.add(newPieceIHM);
				}
			}
		}
		return list;
	}
	
	/**
	 * met Ã  jour un booléen pour activer l'hypothèse d'un roque du roi
	 */
	public void setCastling(){
		this.castling=true;
	}
	
	/**
	 * annuler le mouvement précédent
	 */
	public void undoMove(){
		//TODO
	}
	
	/**
	 * vérifie si la promotion d'un pion est possible
	 * @param xFinal
	 * @param yfinal
	 * @return true si on est bien dans le cas d'une promotion du pion, false sinon
	 */
	public boolean isPawnPromotion(int xFinal,int yfinal){
		boolean ret=false;
		if(this.findPiece(xFinal, yfinal).getClass().getSimpleName().equals("Pion"))
			ret=  yfinal==0 ||  yfinal==7;	
		return ret;
	}
	
	/**
	 * Promotion du pion en xFinal,yFinal en un type donné
	 * @param xFinal
	 * @param yfinal
	 * @param type :type de Pieces dans lequel le pion est promu
	 * @return true si la promotion a eu lieu, false sinon
	 */
	public boolean pawnPromotion(int xFinal,int yfinal,String type){
		boolean ret=false;
		if(this.isPawnPromotion(xFinal,yfinal)){
			
			this.findPiece(xFinal, yfinal).capture();
			list_piece.add(new Reine(couleur, new Coord(xFinal, yfinal)));
		
		
		}
		return ret;
	}
	
	/**
	 * Recherche les coordonnées du roi
	 * @return les coordonnées du roi
	 */
	public Coord getKingCoord(){
		Coord ret=new Coord(-1,-1);
		for(Pieces piece: list_piece){
			if(piece.getClass().getSimpleName().equals("Roi")){
				ret.x=piece.getX();
				ret.y=piece.getY();
			}
		}
		return ret;
	}
            
	
	@Override
	public String toString() {
		return list_piece.toString();
	}
	
	public static void main(String[] args){
		Jeu jeu = new Jeu(Couleur.BLANC);
		System.out.println(jeu.toString());
		System.out.println(jeu.findPiece(0, 7));
		System.out.println(jeu.isPieceHere(0, 7));
		System.out.println(jeu.isMoveOk(0, 7,0,0));
		System.out.println(jeu.findPiece(0, 6).move(0, 7));
		System.out.println(jeu.isPawnPromotion(0, 7));

	}
}
