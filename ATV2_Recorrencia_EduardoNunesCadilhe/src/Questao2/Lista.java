package Questao2;

import java.io.*;

import Tools.EstruturaDados;
import Tools.Node;

public class Lista<T> implements EstruturaDados<T> {
  private Node<T> head;
  private Node<T> tail;

  public Lista(String dadosParaIniciar) {
    this.head = null;
    this.tail = null;
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
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.setNext(newNode);
      newNode.setBefore(tail);
      tail = newNode;
    }
  }

  public void remover(T element) {
    Node<T> current = head;
    while (current != null) {
      if (current.getElemento().equals(element)) {
        if (current == head) {
          head = head.getNext();
          if (head != null) {
            head.setBefore(null);
          } else {
            tail = null;
          }
        } else if (current == tail) {
          tail = tail.getBefore();
          tail.setNext(null);
        } else {
          current.getBefore().setNext(current.getNext());
          current.getNext().setBefore(current.getBefore());
        }
      }
      current = current.getNext();
    }
  }

  public boolean buscar(T element) {
    Node<T> current = head;
    while (current != null) {
      if (current.getElemento().equals(element)) {
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  public void exibir() {
    Node<T> current = head;
    while (current != null) {
      System.out.println(current.getElemento() + " ");
      current = current.getNext();
    }
    System.out.println();
  }

  public void ordemReversa(){
    ordemReversa(head);
  }

  private void ordemReversa(Node atual){
    if(atual.getNext()!=null) {
      ordemReversa(atual.getNext());
    }
    System.out.println(atual.getElemento());
  }

}