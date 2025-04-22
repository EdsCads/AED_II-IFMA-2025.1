package Questao2;

public class ListaLigada {
    NoLista primeiro=null;

    public void inserir(int dado){
        if(primeiro==null){
            primeiro= new NoLista(dado);
        }else{
            primeiro.inserir(dado);
        }
    }

    public void ordemReversa(){
        primeiro.ordemReversa(primeiro);
    }
}

class NoLista{

    NoLista prox=null;
    int dados;

    public NoLista(int dado){
        this.dados=dado;
    }

    protected void inserir(int dado){
        if(dados==dado){
            return;
        }
        if(prox==null){
            prox=new NoLista(dado);
        }else{
            prox.inserir(dado);
        }
    }

    protected void ordemReversa(NoLista noAtual){
        if(noAtual.prox!=null) {
         ordemReversa(noAtual.prox);
        }
        System.out.println(noAtual.dados);
    }
}
