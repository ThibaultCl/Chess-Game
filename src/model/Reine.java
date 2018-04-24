package model;
/**
 * Class définissant une Piece Dame
 * @author thibault.caussanel
 *
 */
public class Reine extends AbstractPiece {

	public Reine(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		if((this.getX()==xFinal && this.getY()!=yFinal)||(this.getX()!=xFinal && this.getY()==yFinal)||Math.abs(this.getX()-xFinal)==Math.abs(this.getY()-yFinal))
		{
			return true;
		}
		else
			return false;
	
	}

}
