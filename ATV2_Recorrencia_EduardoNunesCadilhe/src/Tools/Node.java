package Tools;

import java.util.Objects;

public class Node<T>{
    T elemento;
    Node<T> next;
    Node<T> before;
    
    public Node(T elemento) {
        this.elemento = elemento;
        this.next = null;
        this.before = null;
    }

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getBefore() {
		return this.before;
	}

	public void setBefore(Node<T> before) {
		this.before = before;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Node<?> node = (Node<?>) o;
		return Objects.equals(elemento, node.elemento);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(elemento);
	}
}
