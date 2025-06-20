public class MergeSort {
    public void mergeSort ( int[] dados){
        mergeSort (dados, 0, dados.length-1);
    }

    public static void merge ( int[] dados, int inicio, int meio, int fim){
        int numEsq = meio - inicio+1;
        int numDir = fim - meio;

        //Divide intervalo em Arrays Temporarios (Esquerda e Direita)
        int[] Esq = new int[numEsq];
        int[] Dir = new int[numDir];

        for(int i = 0; i < numEsq; i++){
            Esq[i] = dados[inicio + i];
        }
        for(int i = 0; i < numDir; i++){
            Dir[i] = dados[meio + 1 + i];
        }

        //Reagrupa o intervalo, agora ordenado, ao Array principal
        int i = 0, j = 0,k=inicio;
        while(i<numEsq && j<numDir){
            if(Esq[i]<=Dir[j]){
                dados[k]=Esq[i];
                i++;
            }else{
                dados[k]=Dir[j];
                j++;
            }
            k++;
        }
        for(;i<numEsq;i++,k++){
            dados[k]=Esq[i];
        }
        for(;j<numDir;j++,k++){
            dados[k]=Dir[j];
        }
        return;
    }
    public static void mergeSort ( int[] dados, int inicio, int fim){
        if (inicio >= fim) {
            return;
        } else {
            int meio =inicio + (fim - inicio) / 2;
            MergeSort(dados, inicio, meio);
            MergeSort(dados, meio + 1, fim);
            merge(dados, inicio, meio, fim);
        }
    }

}
