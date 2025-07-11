package ifma.RedBlack;

import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree <T extends Comparable<T>> {
    public static final boolean VERMELHO = true;
    public static final boolean PRETO = false;

    // A raiz da árvore
    private Node<T> raiz;

    // Construtor da Árvore Rubro-Negra
    public RedBlackTree() {
        this.raiz = null; // Inicialmente a árvore está vazia
    }

    /**
     * Insere um novo dado na Árvore Rubro-Negra.
     * Mantém as propriedades da árvore através de rotações e recolorações.
     *
     * @param dado O dado a ser inserido.
     */
    public void inserir(T dado) {
        Node<T> novoNo = new Node<>(dado); // Cria um novo nó, inicialmente vermelho
        if (raiz == null) {
            raiz = novoNo;
            raiz.cor = PRETO; // A raiz é sempre preta
            return;
        }

        Node<T> atual = raiz;
        Node<T> paiDoAtual = null;

        // Procura a posição correta para o novo nó, como em uma BST
        while (atual != null) {
            paiDoAtual = atual;
            if (dado.compareTo(atual.dado) < 0) {
                atual = atual.esquerda;
            } else if (dado.compareTo(atual.dado) > 0) {
                atual = atual.direita;
            } else {
                // Se o valor já existe, não faz nada (ou poderia atualizar)
                return;
            }
        }

        // Conecta o novo nó ao seu pai
        novoNo.pai = paiDoAtual;
        if (dado.compareTo(paiDoAtual.dado) < 0) {
            paiDoAtual.esquerda = novoNo;
        } else {
            paiDoAtual.direita = novoNo;
        }

        // Corrige as violações das propriedades da Árvore Rubro-Negra
        corrigirInsercao(novoNo);
    }

    /**
     * Corrige as violações das propriedades da Árvore Rubro-Negra após uma inserção.
     *
     * @param no O nó recém-inserido que pode ter causado a violação.
     */
    private void corrigirInsercao(Node<T> no) {
        Node<T> tio; // Variável para o nó "tio"
        // Continua corrigindo enquanto o nó não for a raiz e o pai for vermelho (violação da propriedade 4)
        while (no != raiz && no.pai.cor == VERMELHO) {
            // Verifica se o pai do nó é o filho esquerdo do avô
            if (no.pai == no.pai.pai.esquerda) {
                tio = no.pai.pai.direita; // O tio é o filho direito do avô
                if (tio != null && tio.cor == VERMELHO) {
                    // Caso 1: Tio é vermelho. Recolorir e subir na árvore.
                    no.pai.cor = PRETO;
                    tio.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    no = no.pai.pai; // Move para o avô para continuar a verificação
                } else {
                    // Casos 2 e 3: Tio é preto
                    if (no == no.pai.direita) {
                        // Caso 2: O nó é filho à direita (configuração "triângulo").
                        // Rotação à esquerda para transformar em Caso 3.
                        no = no.pai;
                        rotacionarEsquerda(no);
                    }
                    // Caso 3: O nó é filho à esquerda (configuração "linha").
                    // Recolorir e rotação à direita.
                    no.pai.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    rotacionarDireita(no.pai.pai);
                }
            } else {
                // Casos simétricos quando o pai do nó é o filho direito do avô
                tio = no.pai.pai.esquerda; // O tio é o filho esquerdo do avô
                if (tio != null && tio.cor == VERMELHO) {
                    // Caso 1: Tio é vermelho. Recolorir e subir na árvore.
                    no.pai.cor = PRETO;
                    tio.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    no = no.pai.pai; // Move para o avô
                } else {
                    // Casos 2 e 3: Tio é preto
                    if (no == no.pai.esquerda) {
                        // Caso 2: O nó é filho à esquerda (configuração "triângulo").
                        // Rotação à direita para transformar em Caso 3.
                        no = no.pai;
                        rotacionarDireita(no);
                    }
                    // Caso 3: O nó é filho à direita (configuração "linha").
                    // Recolorir e rotação à esquerda.
                    no.pai.cor = PRETO;
                    no.pai.pai.cor = VERMELHO;
                    rotacionarEsquerda(no.pai.pai);
                }
            }
        }
        raiz.cor = PRETO; // Garante que a raiz seja sempre preta
    }

    /**
     * Deleta um dado da Árvore Rubro-Negra.
     *
     * @param dado O dado a ser deletado.
     */
    public void deletar(T dado) {
        Node<T> noParaDeletar = buscarNo(dado); // Encontra o nó a ser deletado
        if (noParaDeletar == null) {
            System.out.println("Valor " + dado + " não encontrado na árvore.");
            return;
        }
        deletarNo(noParaDeletar); // Chama o método auxiliar de deleção
    }

    /**
     * Método auxiliar para deletar um nó específico da Árvore Rubro-Negra.
     *
     * @param noParaDeletar O nó a ser removido.
     */
    private void deletarNo(Node<T> noParaDeletar) {
        Node<T> x; // Nó que substitui o nó deletado ou seu sucessor
        Node<T> y = noParaDeletar; // Nó que é realmente removido ou movido
        boolean corOriginal = y.cor; // Cor original do nó que será removido/movido

        if (noParaDeletar.esquerda == null) {
            x = noParaDeletar.direita;
            rbTransplantar(noParaDeletar, noParaDeletar.direita);
        } else if (noParaDeletar.direita == null) {
            x = noParaDeletar.esquerda;
            rbTransplantar(noParaDeletar, noParaDeletar.esquerda);
        } else {
            y = minimo(noParaDeletar.direita); // Encontra o sucessor in-order
            corOriginal = y.cor;
            x = y.direita; // O filho direito de y (pode ser nulo)

            if (y.pai == noParaDeletar) {
                // Se y é filho direto de noParaDeletar
                if (x != null) {
                    x.pai = y; // Garante que x aponte para y como pai
                }
            } else {
                // Se y não é filho direto de noParaDeletar
                rbTransplantar(y, y.direita); // Move o filho direito de y para a posição de y
                y.direita = noParaDeletar.direita;
                y.direita.pai = y;
            }
            rbTransplantar(noParaDeletar, y); // Move y para a posição de noParaDeletar
            y.esquerda = noParaDeletar.esquerda;
            y.esquerda.pai = y;
            y.cor = noParaDeletar.cor; // y herda a cor do nó deletado
        }

        // Se a cor original do nó removido/movido era PRETO, é necessário corrigir a árvore
        if (corOriginal == PRETO) {
            corrigirDelecao(x);
        }
    }

    /**
     * Substitui a subárvore enraizada em 'u' pela subárvore enraizada em 'v'.
     *
     * @param u O nó a ser substituído.
     * @param v O nó que irá substituir 'u'.
     */
    private void rbTransplantar(Node<T> u, Node<T> v) {
        if (u.pai == null) {
            raiz = v; // Se u é a raiz, v se torna a nova raiz
        } else if (u == u.pai.esquerda) {
            u.pai.esquerda = v; // Se u é filho esquerdo, v se torna o novo filho esquerdo
        } else {
            u.pai.direita = v; // Se u é filho direito, v se torna o novo filho direito
        }
        if (v != null) {
            v.pai = u.pai; // O pai de v agora é o pai de u
        }
    }

    /**
     * Corrige as violações das propriedades da Árvore Rubro-Negra após uma deleção.
     *
     * @param x O nó que causou a violação (geralmente o filho do nó deletado ou seu sucessor).
     */
    private void corrigirDelecao(Node<T> x) {
        // Continua corrigindo enquanto x não for a raiz e x for preto (ou nulo, tratado como preto)
        while (x != raiz && (x == null || x.cor == PRETO)) {
            if (x == x.pai.esquerda) {
                Node<T> irmao = x.pai.direita; // O irmão de x

                if (irmao != null && irmao.cor == VERMELHO) {
                    // Caso 1: Irmão é vermelho. Recolorir e rotacionar.
                    irmao.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacionarEsquerda(x.pai);
                    irmao = x.pai.direita; // Atualiza o irmão após a rotação
                }

                if ((irmao == null || irmao.esquerda == null || irmao.esquerda.cor == PRETO) &&
                    (irmao == null || irmao.direita == null || irmao.direita.cor == PRETO)) {
                    // Caso 2: Irmão é preto e ambos os filhos do irmão são pretos.
                    // Recolorir irmão e subir na árvore.
                    if (irmao != null) irmao.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (irmao != null && (irmao.direita == null || irmao.direita.cor == PRETO)) {
                        // Caso 3: Irmão é preto, filho esquerdo do irmão é vermelho, filho direito é preto.
                        // Recolorir e rotacionar para transformar em Caso 4.
                        if (irmao.esquerda != null) irmao.esquerda.cor = PRETO;
                        if (irmao != null) irmao.cor = VERMELHO;
                        rotacionarDireita(irmao);
                        irmao = x.pai.direita; // Atualiza o irmão
                    }
                    // Caso 4: Irmão é preto, filho direito do irmão é vermelho.
                    // Recolorir, rotacionar e parar.
                    if (irmao != null) irmao.cor = x.pai.cor;
                    if (x.pai != null) x.pai.cor = PRETO;
                    if (irmao != null && irmao.direita != null) irmao.direita.cor = PRETO;
                    rotacionarEsquerda(x.pai);
                    x = raiz; // Define x para a raiz para terminar o loop
                }
            } else {
                // Casos simétricos (quando x é filho direito)
                Node<T> irmao = x.pai.esquerda;

                if (irmao != null && irmao.cor == VERMELHO) {
                    irmao.cor = PRETO;
                    x.pai.cor = VERMELHO;
                    rotacionarDireita(x.pai);
                    irmao = x.pai.esquerda;
                }

                if ((irmao == null || irmao.direita == null || irmao.direita.cor == PRETO) &&
                    (irmao == null || irmao.esquerda == null || irmao.esquerda.cor == PRETO)) {
                    if (irmao != null) irmao.cor = VERMELHO;
                    x = x.pai;
                } else {
                    if (irmao != null && (irmao.esquerda == null || irmao.esquerda.cor == PRETO)) {
                        if (irmao.direita != null) irmao.direita.cor = PRETO;
                        if (irmao != null) irmao.cor = VERMELHO;
                        rotacionarEsquerda(irmao);
                        irmao = x.pai.esquerda;
                    }
                    if (irmao != null) irmao.cor = x.pai.cor;
                    if (x.pai != null) x.pai.cor = PRETO;
                    if (irmao != null && irmao.esquerda != null) irmao.esquerda.cor = PRETO;
                    rotacionarDireita(x.pai);
                    x = raiz;
                }
            }
        }
        if (x != null) x.cor = PRETO; // Garante que o nó final seja preto
    }

    /**
     * Realiza uma rotação à esquerda em torno do nó 'x'.
     *
     * @param x O nó pivô da rotação.
     */
    private void rotacionarEsquerda(Node<T> x) {
        Node<T> y = x.direita; // 'y' é o filho direito de 'x'
        x.direita = y.esquerda; // O filho esquerdo de 'y' se torna o filho direito de 'x'
        if (y.esquerda != null) {
            y.esquerda.pai = x; // Atualiza o pai do antigo filho esquerdo de 'y'
        }
        y.pai = x.pai; // O pai de 'y' se torna o pai de 'x'
        if (x.pai == null) {
            raiz = y; // Se 'x' era a raiz, 'y' se torna a nova raiz
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y; // Se 'x' era filho esquerdo, 'y' se torna o novo filho esquerdo
        } else {
            x.pai.direita = y; // Se 'x' era filho direito, 'y' se torna o novo filho direito
        }
        y.esquerda = x; // 'x' se torna o filho esquerdo de 'y'
        x.pai = y; // O pai de 'x' agora é 'y'
    }

    /**
     * Realiza uma rotação à direita em torno do nó 'x'.
     *
     * @param x O nó pivô da rotação.
     */
    private void rotacionarDireita(Node<T> x) {
        Node<T> y = x.esquerda; // 'y' é o filho esquerdo de 'x'
        x.esquerda = y.direita; // O filho direito de 'y' se torna o filho esquerdo de 'x'
        if (y.direita != null) {
            y.direita.pai = x; // Atualiza o pai do antigo filho direito de 'y'
        }
        y.pai = x.pai; // O pai de 'y' se torna o pai de 'x'
        if (x.pai == null) {
            raiz = y; // Se 'x' era a raiz, 'y' se torna a nova raiz
        } else if (x == x.pai.direita) {
            x.pai.direita = y; // Se 'x' era filho direito, 'y' se torna o novo filho direito
        } else {
            x.pai.esquerda = y; // Se 'x' era filho esquerdo, 'y' se torna o novo filho esquerdo
        }
        y.direita = x; // 'x' se torna o filho direito de 'y'
        x.pai = y; // O pai de 'x' agora é 'y'
    }

    /**
     * Busca um nó na árvore que contém o dado especificado.
     *
     * @param dado O dado a ser buscado.
     * @return O nó se encontrado, ou null caso contrário.
     */
    public Node<T> buscarNo(T dado) {
        Node<T> atual = raiz;
        while (atual != null) {
            int cmp = dado.compareTo(atual.dado);
            if (cmp < 0) {
                atual = atual.esquerda; // Se o dado é menor, vai para a esquerda
            } else if (cmp > 0) {
                atual = atual.direita; // Se o dado é maior, vai para a direita
            } else {
                return atual; // Dado encontrado
            }
        }
        return null; // Dado não encontrado
    }

    /**
     * Verifica se a árvore contém o dado especificado.
     *
     * @param dado O dado a ser verificado.
     * @return true se o dado estiver na árvore, false caso contrário.
     */
    public boolean contem(T dado) {
        return buscarNo(dado) != null;
    }

    /**
     * Encontra o nó com o valor mínimo na subárvore dada.
     *
     * @param no O nó raiz da subárvore.
     * @return O nó com o valor mínimo.
     */
    private Node<T> minimo(Node<T> no) {
        while (no.esquerda != null) {
            no = no.esquerda; // O mínimo está sempre no caminho mais à esquerda
        }
        return no;
    }

    /**
     * Realiza um percurso em ordem na árvore, imprimindo os nós.
     */
    public void percursoEmOrdem() {
        percursoEmOrdem(raiz);
        System.out.println(); // Nova linha após o percurso
    }

    /**
     * Método auxiliar para o percurso em ordem recursivo.
     *
     * @param no O nó atual no percurso.
     */
    private void percursoEmOrdem(Node<T> no) {
        if (no != null) {
            percursoEmOrdem(no.esquerda); // Visita a subárvore esquerda
            System.out.print(no.dado + (no.cor == VERMELHO ? "(V) " : "(P) ")); // Visita o nó atual
            percursoEmOrdem(no.direita); // Visita a subárvore direita
        }
    }

    /**
     * Imprime a árvore nível por nível (percurso em largura), mostrando o dado, cor e pai de cada nó.
     */
    public void imprimirArvorePorNivel() {
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        Queue<Node<T>> fila = new LinkedList<>(); // Fila para o percurso em largura
        fila.add(raiz);

        while (!fila.isEmpty()) {
            int tamanhoNivel = fila.size(); // Número de nós no nível atual
            for (int i = 0; i < tamanhoNivel; i++) {
                Node<T> no = fila.poll(); // Remove o nó da frente da fila
                String cor = no.cor == VERMELHO ? "V" : "P"; // Determina a cor para impressão
                String dadoPai = (no.pai != null) ? String.valueOf(no.pai.dado) : "N/A"; // Obtém o dado do pai
                System.out.print(no.dado + "(" + cor + ") [Pai:" + dadoPai + "] "); // Imprime o nó

                // Adiciona os filhos à fila para o próximo nível
                if (no.esquerda != null) {
                    fila.add(no.esquerda);
                }
                if (no.direita != null) {
                    fila.add(no.direita);
                }
            }
            System.out.println(); // Nova linha para o próximo nível
        }
    }
}
