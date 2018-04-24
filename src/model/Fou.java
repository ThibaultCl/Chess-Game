package model;

/**
 * Class définissant une Piece Fou
 * @author thibault.caussanel
 *
 */
public class Fou extends AbstractPiece{

	public Fou(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		return (Math.abs(this.getX()-xFinal)==Math.abs(this.getY()-yFinal));
	}

	

}
