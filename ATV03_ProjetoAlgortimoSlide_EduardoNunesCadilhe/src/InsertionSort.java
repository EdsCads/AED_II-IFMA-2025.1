public class InsertionSort {
    public void insertionSort ( int[] dados){
        int temp;
        for (int i = 1,j; i < dados.length; i++) {
            temp = dados[i];
            for (j=i-1; j >= 0&&dados[j]>temp; j--) {
                dados[j + 1] = dados[j];
            }
            dados[j+1]= temp;
        }
    }
}
