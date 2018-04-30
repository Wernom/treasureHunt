import java.util.Comparator;

/**
 * Class permettant de comparer 2 chasseur.
 * @author Mathilde Siegwart et Sagona Loïc
 */
public class ChasseurComparator implements Comparator<Chasseur> {
    @Override
    public int compare(Chasseur o1, Chasseur o2) {
        return Integer.compare(o2.getDistance(), o1.getDistance());
    }
}
