package model;
/**
 * interface contenant les méthodes communes à toutes les Pièces
 * @author thibault.caussanel
 *
 */
public interface Pieces {

	/**
	 * @return true si la pièce est capturée et Positionne x et y à -1, false si la pièce est déjà capturée
	 */
	boolean capture();
	/**
	 * @return la couleur de la pièce
	 */
	Couleur getCouleur();
	/**
	 * 
	 * @return la coordonnée X de la pièce
	 */
	int getX();
	/**
	 * 
	 * @returnla la coordonnée Y de la pièce
	 */
	int getY();
	/**
	 * 
	 * @param xFinal
	 * @param yFinal
	 * @return true si le déplacement de la pièce est possible, false sinon
	 */
	boolean isMoveOk(int xFinal, int yFinal);
	/**
	 * déplace la pièce aux coordonnéés (xFinal,yFinal)
	 * @param xFinal
	 * @param yFinal
	 * @return true si le déplacement a été effectué, false sinon
	 */
	boolean move(int xFinal, int yFinal);
	
}
