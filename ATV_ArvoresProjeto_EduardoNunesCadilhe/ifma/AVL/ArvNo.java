package ifma.AVL;

public class ArvNo {
    
    private int height;
    private int value;
    
    private ArvNo left;
    private ArvNo right;
    private ArvNo parent;
    
    public ArvNo( int value, ArvNo left, ArvNo right, ArvNo parent) {
        this.height = 0;
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public void display(ArvNo root) {
        final int height = 5, width = 64;

        int len = width * height * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, root, " ");
        System.out.println(sb);
    }

    private void displayR(StringBuilder sb, int c, int r, int d, int w, ArvNo n,
                          String edge) {
        if (n != null) {
            displayR(sb, c - d, r + 2, d / 2, w, n.left, " /");

            String s = String.valueOf(n.value);
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, n.right, "\\ ");
        }
    }

        public void add (int newValue, ArvNo parentNode){
        if(newValue < parentNode.value){
            if(parentNode.left==null){
                var esq = new ArvNo(newValue,null,null,parentNode);
                parentNode.left = esq;
                esq.parent = parentNode;
                atualizaAltura(parentNode);
            }
            else{
                add(newValue, parentNode.left );
            }
        }
        else{
            if(parentNode.right==null){
                var dir = new ArvNo(newValue,null,null,parentNode);
                parentNode.right = dir;
                dir.parent = parentNode;
                atualizaAltura(parentNode);
            }
            else{
                add(newValue, parentNode.right);
            }
        }

      realizaBalanco(parentNode,parentNode.value);

    }

    public void remove(ArvNo node, int removeValue) {
        if (node == null) {
            return;
        }
        
        if (node.value != removeValue) {
            if (removeValue < node.getValue()) {
                remove(node.left, removeValue);
            } else {
                remove(node.right, removeValue);
            }
        } else {
            // Caso 1: Nó folha
            if (node.left == null && node.right == null) {
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
            }
            // Caso 2: Nó com um filho
            else if (node.left == null) {
                if (node.parent.left == node) {
                    node.parent.left = node.right;
                } else {
                    node.parent.right = node.right;
                }
                node.right.parent = node.parent;
            }
            else if (node.right == null) {
                if (node.parent.left == node) {
                    node.parent.left = node.left;
                } else {
                    node.parent.right = node.left;
                }
                node.left.parent = node.parent;
            }
            // Caso 3: Nó com dois filhos
            else {
                ArvNo sucessor = findMax(node.left);
                node.value = sucessor.value;
                remove(sucessor, sucessor.value);
            }
            
            // Rebalancear a árvore após remoção
            realizaBalanco(node.parent, removeValue);
        }
    }


    private ArvNo findMax(ArvNo node) {
        if (node.right == null) {
            return node;
        }
        return findMax(node.right);
    }

    // Função para realizar uma rotação à direita
    private ArvNo rotateLeft(ArvNo raiz) {
        
        ArvNo filhoDir = raiz.right;
        ArvNo esqFilho = filhoDir.left;

        filhoDir.left = raiz;
        raiz.right = esqFilho;

        atualizaAltura(filhoDir);
        atualizaAltura(raiz);

        return filhoDir;
     }

    // Função para realizar uma rotação à esquerda
    private ArvNo rotateRight(ArvNo raiz) {

         ArvNo filhoEsq = raiz.left;
         ArvNo dirFilho = filhoEsq.right;

         filhoEsq.right = raiz;
         raiz.left = dirFilho;

        atualizaAltura(filhoEsq);
        atualizaAltura(raiz);
        
         return filhoEsq;
     }

    public Integer ehBalanceado(ArvNo node){
        Integer left = (node.left!=null) ? node.left.height : -1;
        Integer right = (node.right!=null) ? node.right.height : -1;
        return left-right;
    }


    public void realizaBalanco(ArvNo node,int value){
        // Verifica o balanceamento e realiza rotações se necessário
        int balance = ehBalanceado(node);
        if(balance >= -1 && balance <= 1){
            return;
        }

        // Caso esquerda-esquerda
        if (balance > 1 && value < node.left.value) {
            atualizaReferenciasPai(node,rotateRight(node));
        }


        // Caso direita-direita
        if (balance < -1 && value > node.right.value) {
            atualizaReferenciasPai(node,rotateLeft(node));
        }


        // Caso esquerda-direita
        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            ArvNo novoNo = rotateRight(node);
            atualizaReferenciasPai(node,novoNo);
        }


        // Caso direita-esquerda
        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            atualizaReferenciasPai(node,rotateLeft(node));
        }

        atualizaAltura(this);
    }

    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public ArvNo getLeft() {
        return left;
    }
    
    public void setLeft(ArvNo left) {
        this.left = left;
    }
    
    public ArvNo getRight() {
        return right;
    }
    
    public void setRight(ArvNo right) {
        this.right = right;
    }
    
    public ArvNo getParent() {
        return parent;
    }
    
    public void setParent(ArvNo parent) {
        this.parent = parent;
    }
    public boolean ehFolha(){
        return left == null && right == null;
    }
    private void atualizaAltura(ArvNo node) {
        if(node == null) return;

        // Atualiza recursivamente a altura dos filhos
        atualizaAltura(node.left);
        atualizaAltura(node.right);

        // Calcula altura dos filhos
        int alturaEsquerda = (node.left != null) ? node.left.height : -1;
        int alturaDireita = (node.right != null) ? node.right.height :-1;

        // Atualiza altura do nó atual
        node.height = Math.max(alturaEsquerda, alturaDireita) + 1;
    }
    private void atualizaReferenciasPai(ArvNo oldRoot, ArvNo newRoot) {
        if (oldRoot.parent != null) {
            if (oldRoot.parent.left == oldRoot) {
                oldRoot.parent.left = newRoot;

            } else {
                oldRoot.parent.right = newRoot;
            }
        }
    }



}
