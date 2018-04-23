public class Cellule {
    static int posXTresor;
    static int posYTresor;
    private int posX;
    private int posY;
    private int distanceTresor;

    Cellule(){
    }

    Cellule(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        if(posXTresor > posX){
            if(posYTresor > posY){
                this.distanceTresor = (posXTresor-posX)+(posYTresor-posY);
            }else{
                this.distanceTresor = (posXTresor-posX)+(posY-posYTresor);
            }
        }else{
            if(posYTresor > posY){
                this.distanceTresor = (posX-posXTresor)+(posYTresor-posY);
            }else{
                this.distanceTresor = (posX-posXTresor)+(posY-posYTresor);
            }
        }
    }

    public int getDistance(){
        return distanceTresor;
    }

    public int getX(){
        return posX;
    }

    public int getY(){
        return posY;
    }
}
