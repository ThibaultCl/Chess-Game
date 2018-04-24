package model;
/**
 * Class définissant une Piece Roi
 * @author thibault.caussanel
 *
 */
public class Roi extends AbstractPiece {

	public Roi(Couleur couleur, Coord coord) {
		super(couleur, coord);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if((this.getX()==xFinal+1 || this.getY()!=yFinal+1)||(this.getX()!=xFinal-1 || this.getY()==yFinal-1)){
			return true;
		}
		else
			return false;
	}
	

}
