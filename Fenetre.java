import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe permettant l'affichage de la fenetre de jeu.
 */
public class Fenetre extends JFrame{
    /**
     * Container de la fenetre.
     */
    Container espace_jeu = getContentPane();

    /**
     * Initialise la fenetre de jeu.
     * Ajoute notamment un nouvelle MouseAdapter qui quand on clic dans la zone de jeu permet de placer ou déplacer une pierre.
     * @param p Le plateau de jeu
     */
    Fenetre(Plateau p){
        this.setSize(900, 700);
        setTitle("Treasure HUNT");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        espace_jeu.setLayout(new BorderLayout());
        espace_jeu.setBackground(Color.BLUE);
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//TODO: implementer un nombre max de pierre.
                if(e.getX() > p.getPosX()  && e.getX() < getWidth() - p.getPosX() && e.getY() > p.getPosY() && e.getY() < getHeight() - p.getPosY() && Main.tourJoueur){
                    int i = (e.getX() - p.getPosX())/p.plateau[0][0].getSize();
                    int j = (e.getY() - p.getPosY())/p.plateau[0][0].getSize();
                    if(Pierre.isIsMoved()){
                        Pierre.setIsMoved(false);
                        Pierre.placerPierre(e.getX(), e.getY(), p);
                    }else if(p.plateau[i][j].getId().equals("_"))
                        Pierre.placerPierre(e.getX(), e.getY(), p);
                    else if(p.plateau[i][j].getId().equals("P")){
                        Pierre.setIsMoved(true);
                        p.plateau[i][j] = new Cellule(i, j, Math.abs(Tresor.getTresorX() -i)+Math.abs(Tresor.getTresorY() -j));
                    }
                }
            }
        });

        bouton(espace_jeu);

    }

    /**
     * Ajoute des boutons à la fenetre :
     *                      - Abondonner qui permet de quitter la partie
     *                      - Passer son tour qui permet de passer son tour
     * @param espace_jeu Le Container de la fenetre.
     */
    void bouton (Container espace_jeu){
        JPanel bot = new JPanel();
        bot.setBackground(Color.green);
        espace_jeu.add(bot, BorderLayout.SOUTH);
        JButton quitter = new JButton("Abandonner");
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        bot.add(quitter);
    }

    /**
     * Dessine le plateau.
     * @param p Le plateau de jeu.
     */
    void draw(Plateau p){
        espace_jeu.add(p, BorderLayout.CENTER);
        long t1 = System.currentTimeMillis();
        while(System.currentTimeMillis()<t1+1000)
        espace_jeu.repaint();
    }
}

