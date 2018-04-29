import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Classe contenant les information et méthode relative au trésor que recherche les chasseurs.
 * @author Mathilde Siegwart et Sagona Loïc
 */
class Tresor extends Cellule {
    /**
     * Position en x du trésor.
     */
    static private int TresorX = (int)(Math.random()*Plateau.largeur);
    /**
     * Position en y du trésor.
     */
    static private int tresorY = (int)(Math.random()*Plateau.hauteur);

    /**
     * Constructeur de Trésor
     * @param x PLosition du trésor en x.
     * @param y Position du trésor en Y
     */
    public Tresor(int x, int y) {
        super(x, y, 0);
        this.id = "T";
        try {
            this.sprite = ImageIO.read(new File("./sprite/tresor.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            this.sprite = null;
            this.couleur = Color.yellow;
        }
    }

    /**
     *
     * @return La position du trésor en x
     */
    public static int getTresorX() {
        return TresorX;
    }

    /**
     *
     * @return La position du trésor en Y
     */
    public static int getTresorY() {
        return tresorY;
    }
}
