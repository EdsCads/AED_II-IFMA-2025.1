package ifma.AVL;


import ifma.data.InputData;

import java.util.List;

public class Main {
    public static void main(String[] args){
        ArvoreAvl arvore = new ArvoreAvl ();
        List<Integer> valores = InputData.readFromFile("input.txt");

        for (Integer valor : valores) {
            arvore.add(valor);
        }

        arvore.amostra();

    }
}