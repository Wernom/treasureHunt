import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Classe contenant les information et méthode relative aux cellule, les cellule son toute les cases du plateau de jeu
 * elle peuvent reprétenter un chasseur, un trésor ou une pierre, si elle ne représente rien elles sont concideré comme
 * des case n'étant pas occupé et donc les chasseurs peuvent si déplacer et les pierres peuvent y être posé.
 * @see Chasseur
 * @see Tresor
 * @see Pierre
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Cellule{
    /**
     * Position en x de la cellule.
     */
    protected int posX;
    /**
     * Position en y de la cellule.
     */
    protected int posY;
    /**
     * Taille d'une cellule.
     */
    static private int size;
    /**
     * Distance d'une celluel au trésor.
     */
    protected int distanceTresor;
    /**
     * Identifiant de la cellule
     * Les identifiant sont :
     * <ul>
     *     <li>T: Trésor</li>
     *     <li>_: Cellule vide</li>
     *     <li>P:Pierre</li>
     *     <li>C: Chasseur</li>
     * </ul>
     */
    protected String id;
    /**
     * Couleur d'une cellule est utilisé uniquement si une image n'a pas pu etre chargé.
     * Les couleurs sont :
     * <ul>
     *     <li>Trésor: Jaune</li>
     *     <li>Cellule: blanche</li>
     *     <li>Chasseur: rouge</li>
     *     <li>Pierre: gris</li>
     * </ul>
     */
    protected Color couleur;
    /**
     * Image de la cellule qui sera affiché dans la fenetre.
     */
    protected BufferedImage sprite;

    /**
     * Constructeur d'une cellule.
     * @param posX position de la cellule en x
     * @param posY position de la cellule en y
     */
    private Cellule(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.id = "_";
        this.size = 50;
        try {
            this.couleur = null;
            this.sprite = ImageIO.read(new File("sprite/cellule.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            this.sprite = null;
            this.couleur = Color.white;
        }
    }

    /**
     * Créée une cellule avec la distance au trésor.
     * @param posX position de la cellule en x
     * @param posY position de la cellule en y
     * @param distanceTresor La distance au trésor
     */
    Cellule(int posX, int posY, int distanceTresor){
        this(posX, posY);
        this.distanceTresor = distanceTresor;
    }

    /**
     * Créée une Cellule en calculant la distance u trésor
     * @param posX position de la cellule en x
     * @param posY position de la cellule en y
     * @param tresor Le trésor.
     */
    Cellule(int posX, int posY, Tresor tresor){
        this(posX, posY);
        this.distanceTresor = Math.abs(tresor.posX -posX)+Math.abs(tresor.posY -posY);

    }


    /**
     *
     * @return la distance au trésor.
     */
    public int getDistance(){
        return distanceTresor;
    }

    /**
     *
     * @return L'identifiant d'une cellule
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return La position en x.
     */
    public int getX(){
        return posX;
    }

    /**
     *
     * @return La positin en y
     */
    public int getY(){
        return posY;
    }

    /**
     *
     * @return La taille d'une cellule.
     */
    static public int getSize() {
        return size;
    }
}
