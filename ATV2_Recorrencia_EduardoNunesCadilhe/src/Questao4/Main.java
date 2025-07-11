package Questao4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]){
        ArrayList<Pontos> pontos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./src\\Tools\\Pontos_Entrada.txt"))) {
            while(br.ready()){
                String linhaAtual[] = br.readLine().split(";");
                pontos.add(new Pontos(Integer.parseInt(linhaAtual[0]),Integer.parseInt(linhaAtual[1])));
            }

            pontos.sort((p1, p2) -> Double.compare(p1.x, p2.x));

            System.out.printf("\nA menor distância entre dois pontos no arquivo é: %.6f\n",Math.sqrt(menorDistance(pontos)));

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public static double menorDistance(List<Pontos> ponto){

        if(ponto.size()<=3){ return calcula(ponto,Double.POSITIVE_INFINITY);}

        int meio = ponto.size()/2;

        menorDistance(ponto.subList(0,meio));
        menorDistance(ponto.subList(meio,ponto.size()));

        return calcula(ponto,Double.POSITIVE_INFINITY);
    }

    private static double calcula(List<Pontos> pontos,double minDist) {
        if (pontos.size() == 1) {
            return minDist;
        }
        double dx = pontos.get(0).getX()-pontos.get(1).getX();
        double dy = pontos.get(0).getY()-pontos.get(1).getY();
        double menorDist =(dx*dx + dy*dy);
        for (int i = 0; i < pontos.size(); ++i) {
            for (int j = i + 1; j < pontos.size(); ++j) {
                dx = pontos.get(i).getX()-pontos.get(j).getX();
                dy = pontos.get(i).getY()-pontos.get(j).getY();
                double temp =(dx*dx + dy*dy);

                if(temp<=menorDist){
                    menorDist=temp;
                }

            }
        }

        return menorDist;
    }



}
