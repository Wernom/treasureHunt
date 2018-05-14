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
    private JPanel jeu;
    private JPanel menu;
    private JPanel option;

    private boolean premierePartie = true;

    static int nbChasseur = 3;
    static boolean tourJoueur;
    static boolean perdu;
    static boolean gagner;
    static private boolean fin;
    Tresor tresor;
    CollectionChasseur listeChasseur;
    Plateau plateau;
    JPanel bot = new JPanel();
    /**
     * Hauteur du plateau entré par l'utilisateur.
     */
    protected int hauteur;
    /**
     * Largeur du plateau entré par l'utilisateur.
     */
    int largeur;
    /**
     * Nombre de chasseur entré par l'utilisateur.
     */
    int NbChasseur;



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

        //boutonMenu();
        createMenuScreen();
        //initialise();
        //Initialisation variable de jeu
        setVisible(true);


    }


    /**
     * Ajoute des boutons à la fenetre :
     *                      - Abondonner qui permet de quitter la partie
     *                      - Passer son tour qui permet de passer son tour
     */
    void boutonMenu(){
        JButton jouer = new JButton("Jouer");
        jouer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boutonJeu();//TODO: ca marche pas
                jeu();
                bot.setBackground(Color.green);
                espaceJeu.add(bot, BorderLayout.SOUTH);
            }
        });
        menu.add(jouer);

        JButton option = new JButton("Options");
        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        boutonQuitter(menu);
    }

    private void boutonQuitter(JPanel pan){
        JButton quitter = new JButton("Quitter");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pan.add(quitter);
    }

    private void boutonJeu(){
        JButton recommencer = new JButton("Recommencer");
        recommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialise();
            }
        });
        bot.add(recommencer);

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
        bot.revalidate();
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

    private void createMenuScreen(){
        menu = new JPanel();
        option = new JPanel();
        boutonMenu();
        JLabel longueurLabel = new JLabel("Longueur : ");
        Form longueur = new Form();



        JLabel largeurLabel = new JLabel("Largeur : ");
        JTextField largeur = new JTextField(5);
        JLabel chasseurLabel = new JLabel("Nombre de chasseurs : ");
        JTextField nbChasseur = new JTextField("4");
        JLabel pierreLabel = new JLabel("Nombre de Pierre : ");
        JTextField nbPierre = new JTextField();
        option.add(largeurLabel);
        option.add(largeur);
        option.add(longueurLabel);
        option.add(longueur);
        option.add(chasseurLabel);
        option.add(nbChasseur);
        option.add(pierreLabel);
        option.add(nbPierre);

        JButton b = new JButton()


        espaceJeu.add(menu, BorderLayout.CENTER);
        espaceJeu.add(option, BorderLayout.EAST);
    }



}

