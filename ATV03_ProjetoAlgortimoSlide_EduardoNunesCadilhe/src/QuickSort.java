public class QuickSort {
    public class QuickSort {

        // Método público para iniciar o QuickSort
        public void quickSort(int[] dados) {
            quickSort(dados, 0, dados.length - 1);
        }

        // Método recursivo
        private void quickSort(int[] dados, int inicio, int fim) {
            if (inicio < fim) {
                int pivoIndex = particionar(dados, inicio, fim);
                quickSort(dados, inicio, pivoIndex - 1); // Lado esquerdo
                quickSort(dados, pivoIndex + 1, fim);    // Lado direito
            }
        }

        // Particionamento usando Lomuto
        private int particionar(int[] dados, int inicio, int fim) {
            int pivo = dados[fim];
            int i = inicio - 1;

            for (int j = inicio; j < fim; j++) {
                if (dados[j] <= pivo) {
                    i++;
                    trocar(dados, i, j);
                }
            }

            trocar(dados, i + 1, fim);
            return i + 1;
        }

        // Troca dois elementos do array
        private void trocar(int[] dados, int i, int j) {
            int temp = dados[i];
            dados[i] = dados[j];
            dados[j] = temp;
        }

        // Método de teste
        public static void main(String[] args) {
            int[] dados = {10, 7, 8, 9, 1, 5};

            System.out.println("Antes do QuickSort:");
            for (int num : dados) System.out.print(num + " ");

            QuickSort qs = new QuickSort();
            qs.quickSort(dados);

            System.out.println("\nDepois do QuickSort:");
            for (int num : dados) System.out.print(num + " ");
        }
    }


}
