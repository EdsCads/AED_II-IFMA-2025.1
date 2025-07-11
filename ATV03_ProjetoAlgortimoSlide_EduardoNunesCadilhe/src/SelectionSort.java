public class SelectionSort {
    private int aux = 0;
    private int dadosAux;
    public SelectionSort ( int[] dados){

        for (int i = 0; i < dados.length; i++) {
            for (int j = i + 1; j < dados.length; j++) {
                if (dados[i] > dados[j]) {
                    aux = j;
                    dadosAux = dados[i];
                    dados[i] = dados[aux];
                    dados[aux] = dadosAux;
                }
            }
        }

    }
}
