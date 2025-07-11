import java.util.Arrays;

public class Main {

    // Método principal para testar o algoritmo
    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        int[] arr = {12, 11, 13, 5, 6, 7, -2, 0};

        System.out.println("Array original: " + Arrays.toString(arr));

        hs.sort(arr);

        System.out.println("Array ordenado: " + Arrays.toString(arr));

        // Teste com outro array
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("\nArray original: " + Arrays.toString(arr2));
        hs.sort(arr2);
        System.out.println("Array ordenado: " + Arrays.toString(arr2));

        // Teste com array quase ordenado
        int[] arr3 = {2, 4, 3, 5, 1};
        System.out.println("\nArray original: " + Arrays.toString(arr3));
        hs.sort(arr3);
        System.out.println("Array ordenado: " + Arrays.toString(arr3));
    }
}