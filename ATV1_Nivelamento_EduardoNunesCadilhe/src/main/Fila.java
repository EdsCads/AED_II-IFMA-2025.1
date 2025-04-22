package main;

import java.io.*;

public class Fila<T> implements EstruturaDados<T> {
  private Node<T> front;
  private Node<T> rear;

  public Fila(String dadosParaIniciar) {
    this.front = null;
    this.rear = null;
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
    if (front == null) {
      front = newNode;
      rear = newNode;
    } else {
      rear.setNext(newNode);
      newNode.setBefore(rear);
      rear = newNode;
    }
  }

  public void remover(T elemento) {
    Node noAux = new Node(null);
    if(front == null){return;}
    if(front == rear){
      front = null;
      rear = null;
      System.out.println("Unico elemento removido com sucesso");
    }else if(front.getElemento().equals(elemento)){
      noAux=front;
      front=front.getNext();
    }
  }

  public boolean buscar(T elemento) {
    Node<T> current = front;
    while (current != null) {
      if (current.getElemento().equals(elemento)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  public void exibir() {
    Node<T> current = front;
    while (current != null) {
      System.out.println(current.getElemento() + " ");
      current = current.getNext();
    }
    System.out.println();
  }
}