import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe permettant l'affichage de la fenetre de jeu.
 */
public class Fenetre extends JFrame{
    /**
     * Container de la fenetre de jeu.
     */
    private Container espaceJeu = getContentPane();
    private boolean premierePartie = true;

    static int nbChasseur = 3;
    static boolean tourJoueur;
    static boolean perdu;
    static boolean gagner;
    static private boolean fin;
    Tresor tresor;
    CollectionChasseur listeChasseur;
    Plateau plateau;

    /**
     * Initialise la fenetre de jeu.
     * Ajoute notamment un nouvelle MouseAdapter qui quand on clic dans la zone de jeu permet de placer ou déplacer une pierre.
     */
    Fenetre(){
        this.setSize(900, 700);
        setTitle("Treasure HUNT");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        espaceJeu.setLayout(new BorderLayout());
        espaceJeu.setBackground(Color.BLUE);


        JPanel bot = new JPanel();
        bot.setBackground(Color.green);
        espaceJeu.add(bot, BorderLayout.SOUTH);
        boutonMenu(bot);
        initialise();
        //Initialisation variable de jeu
        setVisible(true);


    }


    /**
     * Ajoute des boutons à la fenetre :
     *                      - Abondonner qui permet de quitter la partie
     *                      - Passer son tour qui permet de passer son tour
     */
    void boutonMenu(JPanel bot){
        JButton jouer = new JButton("Jouer");
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jeu();
                boutonJeu(bot);//TODO: ca marche pas
            }
        });
        bot.add(jouer);

        boutonQuitter(bot);
    }

    private void boutonQuitter(JPanel bot){
        JButton quitter = new JButton("Quitter");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bot.add(quitter);
    }

    private void boutonJeu(JPanel bot){
        JButton recommencer = new JButton("Recommencer");
        recommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialise();
            }
        });

        JButton passer = new JButton("Passer son tour");
        passer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(tourJoueur) {
                    tourJoueur = !tourJoueur;
                }
            }
        });
        bot.add(passer);
    }


    /**
     *
     */
    void tourJeu(){
        Chasseur chasseurActif;
        CollectionChasseur newListeChasseur = new CollectionChasseur(nbChasseur, new ChasseurComparator());

        while ((chasseurActif = listeChasseur.poll()) != null) {
            newListeChasseur.add(chasseurActif.deplacer(plateau, tresor));
            plateau.repaint();
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }*/
        }
        listeChasseur = newListeChasseur;
        if (listeChasseur.gagner()) {
            gagner = true;
            fin = !fin;
        }

        if (perdu) {
            fin = !fin;
        }
        tourJoueur = true;
        Chasseur.coincer = 0;
    }

    /**
     *
     */
    private void setFin() {
        fin = true;
    }

    private void initialise(){
        tourJoueur = false;
        perdu = false;
        gagner = false;
        fin = false;
        tresor = new Tresor();
        listeChasseur = new CollectionChasseur(nbChasseur, new ChasseurComparator());
        plateau = new Plateau(tresor, listeChasseur);
        espaceJeu.add(plateau, BorderLayout.CENTER);
    }


    private void jeu(){
        tourJeu();
        plateau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getX() > plateau.getPosX()  && e.getX() < getWidth() - plateau.getPosX() && e.getY() > plateau.getPosY() && e.getY() < getHeight() - plateau.getPosY() && !gagner && !perdu){
                    int i = (e.getX() - plateau.getPosX())/ Cellule.getSize();
                    int j = (e.getY() - plateau.getPosY())/ Cellule.getSize();
                    if(!plateau.plateau[i][j].getId().equals("C") && Pierre.isIsMoved()){
                        Pierre.setIsMoved(false);
                        Pierre.placerPierre(e.getX(), e.getY(), plateau);
                        Pierre.setNbPierre(Pierre.getNbPierre()+1);
                    }else if(plateau.plateau[i][j].getId().equals("P")){
                        Pierre.setIsMoved(true);
                        Pierre.setNbPierre(Pierre.getNbPierre()-1);
                        plateau.plateau[i][j] = new Cellule(i, j, Math.abs(Tresor.getTresorX() -i)+Math.abs(Tresor.getTresorY() -j));
                        plateau.repaint();
                    }else if(!plateau.plateau[i][j].getId().equals("C") && Pierre.getNbPierre() < Pierre.getNbPierreMax()) {
                        Pierre.placerPierre(e.getX(), e.getY(), plateau);
                        Pierre.setNbPierre(Pierre.getNbPierre()+1);
                    }

                    if(!tourJoueur)
                        tourJeu();
                }
            }
        });
    }
}

