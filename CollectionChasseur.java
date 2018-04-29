import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Classe permettant de stocker les chasseur dans l'ordre ou ils ils doivent jouer
 */
public class CollectionChasseur extends PriorityQueue<Chasseur> {
    /**
     * Constructeur de Collection de chasseur.
     * @param capacity Nombre de chasseurs.
     * @param comparator La classe qui permet de comparer les chasseurs.
     */
    public CollectionChasseur(int capacity, Comparator<Chasseur> comparator) {
        super(capacity, comparator);
    }

    /**
     * Vérifie si tout les chasseurs sont coincé.
     * @return true la partie est gangé, false la partie continue.
     */
    boolean gagner(){
        if(Chasseur.coincer == Main.nb_chasseur)return true;
        return false;
    }
}
