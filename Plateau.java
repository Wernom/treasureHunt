public class Plateau {
    Cellule[][] plateau;
    public Plateau (Tresor tresor, CollectionChasseur listeChasseur){

        if(Main.hauteur*Main.largeur-1 < Main.nb_chasseur){
            return;
        }
        plateau = new Cellule[Main.largeur][Main.hauteur];
//        posXTresor = (int)(Math.random()*Main.largeur);
//        posYTresor = (int)(Math.random()*Main.hauteur);
        plateau[tresor.getX()][tresor.getY()] = tresor;
        int posXChasseur = 0;
        int posYChasseur = 0;
        int distance = 0;

        for(int i = 0; i < Main.nb_chasseur; ++i){
            do{
                posXChasseur = (int)(Math.random()*Main.largeur);
                posYChasseur = (int)(Math.random()*Main.hauteur);
                distance = Math.abs(posXChasseur-tresor.getX())+Math.abs(posYChasseur-tresor.getY());
            }while (plateau[posXChasseur][posYChasseur] != null || distance < 5);
            Chasseur c1 = new Chasseur(posXChasseur, posYChasseur, tresor);
            plateau[posXChasseur][posYChasseur] = c1;
            listeChasseur.add(c1);
        }

        for(int i = 0; i < Main.largeur; ++i){
            for(int j = 0; j < Main.hauteur; ++j){
                if(plateau[i][j] == null){
                    plateau[i][j] = new Cellule(i,j, tresor);
                }
            }
        }

    }
}
