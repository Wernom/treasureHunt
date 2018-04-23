import java.util.Comparator;

public class Chasseur extends Cellule implements Comparator<Chasseur>{
    Chasseur(int posX, int posY) {
        super(posX, posY);
    }

    public int compare(Chasseur o1, Chasseur o2) {
        return Integer.compare(o2.getDistance(), o1.getDistance());
    }

    public void deplacer(){
        //TODO: faire le plus court chemin
    }

    public boolean deplacementValide(int newPosX, int newPosY) {
        //TODO: récupérer avec plateau
        return true;
    }
}
