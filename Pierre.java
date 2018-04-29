import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Classe de gestion des pierres
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Pierre extends Cellule{
    /**
     * Permet de savoir si on est en train de déplacer une pierre. true si on déplace une pierre false sinon
     */
    private static boolean isMoved = false;

    /**
     * Constructeur d'une pierre.
     * @param posX position de la pierre en x.
     * @param posY position de la pierre en y.
     */
    Pierre(int posX, int posY){
        super(posX, posY, 999999999);
        this.id = "P";
        this.couleur = Color.GRAY;
        try {
            this.sprite = ImageIO.read(new File("./sprite/pierre.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            this.sprite = null;
            this.couleur = Color.gray;
        }
    }

    /**
     * Change la valeur de isMoved
     *
     * @param ismoved Determine si on déplace une pierre.
     */
    public static void setIsMoved(boolean ismoved) {
        Pierre.isMoved = ismoved;

    }

    /**
     * Accede à la valeur isMoved
     * @return la valeur isMoved
     */
    public static boolean isIsMoved() {
        return isMoved;
    }

    /**
     * Place une pierre sur le plateau de jeu.
     * Cette méthode est appelé lors d'un clic de souris sur le plateau de jeu
     * @see Plateau
     * @param mouseX Position de la souris en x
     * @param mouseY Position de la souris en y
     * @param p Plateau de jeu
     */
    static void placerPierre(int mouseX, int mouseY, Plateau p){
        int i = (mouseX - p.getPosX())/p.plateau[0][0].getSize();
        int j = (mouseY - p.getPosY())/p.plateau[0][0].getSize();
        System.out.println("i: " + i + "j: " + j);
        p.plateau[i][j] = new Pierre(i, j);

        Main.tourJoueur = false;
    }
}
