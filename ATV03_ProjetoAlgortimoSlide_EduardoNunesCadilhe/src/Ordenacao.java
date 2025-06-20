public class Ordenacao {

    public void yateEPeixe(int[] dados, int fim) {
        for (int numEscolhe, aux, i = fim - 1;
             i >= 0;
             i--
        ) {
            aux = dados[i];
            numEscolhe = (int) (Math.random() * i);
            dados[i] = dados[numEscolhe];
            dados[numEscolhe] = aux;
        }
    }

}