package Questao1;

public class ArvBin {
    NoArv root=null;

    public void inserir(int dado){
        if(root==null){
            root = new NoArv(dado);
        }else{
            root.inserir(dado);
        }
    }

    public void imprimir(){
        root.imprimir();
    }

    public int contaFilho(){
        int contagem=0;
        contagem=root.contaFilho(contagem);
        return contagem;
    }
}

class NoArv{
    NoArv esq=null;
    NoArv dir=null;
    int dados;

    public NoArv (int dado){
        this.dados=dado;
    }

    protected void inserir(int dado){
        if(dados==dado){
            return;
        }
        if(dados<dado){
            if(esq!=null){
                esq.inserir(dado);
            }else{
                esq = new NoArv(dado);
            }
        }else{
            if(dir!=null){
                dir.inserir(dado);
            }else{
                dir = new NoArv(dado);
            }
        }
    }

    public void imprimir(){
        System.out.println(this.dados);
        if(esq!=null)
            esq.imprimir();
        if(dir!=null)
            dir.imprimir();
    }

    protected int contaFilho(int contagem){
        if(esq!=null||dir!=null){
            contagem++;
        }
        if(esq!=null)
            contagem=esq.contaFilho(contagem);
        if(dir!=null)
            contagem=dir.contaFilho(contagem);

        return contagem;
    }
}

