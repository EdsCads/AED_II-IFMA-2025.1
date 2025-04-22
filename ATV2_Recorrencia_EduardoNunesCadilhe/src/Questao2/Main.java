package Questao2;

import Questao1.ArvBin;

public class Main {
    public static void main(String[] args) {

        ListaLigada lista1 = new ListaLigada();

        lista1.inserir(50);
        lista1.inserir(80);
        lista1.inserir(60);
        lista1.inserir(30);
        lista1.inserir(55);
        lista1.inserir(20);
        lista1.inserir(0);
        lista1.ordemReversa();
    }
}

