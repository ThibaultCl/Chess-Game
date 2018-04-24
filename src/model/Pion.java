package model;
/**
 * Class définissant la pièce Pion
 * @author thibault.caussanel
 *
 */
public class Pion extends AbstractPiece{
	/**
	 * Constructeur de Pion
	 * @param couleur
	 * @param coord
	 */
	public Pion(Couleur couleur, Coord coord) {
		super(couleur, coord);
	}

	@Override
	public boolean isMoveOk(int xFinal, int yFinal) {
		/*
		 * TODO: déplacement de une case uniquement, pas de prise en compte du déplacement de 2 cases au premier tour
		 */
		return (this.getX()==xFinal && this.getY()==yFinal+1)||(this.getX()==xFinal && this.getY()==yFinal-1);
	}

}
