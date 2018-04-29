import javax.swing.*;
import java.awt.*;


/**
 * Classe contenant les information et les méthode relative au plateau de jeu
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Plateau extends JPanel{
    /**
     * Position du plateau en x.
     */
    private int posX;
    /**
     * Position du plateau en Y
     */
    private int posY;
    /**
     * Hauteur du plateau.
     */
    static final int hauteur = 5;
    /**
     * Largeur du plateau.
     */
    static final int largeur = 5;

    /**
     * Tableau conteant les cellules du plateau.
     */
    Cellule[][] plateau;

    /**
     * Constructeur du plateau
     * @param tresor Le trésor
     * @param listeChasseur La liste des chasseurs
     */
    public Plateau (Tresor tresor, CollectionChasseur listeChasseur){
        if(hauteur*largeur-1 < Main.nb_chasseur){
            return;
        }

        this.posX = this.getHeight()/10;
        this.posY = this.getWidth()/10;
        plateau = new Cellule[largeur][hauteur];
        plateau[tresor.getX()][tresor.getY()] = tresor;
        int posXChasseur = 0;
        int posYChasseur = 0;
        int distance = 0;

        for(int i = 0; i < largeur; ++i){
            for(int j = 0; j < hauteur; ++j){
                if(plateau[i][j] == null){
                    plateau[i][j] = new Cellule(i,j, tresor);
                }
            }
        }

        for(int i = 0; i < Main.nb_chasseur; ++i){
            do{
                posXChasseur = (int)(Math.random()*largeur);
                posYChasseur = (int)(Math.random()*hauteur);
                distance = Math.abs(posXChasseur-tresor.getX())+Math.abs(posYChasseur-tresor.getY());
            }while (!plateau[posXChasseur][posYChasseur].id.equals("_") || distance < 5);
            Chasseur c1 = new Chasseur(posXChasseur, posYChasseur, tresor);
            plateau[posXChasseur][posYChasseur] = c1;
            listeChasseur.add(c1);
        }
    }

    /**
     * Permet de dessiner le plateau de jeu dans la fenetre
     * @see Graphics
     */
    public void paintComponent(Graphics g){
        posX = this.getWidth()/2 -this.plateau.length*this.plateau[0][0].getSize()/2;
        posY = this.getHeight()/2 - this.plateau[0].length*this.plateau[0][0].getSize()/2;

        for (int i = 0; i < this.plateau.length; ++i){
            for(int j = 0; j < this.plateau[0].length; ++j){
                g.setColor(Color.BLACK);
                g.drawString(Main.tourJoueur ? "A VOTRE TOUR": "AU TOUR DES CHASSEURS DE TRESOR", 10, 20);
                if(this.plateau[j][i].sprite == null){
                    g.setColor(this.plateau[j][i].couleur);
                    g.fillRect(posX+j*this.plateau[j][i].getSize(), posY+i*this.plateau[j][i].getSize(), this.plateau[j][i].getSize(), this.plateau[j][i].getSize());
                }else{
                    g.drawImage(this.plateau[j][i].sprite, posX+j*this.plateau[j][i].getSize(), posY+i*this.plateau[j][i].getSize(), this.plateau[j][i].getSize(), this.plateau[j][i].getSize(), this);
                }
            }
        }
    }

    /**
     * @return La position en x du plateau.
     */
    public int getPosX() {
        return posX;
    }


    /**
     * @return La position en Y du plateau.
     */
    public int getPosY() {
        return posY;
    }
}
