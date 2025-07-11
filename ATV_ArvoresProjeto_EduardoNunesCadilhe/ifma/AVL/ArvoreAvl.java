package ifma.AVL;


public class ArvoreAvl {
    
    ArvNo root;

    public void add(int value){
        if(root==null){
            root = new ArvNo(value,null,null,null);
        }else{
            root.add(value,root);
        }
    }
    
    public void imprimir(ArvNo noAtual){
        System.out.println(noAtual.getValue());
    }

    public void remover(int value){
       if(this.root!=null){
          root.remove(root,value);
       }
    }

   public void preOrder(){
        preOrder(this.root);
    } 
   public void order(){
        order(this.root);
    }
   public void posOrder(){
        posOrder(this.root);
    }
    private void preOrder(ArvNo raiz){
        if(raiz==null){
            return;
        }
        preOrder(raiz.getLeft());
        preOrder(raiz.getRight());
        this.imprimir(raiz);
    }
     private void order(ArvNo raiz){
         if (raiz == null) {
            return;
        }
        order(raiz.getLeft());
        this.imprimir(raiz);
        order(raiz.getRight());
    }

    private void posOrder(ArvNo raiz) {
        if (raiz == null) {
            return;
        }
        this.imprimir(raiz);
        posOrder(raiz.getLeft());
        posOrder(raiz.getRight());
    }

    public void amostra(){
        root.display(root);
    }

    public ArvNo getRoot(){
        return this.root;
    }


}
