/**
 * Programme Principale du jeu
 *
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Main {



    /*public static void setFin() {
        fin = true;
    }*/

    public static void main(String[] args) {

        Fenetre f = new Fenetre();

        //f.drawJeu(plateau);
        //Chasseur chasseurActif;
        int i = 0;
/*        while(true) {
            while (!fin) {
                if (!tourJoueur) {
                    i++;
                    CollectionChasseur newListeChasseur = new CollectionChasseur(nbChasseur, new ChasseurComparator());
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
            listeChasseur = new CollectionChasseur(nbChasseur, new ChasseurComparator());
            plateau = new Plateau(tresor, listeChasseur);
            f = new Fenetre(plateau);
            f.drawJeu(plateau);
        }*/

    }
}


