package Tools;

public interface EstruturaDados<T> {

  void inserir(T elemento);

  void remover(T elemento);

  boolean buscar(T elemento);

  void exibir();

}