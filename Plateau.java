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
    static final int hauteur = 10;
    /**
     * Largeur du plateau.
     */
    static final int largeur = 10;

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

        this.posX = this.getHeight()/10;
        this.posY = this.getWidth()/10;
        plateau = new Cellule[largeur][hauteur];
        plateau[tresor.getX()][tresor.getY()] = tresor;
        int posXChasseur;
        int posYChasseur;
        int distance;

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
        posX = this.getWidth()/2 -this.plateau.length*Cellule.getSize()/2;
        posY = this.getHeight()/2 - this.plateau[0].length*Cellule.getSize()/2;
        g.setColor(Color.black);
        g.drawString("Il vous reste "+(Pierre.getNbPierreMax() - Pierre.getNbPierre())+" pierre(s)", 10, 30);
        for (int i = 0; i < this.plateau.length; ++i){
            for(int j = 0; j < this.plateau[0].length; ++j){
                g.setColor(Color.BLACK);
                g.drawString(Main.tourJoueur ? "A VOTRE TOUR": "AU TOUR DES CHASSEURS DE TRESOR", 10, 20);
                if(this.plateau[j][i].sprite == null){
                    if((Main.gagner || Main.perdu) && this.plateau[j][i].id.equals("T"))
                        g.setColor(Tresor.getCouleurTresorDevoile());
                    else
                        g.setColor(this.plateau[j][i].couleur);

                    g.fillRect(posX+j*Cellule.getSize(), posY+i*Cellule.getSize(), Cellule.getSize(), Cellule.getSize());
                }else{
                    if((Main.gagner || Main.perdu) && this.plateau[j][i].id.equals("T"))
                        g.drawImage(Tresor.getSpriteTresorDevoile(), posX+j*Cellule.getSize(), posY+i*Cellule.getSize(), Cellule.getSize(), Cellule.getSize(), this);
                    else
                        g.drawImage(this.plateau[j][i].sprite, posX+j*Cellule.getSize(), posY+i*Cellule.getSize(), Cellule.getSize(), Cellule.getSize(), this);
                }
            }
        }

        if(Main.gagner){
            g.setColor(Color.green);
            drawGagne(g);
        }else if(Main.perdu){
            g.setColor(Color.red);
            drawPerdu(g);
        }
    }

    /**
     * Affiche "PERDU!!!!!!!" sur l'écran à des position aléatoire 20 fois.
     * @see Graphics
     */
    void drawPerdu(Graphics g){
        for(int i =0; i <20; ++i){
            g.drawString("PERDU!!!!!!!", (int) (Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()));
        }
    }

    /**
     * Affiche "GAGNE!!!!!!!" sur l'écran à des position aléatoire 20 fois.
     * @see Graphics
     */
    void drawGagne(Graphics g){
        for(int i =0; i <20; ++i){
            g.drawString("GAGNE!!!!!!!", (int) (Math.random()*this.getWidth()),(int)(Math.random()*this.getHeight()));
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
