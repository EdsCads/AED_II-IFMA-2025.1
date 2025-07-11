package src.Tool;

public class Lista<T> {
  private Node<T> head;
  private Node<T> last;

  public Lista() {
    this.head = null;
    this.last = null;
  }

  // Insere na lista direto na ultima posição
  public void inserir(T elemento) {
    Node<T> newNode = new Node<>(elemento);
    if (head == null) {
      head = newNode;
      last = newNode;
    } else {
      last.setNext(newNode);
      newNode.setBefore(last);
      last = newNode;
    }
  }

  // Insere na posição definida
  public void inserir(T elemento, int pos) {
    if (pos < 0 || pos > tamanho()) {
      return;
    }
    Node<T> newNode = new Node<>(elemento);
    if (pos == 0) {
      if (last == null) {
        last = newNode;
      } else {
        newNode.setNext(head);
        head.setBefore(newNode);
      }
      head = newNode;
    } else {
      Node<T> noAnt = head;
      for (int i = 1; i < pos; i++) {
        noAnt = noAnt.getNext();
      }
      newNode.setNext(noAnt.getNext());
      noAnt.setNext(newNode);
      newNode.setBefore(noAnt);
      if (pos == tamanho()) {
        last = newNode;
      }
    }
  }

  public boolean remover(T element) {
    Node<T> current = head;
    while (current != null) {
      if (current.getElemento().equals(element)) {
        if (current == head) {
          head = head.getNext();
          if (head != null) {
            head.setBefore(null);
          } else {
            last = null;
          }
        } else if (current == last) {
          last = last.getBefore();
          last.setNext(null);
        } else {
          current.getBefore().setNext(current.getNext());
          current.getNext().setBefore(current.getBefore());
        }
        return true;
      }
      current = current.getNext();
    }
    return false;
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

  public T buscar(int pos) {
    if (pos < 0 || pos >= tamanho()) {
      return null;
    }
    Node<T> no = head;
    for (int i = 0; i < pos; i++) {
      no = no.getNext();
    }
    return no.getElemento();
  }

  public void exibir() {
    Node<T> current = head;
    System.out.print(current.getElemento());
    while (current.getNext() != null) {
      current = current.getNext();
      System.out.print("/");
      System.out.print(current.getElemento());
    }
  }

  public int tamanho() {
    int tamanho = 0;
    Node<T> current = head;
    while (current != null) {
      tamanho += 1;
      current = current.getNext();
    }
    return tamanho;
  }
}