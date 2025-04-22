package main;

import java.io.*;

public class Pilha<T> implements EstruturaDados<T> {
  private Node<T> top;

  public Pilha(String dadosParaIniciar) {
    this.top = null;
    inserir(dadosParaIniciar);
  }

  public void inserir(String arquivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
      while (br.ready()) {
        inserir((T) br.readLine().trim());
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void inserir(T elemento) {
    Node<T> newNode = new Node<>(elemento);
    newNode.setNext(top);
    top = newNode;
  }

  public void remover(T elemento) {
    Node<T> noAux;
    if (top == null) {
      System.out.println("Pilha vazia");
      return;
    } else if (top.getElemento().equals(elemento)) {
      noAux=top;
      top = top.getNext();
      System.out.println("Elemento "+ noAux.getElemento().toString()+ " removido com sucesso");
      return;
    }
    System.out.println("Elemento nao esta no topo");
  }

  public boolean buscar(T element) {
    Node<T> current = top;
    while (current != null) {
      if (current.getElemento().equals(element)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  public void exibir() {
    Node<T> current = top;
    while (current != null) {
        System.out.println(current.getElemento() + " ");
      current = current.getNext();
    }
    System.out.println();
  }
}
