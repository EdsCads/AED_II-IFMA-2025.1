package Questao1;

public class Main {
    public static void main(String[] args) {

        ArvBin arvore1 = new ArvBin();

        arvore1.inserir(50);
        arvore1.inserir(80);
        arvore1.inserir(60);
        arvore1.inserir(30);
        arvore1.inserir(55);
        arvore1.inserir(20);
        arvore1.inserir(0);
        arvore1.imprimir();
        System.out.println("Temos "+arvore1.contaFilho()+" nós com no minimo 1 filho");
    }
}

