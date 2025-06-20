public class CombSort {
    public void combSort ( int[] dados){
        int gap = (int)(dados.length/1.3);
        int i=0;
        while(gap>=1) {
            for (; i + gap < dados.length; i++) {
                if (dados[i] > dados[i + gap]) {
                    int temp = dados[i];
                    dados[i] = dados[i + gap];
                    dados[i + gap] = temp;
                }
            }
            if (i + gap == dados.length) {
                gap = (int) (gap / 1.3);
                i = 0;
            }
        }
    }
}
