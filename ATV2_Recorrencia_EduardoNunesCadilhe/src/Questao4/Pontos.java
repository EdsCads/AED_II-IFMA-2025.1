package Questao4;

public class Pontos {
    int x;
    int y;

    public Pontos(int posX,int posY){
        x=posX;
        y=posY;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Pontos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
