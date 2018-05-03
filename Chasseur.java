import javafx.geometry.Point2D;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe contenant les information et méthode relative au chasseurs.
 * @author Mathilde Siegwart
 * @author Sagona Loïc
 */
public class Chasseur extends Cellule implements Comparator<Chasseur>{
    /**
     * Le nombre de Chasseur qui ne peuvent pas atteindre le trésor
     * @see CollectionChasseur#gagner()
     */
    static int coincer = 0;

    /**
     * Classe qui créée un chasseur.
     * @param posX Position en x.
     * @param posY Positin en y.
     * @param tresor Le trésor que le chasseur de trésor doit trouver.
     */
    Chasseur(int posX, int posY, Tresor tresor) {
        super(posX, posY, tresor);
        this.id = "C";
        try {
            this.sprite = ImageIO.read(new File("./sprite/chasseur.jpg"));
        } catch (IOException ex) {
            System.err.println("l'image n'à pas pu être charger");
            this.sprite = null;
            this.couleur = Color.red;
        }
    }


    public int compare(Chasseur o1, Chasseur o2) {
        return Integer.compare(o2.getDistance(), o1.getDistance());
    }


    /**
     * Déplace un chasseur sur le plateau. Vérifie également si un chemin existe.
     * @param p Le plateau de jeu.
     * @param tresor Le trésor.
     * @return Le nouveau Chasseur avec la postion mise à jour si il c'est déplacé.
     */
    public Chasseur deplacer(Plateau p, Tresor tresor){
        chemin(p, this.posX, this.posY, 0);
        int dirAleatoire = -1;
        ArrayList<Point2D> direction = new ArrayList<>();
        direction.add(new Point2D(1, 0));
        direction.add(new Point2D(-1, 0));
        direction.add(new Point2D(0, 1));
        direction.add(new Point2D(0, -1));
        Collections.shuffle(direction);
        for (int i = 0; i < direction.size(); ++i) {
            int newPosX = (int) direction.get(i).getX() + this.posX;
            int newPosY = (int) direction.get(i).getY() + this.posY;
            if (deplacementValide(newPosX, newPosY, p)) {
                dirAleatoire = i;
                if (this.distanceTresor > p.plateau[newPosX][newPosY].getDistance()) {
                    Fenetre.perdu = perdu(p, newPosX, newPosY);
                    p.plateau[this.posX][this.posY] = new Cellule(this.posX, this.posY, tresor);
                    Chasseur c = new Chasseur(newPosX, newPosY, tresor);
                    p.plateau[newPosX][newPosY] = c;
                    return c;
                }
            }
        }
        if(dirAleatoire != -1){
            int newPosX = (int) direction.get(dirAleatoire).getX() + this.posX;
            int newPosY = (int) direction.get(dirAleatoire).getY() + this.posY;
            Fenetre.perdu =  perdu(p, newPosX, newPosY);
            p.plateau[this.posX][this.posY] = new Cellule(this.posX, this.posY, tresor);
            Chasseur c = new Chasseur(newPosX, newPosY, tresor);
            p.plateau[newPosX][newPosY] = c;
            return c;
        }

        return this;
    }

    /**
     * Verifie si le chasseur peut accéder au trésor en largeur du plateau fois hauteur du plateau fois 2 coups. Si on
     * concidere que les chasseurs ne peuvent pas accèder au trésor on place la variable coincer à vrais. Si les 4 chasseurs ont cette variable à true en même temps le joueur à ganger.
     * @param p le plateau de jeu
     * @param nextX nouvelle position en x
     * @param nextY nouvelle position en y
     * @param conteur conteur du nombre de déplcement simulé
     */
    public void chemin(Plateau p, int nextX, int nextY, int conteur){

        if(conteur > Plateau.hauteur*Plateau.largeur*2){
            ++coincer;
            return;
        }

        if(perdu(p, nextX, nextY))return;

        int dirAleatoire = -1;
        ArrayList<Point2D> direction = new ArrayList<>();
        direction.add(new Point2D(1, 0));
        direction.add(new Point2D(-1, 0));
        direction.add(new Point2D(0, 1));
        direction.add(new Point2D(0, -1));
        Collections.shuffle(direction);
        for (int i = 0; i < direction.size(); ++i) {
            int newPosX = (int) direction.get(i).getX() + nextX;
            int newPosY = (int) direction.get(i).getY() + nextY;
            if(deplacementValide(newPosX, newPosY, p)) {
                dirAleatoire = i;
                if(this.distanceTresor > p.plateau[newPosX][newPosY].getDistance()) {
                    chemin(p, newPosX, newPosY, ++conteur);
                    return;
                }
            }
        }

        if(dirAleatoire != -1){
            chemin(p, (int) direction.get(dirAleatoire).getX() + nextX, (int) direction.get(dirAleatoire).getY() + nextY, ++conteur);
            return;
        }

        ++coincer;
    }

    /**
     * Verifie si la case trésor est accessible par le chasseur.
     * @param p Le plateau
     * @return true si il y a un trésor false sinon
     */
    boolean perdu(Plateau p, int x, int y){
        if(deplacementValide(x+1, y, p)) {
            if(p.plateau[x + 1][y].id.equals("T")){
                return true;
            }
        }
        if(deplacementValide(x-1, y, p)) {
            if(p.plateau[x - 1][y].id.equals("T")) {
                return true;
            }
        }
        if(deplacementValide(x, y+1, p)) {
            if(p.plateau[x][y + 1].id.equals("T")) {
                return true;
            }
        }
        if(deplacementValide(x, y-1, p)) {
            if(p.plateau[x][y - 1].id.equals("T")) {
                return true;
            }
        }

        return false;
    }


    /**
     * Vérifie si la case où l'on veut déplacer un chasseur est valide.
     * <p>On ne peut pas sortir du plateau ni rentrer en collision avec une pierre.</p>;
     * @param newPosX position en X que l'on veut accéder avec le chasseur;
     * @param newPosY position en Y que l'on veut accéder avec le chasseur.
     * @param p Le plateau de jeu.
     * @return true si la case est valide, false sinon.
     */
    public boolean deplacementValide(int newPosX, int newPosY, Plateau p) {

        if(newPosX < 0 || newPosY < 0 || newPosX >= p.plateau.length || newPosY >= p.plateau[1].length){
            return  false;
        }

        return p.plateau[newPosX][newPosY].getId().equals("_") || p.plateau[newPosX][newPosY].getId().equals("T");
    }
}
