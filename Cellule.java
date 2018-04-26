public class Cellule {
    protected int posX;
    protected int posY;
    protected int distanceTresor;
    protected char id;

    Cellule(){
    }
    Cellule(int posX, int posY, int distanceTresor){
        this.posX = posX;
        this.posY = posY;
        this.id = '_';
        this.distanceTresor = distanceTresor;
    }
    Cellule(int posX, int posY, Tresor tresor){
        this.posX = posX;
        this.posY = posY;
        this.id = '_';
        this.distanceTresor = Math.abs(tresor.posX-posX)+Math.abs(tresor.posY-posY);
    }

    public int getDistance(){
        return distanceTresor;
    }

    public char getId() {
        return id;
    }

    public int getX(){
        return posX;
    }

    public int getY(){
        return posY;
    }
}
