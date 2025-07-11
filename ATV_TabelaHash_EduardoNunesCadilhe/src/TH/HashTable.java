package src.TH;

import src.Tool.Lista;

public class HashTable {
    private Hash hash = new Hash();
    private static final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    Lista<Lista<String>> tabelaHash;
    int max;
    int qtdElementos;

    public HashTable() {
        qtdElementos = 0;
        tabelaHash = new Lista<>();
        max = INITIAL_CAPACITY;
        iniciaTabela(tabelaHash);
    }

    private void iniciaTabela(Lista<Lista<String>> tabelaHash) {
        for (int i = 0; i < max; i++) {
            tabelaHash.inserir(new Lista<>(), i);
        }
    }

    private int gerarPosicao(String frase) {
        return Math.abs(hash.of(frase)) % max;
    }

    public void inserir(String frase) {
        if ((double) quantidadeElementos() / tabelaHash.tamanho() >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }
        int posicao = gerarPosicao(frase);
        Lista<String> lista = tabelaHash.buscar(posicao);
        lista.inserir(frase);
        qtdElementos++;
    }

    private int quantidadeElementos() {
        int quantidade = 0;
        for (int i = 0; i < tabelaHash.tamanho(); i++) {
            Lista<String> atualLista = tabelaHash.buscar(i);
            quantidade += atualLista.tamanho();
        }
        return quantidade;
    }

    public void imprimir() {

        for (int i = 0; i < tabelaHash.tamanho(); i++) {
            Lista<String> atual = tabelaHash.buscar(i);
            if (atual.buscar(0) == null) {
                System.out.print("--");
            } else {
                atual.exibir();
            }
            System.out.print(";");
        }
    }

    public int buscar(String frase) {
        int posicao = gerarPosicao(frase);
        Lista<String> listaElemento = tabelaHash.buscar(posicao);
        int hashFrase = hash.of(frase);
        for (int i = 0; i < listaElemento.tamanho(); i++) {
            if (hash.of(listaElemento.buscar(i)) == hashFrase) {
                System.out.println(listaElemento.buscar(i)+" no bucket "+posicao);
                return posicao;
            }
        }
        return -1;
    }

    public void resize() {
        String frase = "";
        System.out.print("Redimensionando de ");
        Lista<Lista<String>> novaLista = new Lista<>();
        Lista<Lista<String>> antigaLista = tabelaHash;
        System.out.println(max + " para " + (max + max / 2));
        max = max + max / 2;
        iniciaTabela(novaLista);
        tabelaHash = novaLista;

        int tamanho = antigaLista.tamanho();

        for (int i = 0; i < tamanho; i++) {
            Lista<String> atual = antigaLista.buscar(i);

            for (int j = 0; j < atual.tamanho(); j++) {
                frase = atual.buscar(j);
                if (frase != null) {
                    inserir(frase);
                }

            }
        }
    }

    public boolean remover(String frase) {
        int posicao = gerarPosicao(frase);
        Lista<String> listaElemento = tabelaHash.buscar(posicao);
        int hashChave = hash.of(frase);
        for (int i = 0; i < listaElemento.tamanho(); i++) {
            if (hash.of(listaElemento.buscar(i)) == hashChave) {
                return listaElemento.remover(frase);
            }
        }
        return false;
    }

}
