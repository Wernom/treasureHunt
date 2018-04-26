public class Main {
    static final int hauteur = 10;
    static final int largeur = 10;
    static final int nb_chasseur = 4;
    static final int nb_pierre = 10;
    public static void main(String[] args) {
        Tresor tresor = new Tresor(1,1);

        CollectionChasseur listeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
        Plateau plateau = new Plateau(tresor, listeChasseur);
        affiche(plateau);
        Chasseur c;
        CollectionChasseur newListeChasseur = new CollectionChasseur(nb_chasseur, new ChasseurComparator());
        while ((c = listeChasseur.poll()) != null){
            newListeChasseur.add(c.deplacer(plateau, tresor));
        }
        System.out.println();
        affiche(plateau);
    }

    static void affiche (Plateau plateau){
        for(int i = 0; i < hauteur; ++i){
            for(int j = 0; j < largeur; ++j){
                System.out.print(plateau.plateau[j][i].getId());
            }
            System.out.println();
        }
    }

}


