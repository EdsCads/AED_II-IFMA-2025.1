public class HeapSort {

        /**
         * Método principal que ordena o array usando Heap Sort.
         * @param arr O array a ser ordenado.
         */
        public void sort(int[] arr) {
            if (arr == null || arr.length == 0) {
                return; // Não faz nada se o array for nulo ou vazio
            }

            int n = arr.length;

            // 1. Construir o Max-Heap (reorganizar o array)
            // Começa do último nó não-folha (n / 2 - 1) e vai até a raiz (0)
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(arr, n, i);
            }

            // 2. Extrair elementos um por um do heap
            for (int i = n - 1; i > 0; i--) {
                // Move a raiz atual (maior elemento) para o fim do array (posição i)
                swap(arr, 0, i);

                // Chama heapify na heap reduzida (tamanho i) a partir da raiz (0)
                // para garantir que a propriedade de max-heap seja mantida
                heapify(arr, i, 0);
            }
        }

        /**
         * Transforma uma subárvore com raiz no índice 'i' em um Max-Heap.
         *
         * @param arr O array que representa o heap.
         * @param n   O tamanho atual do heap (pode ser menor que arr.length durante a fase de extração).
         * @param i   O índice da raiz da subárvore a ser "heapificada".
         */
        private void heapify(int[] arr, int n, int i) {
            int largest = i;       // Inicializa o maior como a raiz
            int leftChild = 2 * i + 1;  // Índice do filho esquerdo
            int rightChild = 2 * i + 2; // Índice do filho direito

            // Se o filho esquerdo for maior que a raiz atual
            if (leftChild < n && arr[leftChild] > arr[largest]) {
                largest = leftChild;
            }

            // Se o filho direito for maior que o maior encontrado até agora
            if (rightChild < n && arr[rightChild] > arr[largest]) {
                largest = rightChild;
            }

            // Se o maior não for a raiz original 'i'
            if (largest != i) {
                // Troca a raiz com o maior filho
                swap(arr, i, largest);

                // Recursivamente "heapifica" a subárvore afetada (que agora tem a raiz 'largest')
                heapify(arr, n, largest);
            }
        }

        /**
         * Função auxiliar para trocar dois elementos no array.
         * @param arr O array.
         * @param i   Índice do primeiro elemento.
         * @param j   Índice do segundo elemento.
         */
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

}