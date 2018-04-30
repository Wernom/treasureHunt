import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Classe contenant les information et méthode relative au trésor que recherche les chasseurs.
 *
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Tresor extends Cellule{
    /**
     * Position en x du trésor.
     */
    static private int tresorX;
    /**
     * Position en y du trésor.
     */
    static private int tresorY;

    /**
     * Image du trésor qui sera affiché quand la partie sera termine
     */
    static private BufferedImage spriteTresorDevoile;

    /**
     * Couleur du trésor qui sera affiché quand la partie sera termine
     */
    static private Color couleurTresorDevoile;

    /**
     * Constructeur de Trésor
     *
     */
    public Tresor() {
        super((int) (Math.random() * Plateau.largeur), (int) (Math.random() * Plateau.hauteur), 0);
        this.id = "T";
        try {
            this.sprite = ImageIO.read(new File("./sprite/cellule.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            this.sprite = null;
            this.couleur = Color.white;
        }

        try {
            spriteTresorDevoile = ImageIO.read(new File("./sprite/tresor.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            spriteTresorDevoile = null;
            couleurTresorDevoile = Color.yellow;
        }
    }

    public static BufferedImage getSpriteTresorDevoile() {
        return spriteTresorDevoile;
    }

    public static Color getCouleurTresorDevoile() {
        return couleurTresorDevoile;
    }

    /**
     * @return La position du trésor en x
     */
    public static int getTresorX() {
        return tresorX;
    }

    /**
     * @return La position du trésor en Y
     */
    public static int getTresorY() {
        return tresorY;
    }
}
