import java.util.Comparator;
import java.util.PriorityQueue;

public class CollectionChasseur extends PriorityQueue<Chasseur> {
    public CollectionChasseur(int capacity, Comparator<Chasseur> comparator) {
        super(capacity, comparator);
    }
}
