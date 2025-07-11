package ifma.RedBlack;

import java.util.List;
import ifma.data.InputData;

// Classe Principal (Main) para demonstrar o uso da ArvoreRubroNegra
public class Main {
    public static void main(String[] args) {
        // Cria uma nova instância da Árvore Rubro-Negra para inteiros
        RedBlackTree<Integer> arb = new RedBlackTree<>();

        System.out.println("--- Inserindo elementos ---");
        List<Integer> valores = InputData.readFromFile("input.txt");
        
        for (Integer valor : valores) {
            arb.inserir(valor);
        }

        System.out.println("Percurso Em Ordem (após inserções):");
        arb.percursoEmOrdem(); // Imprime a árvore em ordem
        System.out.println("\nEstrutura da Árvore (Percurso em Nível):");
        arb.imprimirArvorePorNivel(); // Imprime a estrutura da árvore por níveis

        System.out.println("\n--- Buscando elementos ---");
        // Testa a função de busca
        System.out.println("Contém 20: " + arb.contem(20)); // Deve ser true
        System.out.println("Contém 100: " + arb.contem(100)); // Deve ser false

        System.out.println("\n--- Deletando elementos ---");
        System.out.println("Deletando 20:");
        arb.deletar(20);
        System.out.println("Percurso Em Ordem (após deletar 20):");
        arb.percursoEmOrdem();
        System.out.println("\nEstrutura da Árvore (Percurso em Nível):");
        arb.imprimirArvorePorNivel();

        System.out.println("\nDeletando 15:");
        arb.deletar(15);
        System.out.println("Percurso Em Ordem (após deletar 15):");
        arb.percursoEmOrdem();
        System.out.println("\nEstrutura da Árvore (Percurso em Nível):");
        arb.imprimirArvorePorNivel();

        System.out.println("\nDeletando 5:");
        arb.deletar(5);
        System.out.println("Percurso Em Ordem (após deletar 5):");
        arb.percursoEmOrdem();
        System.out.println("\nEstrutura da Árvore (Percurso em Nível):");
        arb.imprimirArvorePorNivel();

        System.out.println("\nDeletando 100 (não está na árvore):");
        arb.deletar(100);
        System.out.println("Percurso Em Ordem (após tentar deletar 100):");
        arb.percursoEmOrdem();
        System.out.println("\nEstrutura da Árvore (Percurso em Nível):");
        arb.imprimirArvorePorNivel();
    }
}
