package model;
/**
 * Class définissant une Piece Tour
 * @author thibault.caussanel
 *
 */
public class Tour extends AbstractPiece{

	/**
	 * Constructeur de Tour
	 * @param couleur de la tour
	 * @param coord de la tour
	 */
	public Tour(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if((this.getX()==xFinal && this.getY()!=yFinal)||(this.getX()!=xFinal && this.getY()==yFinal)){
			return true;
		}
		else
			return false;
	}

}
