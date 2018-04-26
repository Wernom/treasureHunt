import javafx.geometry.Point2D;
import javafx.geometry.Pos;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Chasseur extends Cellule implements Comparator<Chasseur>{
    Chasseur(int posX, int posY, Tresor tresor) {
        super(posX, posY, tresor);
        this.id = 'C';
    }

    public int compare(Chasseur o1, Chasseur o2) {
        return Integer.compare(o2.getDistance(), o1.getDistance());
    }

    public Chasseur deplacer(Plateau p, Tresor tresor){
        ArrayList<Point2D> direction = new ArrayList<Point2D>();
        direction.add(new Point2D(1, 1));
        direction.add(new Point2D(-1, 1));
        direction.add(new Point2D(1, -1));
        direction.add(new Point2D(-1, -1));
        Collections.shuffle(direction);
        for(int i = 0; i < direction.size(); ++i) {
            int newPosX = (int)direction.get(i).getX()+this.posX;
            int newPosY = (int)direction.get(i).getY()+this.posY;
            if (deplacementValide(newPosX,newPosY, p)){
                if(this.distanceTresor > p.plateau[newPosX][newPosY].getDistance()){
                    p.plateau[this.posX][this.posY] = new Cellule(this.posX, this.posY, tresor);
                    Chasseur c = new Chasseur(newPosX, newPosY, tresor);
                    p.plateau[newPosX][newPosY] = c;
                    return c;
                }
            }
        }
        return this;
    }

    public boolean deplacementValide(int newPosX, int newPosY, Plateau p) {

        if(newPosX < 0 || newPosY < 0 || newPosX >= p.plateau.length || newPosY >= p.plateau[1].length){
            return  false;
        }

        if(p.plateau[newPosX][newPosY].getId() == '_'){
            return true;
        }
        return false;
    }
}
