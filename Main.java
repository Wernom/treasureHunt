/**
 * Programme Principale du jeu
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class Main{

    static final int nb_chasseur = 1;
    static int tour = 10;
    static boolean tourJoueur = false;
    static boolean perdu = false;

    public static void main(String[] args) {
        Tresor tresor = new Tresor(Tresor.getTresorX(),Tresor.getTresorY());

        CollectionChasseur listeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
        Plateau plateau = new Plateau(tresor, listeChasseur);
        Fenetre f = new Fenetre(plateau);
        f.draw(plateau);
        Chasseur chasseurActif;

        int i = 0;
        while(i < tour){
            if(!tourJoueur){
                i++;
                CollectionChasseur newListeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
                while ((chasseurActif = listeChasseur.poll()) != null){
                    newListeChasseur.add(chasseurActif.deplacer(plateau, tresor));
                    System.out.println("iscoincé: " + Chasseur.coincer);
                    f.draw(plateau);
                }
                listeChasseur = newListeChasseur;
                if(listeChasseur.gagner()) {
                    System.out.println("GAGNER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.exit(0);
                }

                if(perdu){
                    System.out.println("PERDU!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.exit(1);
                }
                tourJoueur = true;

            }
            f.draw(plateau);
            Chasseur.coincer = 0;

        }
        f.draw(plateau);
    }
}


