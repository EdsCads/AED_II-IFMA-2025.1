package Main;

public class Sort {

    public static void bubbleSort(int[] dados){
        int dadosAux=0;
        for(int i=0;i< dados.length-1;i++){
            for(int j=0;j<dados.length-1-i;j++){
                if(dados[j]>dados[j+1]){
                    dadosAux = dados[j];
                    dados[j] = dados[j+1];
                    dados[j+1] = dadosAux;
                }
            }
        }
    }

    public static void selecaoSort(int[] dados) {
        int aux = 0;
        int dadosAux;
        for (int i = 0; i < dados.length; i++) {
            for (int j = i + 1; j < dados.length; j++) {
                if (dados[i] > dados[j]){
                    aux = j;
                    dadosAux = dados[i];
                    dados[i] = dados[aux];
                    dados[aux] = dadosAux;
                }
            }
        }

    }

    public static boolean ordenado(int[] dados) {
        for (int i = 0; i < dados.length - 1; i++) {
            if (dados[i] > dados[i + 1]) {
                return false;
            }
        }
        return true;
    }

}