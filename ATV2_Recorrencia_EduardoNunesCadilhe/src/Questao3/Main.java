package Questao3;

public class Main {
    public static void main(String[] args) {

        mostraValorAteMaximo(100,3500);
    }

    public static void  mostraValorAteMaximo(int valor, int maximo){
        System.out.println(valor);
        multiplicaValor(valor,maximo,1);
        System.out.println(valor);
    }

    public static void multiplicaValor(int valor, int maximo, int multiplica){
        int resultado = valor*(5*multiplica);
        if(resultado<=maximo){
            System.out.println(resultado);
            multiplicaValor(valor,maximo,1+multiplica);
            System.out.println(resultado);
        }
    }
}

