/**
 * Programme Principale du jeu
 *
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Main {

    static int nb_chasseur = 3;
    static boolean tourJoueur = false;
    static boolean perdu = false;
    static boolean gagner = false;
    static private boolean fin = false;

    public static void setFin() {
        fin = true;
    }

    public static void main(String[] args) {
        Tresor tresor = new Tresor();

        CollectionChasseur listeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
        Plateau plateau = new Plateau(tresor, listeChasseur);
        Fenetre f = new Fenetre(plateau);
        f.drawJeu(plateau);
        Chasseur chasseurActif;
        int i = 0;
        while(true) {
            while (!fin) {
                if (!tourJoueur) {
                    i++;
                    CollectionChasseur newListeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
                    while ((chasseurActif = listeChasseur.poll()) != null) {
                        newListeChasseur.add(chasseurActif.deplacer(plateau, tresor));
                        f.drawJeu(plateau);
                    }
                    listeChasseur = newListeChasseur;
                    if (listeChasseur.gagner()) {
                        Main.gagner = true;
                        fin = !fin;
                    }

                    if (perdu) {
                        fin = !fin;
                    }
                    tourJoueur = true;
                }
                f.drawJeu(plateau);
                Chasseur.coincer = 0;
            }
            f.dispose();
            Pierre.setNbPierre(0);
            fin = false;
            gagner = false;
            perdu = false;
            tourJoueur = false;
            tresor = new Tresor();
            listeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
            plateau = new Plateau(tresor, listeChasseur);
            f = new Fenetre(plateau);
            f.drawJeu(plateau);
        }

    }
}


