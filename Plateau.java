public class Plateau {
    Cellule[][] plateau;
    static int posXTresor = 0;
    static int posYTresor = 0;
    public Plateau (){
        if(Main.hauteur*Main.largeur-1 < Main.nb_chasseur){
            return;
        }
        posXTresor = (int)(Math.random()*Main.largeur);
        posYTresor = (int)(Math.random()*Main.hauteur);
        plateau[posXTresor][posYTresor] = new Tresor(posXTresor, posXTresor);
        int posXChasseur = 0;
        int posYChasseur = 0;
        for(int i = 0; i < Main.nb_chasseur; ++i){
            do{
                posXChasseur = (int)(Math.random()*Main.largeur);
                posYChasseur = (int)(Math.random()*Main.hauteur);
            }while (posXTresor == posXChasseur && posYTresor == posYChasseur);
            plateau[posXChasseur][posYChasseur] = new Chasseur(posXChasseur, posXChasseur);
        }
        for(int i = 0; i < Main.largeur; ++i){
            for(int j = 0; j < Main.hauteur; ++j){
                if(plateau[i][j] == null){
                    plateau[i][j] = new Cellule(i,j);
                }
            }
        }
    }
}
