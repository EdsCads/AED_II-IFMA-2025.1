public class BubbleSorte {
    public void bubbleSort ( int[] dados){
        for(int i = 0; i<dados.length; i++){
            for(int j=0; j<dados.length-1-i; j++){
                if(dados[j]>dados[j+1]){
                    int aux = dados[j];
                    dados[j] = dados[j+1];
                    dados[j+1] = aux;
                }
            }
        }
    }
}
