package main;

import java.io.*;

public class Arvore<T> implements EstruturaDados<T> {
  private Node<T> root;

  public Arvore(String dadosParaIniciar) {
    toString("Iniciando a insercao de dados na estrutura");
    this.root = null;
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
    if (root == null) {
      root = newNode;
    } else {
      Node<T> current = root;
      while (true) {
        if (newNode.hashCode() < current.hashCode()) {
          if (current.getBefore() == null) {
            current.setBefore(newNode);
            break;
          }
          current = current.getBefore();
        } else {
          if (current.getNext() == null) {
            current.setNext(newNode);
            break;
          }
          current = current.getNext();
        }
      }
    }
  }

  public void remover(T elemento) {
    if(buscar(elemento)) {
      remover(root, elemento);
      System.out.println("removido");
    }else{
      System.out.println("Não encontrado, remoção falhou");
    }
  }

  private Node<T> remover(Node<T> current, T elemento) {
    if (current == null) {
      return current;
    }
    if (!current.getElemento().equals(elemento)) {
      current.setBefore(remover(current.getBefore(), elemento));
      current.setNext(remover(current.getNext(), elemento));
    } else {
      if (current.getBefore() == null) {
        return current.getNext();
      } else if (current.getNext() == null) {
        return current.getBefore();
      }
      current.setElemento(findMin(current.getNext()));
      current.setNext(remover(current.getNext(), current.getElemento()));
    }
    return current;
  }

  private T findMin(Node<T> current) {
    while (current.getBefore() != null) {
      current = current.getBefore();
    }
    return current.getElemento();
  }

  public boolean buscar(T elemento) {
    Node<T> no = buscar(root, elemento);
    if(no==null){
      System.out.println();
      return false;
    }
    System.out.println(no.getElemento());

    return true;
  }

  private Node<T> buscar(Node<T> current, T elemento) {
    if (current == null) {
      return null;
    }
    if (elemento.equals(current.getElemento())) {
      return current;
    } else if (elemento.hashCode() < current.getElemento().hashCode()) {
      return buscar(current.getBefore(), elemento);
    } else {
      return buscar(current.getNext(), elemento);
    }
  }

  public void exibir() {
    System.out.println("\nPre-Ordem: \n");
    preOrder(root);
    System.out.println("\nEm Ordem: \n");
    inOrder(root);
    System.out.println("\nPos-Ordem: \n");
    postOrder(root);
  }

  private void preOrder(Node<T> current) {
    if (current != null) {
      System.out.println(current.getElemento() + " ");
      preOrder(current.getBefore());
      preOrder(current.getNext());
    }
  }

  private void inOrder(Node<T> current) {
    if (current != null) {
      inOrder(current.getBefore());
      System.out.println(current.getElemento() + " ");
      inOrder(current.getNext());
    }
  }

  private void postOrder(Node<T> current) {
    if (current != null) {
      postOrder(current.getBefore());
      postOrder(current.getNext());
      System.out.println(current.getElemento() + " ");
    }
  }

  static void toString(String frase){
    System.out.println(frase);
  }

}