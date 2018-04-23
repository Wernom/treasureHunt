public class Cellule {
    private int posX;
    private int posY;
    private int distanceTresor;

    Cellule(){
    }

    Cellule(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
        if(Plateau.posXTresor > posX){
            if(Plateau.posYTresor > posY){
                this.distanceTresor = (Plateau.posXTresor-posX)+(Plateau.posYTresor-posY);
            }else{
                this.distanceTresor = (Plateau.posXTresor-posX)+(posY-Plateau.posYTresor);
            }
        }else{
            if(Plateau.posYTresor > posY){
                this.distanceTresor = (posX-Plateau.posXTresor)+(Plateau.posYTresor-posY);
            }else{
                this.distanceTresor = (posX-Plateau.posXTresor)+(posY-Plateau.posYTresor);
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
