package main;

import java.io.*;

class Main {
  public static void main(String[] args) {

    String filePath = new File("./Entrada\\exec.txt").getAbsolutePath();
    String dadosParaIniciar = new File("./Entrada\\Dados.txt").getAbsolutePath();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String estrutura = br.readLine().toLowerCase().trim();
      EstruturaDados<Object> estruturaAtual = null;
      switch (estrutura){
        case "lista": estruturaAtual = new Lista<>(dadosParaIniciar);break;
        case  "fila": estruturaAtual = new Fila<>(dadosParaIniciar);break;
        case  "pilha": estruturaAtual = new Pilha<>(dadosParaIniciar);break;
        case  "arvore": estruturaAtual = new Arvore<>(dadosParaIniciar);break;
      }

      System.out.println("ESTRUTURA SELECIONADA: "+estrutura);

      while (br.ready()) {
        String linha[] = br.readLine().split(";");
        String comando = linha[0].toLowerCase().trim();
        if (linha.length > 1) {
          linha[1] = linha[1].trim();
        }
        System.out.println("COMANDO ATUAL: "+comando);
        switch (comando) {
          case "inserir":
            estruturaAtual.inserir(linha[1]);
            break;
          case "deletar":
            estruturaAtual.remover(linha[1]);
            break;
          case "buscar":
            System.out.println(estruturaAtual.buscar(linha[1]));
            break;
          case "imprimir":
            estruturaAtual.exibir();
            break;
          default:
            System.out.println("Comando inválido: " + comando);
        }
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
