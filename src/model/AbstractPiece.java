package model;

/**
 * Class abstraite d'une pièce contenant les attributs utiles à toutes les pièces
 * @author thibault.caussanel
 *
 */
public abstract class AbstractPiece implements Pieces {

	/**
	 * Coordonnée x de la pièce
	 */
	private int x;
	/**
	 * Coordonnée y de la pièce
	 */
	private int y;
	/**
	 * Couler de la pièce
	 */
	private Couleur couleur;
	
	
	/**
	 * Constructeur 
	 * @param couleur: couleur de la pièce
	 * @param coord: coordonnées de la pièce
	 */
	AbstractPiece(Couleur couleur, Coord coord){
		this.couleur=couleur;
		this.x=coord.x;
		this.y=coord.y;
	}
	
	
	public boolean move(int x,int y){
		if (this.isMoveOk(x, y)){
			this.x=x;
			this.y=y;
			return true;
		}
		else
			return false;
	}
	
	public boolean capture(){
		if(this.x!=-1 && this.y!=-1){
			this.x=-1;
			this.y=-1;
			return true;
		}
		else
			return false;
	}
	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Couleur getCouleur() {
		return couleur;
	}


	@Override
	public String toString() {
		return getClass().getSimpleName()+" [x= " + x + ", y= " + y + ", couleur= " + couleur+ "]";
	}
	
	public static void main(String[] args){
//		Pieces fou = new Fou(Couleur.BLANC , new Coord(0,0));
//		fou.move(2, 2);
//		System.out.println(fou);
//		fou.move(2, 3);
//		System.out.println(fou);
//		Pieces cavalier = new Cavalier(Couleur.NOIR, new Coord(0, 0));
//		cavalier.move(2, 1);
//		System.out.println(cavalier);
//		cavalier.move(3, 3);
//		System.out.println(cavalier);
//		cavalier.move(4, 4);
//		System.out.println(cavalier);

	}
	
}
