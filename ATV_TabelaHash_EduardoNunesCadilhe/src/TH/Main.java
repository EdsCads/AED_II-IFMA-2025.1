package src.TH;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        HashTable tabela = new HashTable();

        String arquivo = "th";

        String caminho = ".\\src\\Entrada\\" + arquivo + ".txt";

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();

                char operacao = linha.charAt(0);
                String valorStr = linha.length() > 2 ? linha.substring(2).trim() : "";

                executaComando(operacao, valorStr, tabela);
                System.out.println();
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void executaComando(char comando, String elemento, HashTable tabela) {
        switch (comando) {
            case 'I':
                tabela.inserir(elemento);
                break;
            case 'R':
                System.out.println(tabela.remover(elemento) ? "V" : "F");
                break;
            case 'P':
                tabela.imprimir();
                break;
            case 'B':
                int pos = tabela.buscar(elemento);
                if (pos<0) { System.out.println("Nao Encontrado");}
                break;
            default:
                System.out.println("Operação inválida: " + comando);
            }
        }
}