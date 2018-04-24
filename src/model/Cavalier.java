package model;
/**
 * Class définissant une Piece Cavalier
 * @author thibault.caussanel
 *
 */
public class Cavalier extends AbstractPiece {

	
	
	public Cavalier(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		return ((this.getX()==xFinal+2 && this.getY()==yFinal+1)||
				(this.getX()==xFinal-2 && this.getY()==yFinal+1)||
				(this.getX()==xFinal-2 && this.getY()==yFinal-1)||
				(this.getX()==xFinal+2 && this.getY()==yFinal-1)||
				(this.getX()==xFinal+1 && this.getY()==yFinal+2)||
				(this.getX()==xFinal-1 && this.getY()==yFinal+2)||
				(this.getX()==xFinal-1 && this.getY()==yFinal-2)||
				(this.getX()==xFinal+1 && this.getY()==yFinal-2));
	}

}
