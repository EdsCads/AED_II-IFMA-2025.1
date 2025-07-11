package ifma.RedBlack;

class Node<T extends Comparable<T>> {
    T dado; 
    Node<T> pai; 
    Node<T> esquerda; 
    Node<T> direita; 
    boolean cor; 

    public Node(T dado) {
        this.dado = dado;
        this.cor = RedBlackTree.VERMELHO;
        this.esquerda = null;
        this.direita = null;
        this.pai = null; 
    }
}
