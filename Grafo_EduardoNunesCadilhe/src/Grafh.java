import java.util.*;

public class Grafh {
    private Map<Integer, List<Integer>> adjVertices; // Lista de adjacências
    private boolean isDirected;

    public Grafh(boolean directed) {
        this.adjVertices = new HashMap<>();
        this.isDirected = directed;
    }

    // Adiciona um vértice ao grafo
    public void addVertex(int vertex) {
        if (!adjVertices.containsKey(vertex)) {
            adjVertices.put(vertex, new ArrayList<>());
        }
    }

    // Adiciona uma aresta entre dois vértices
    public void addEdge(int v1, int v2) {
        addVertex(v1);
        addVertex(v2);

        adjVertices.get(v1).add(v2);

        if (!isDirected) {
            adjVertices.get(v2).add(v1);
        }
    }

    // Remove uma aresta entre dois vértices
    public void removeEdge(int v1, int v2) {
        if (adjVertices.containsKey(v1)) {
            adjVertices.get(v1).remove(Integer.valueOf(v2));
        }
        if (!isDirected && adjVertices.containsKey(v2)) {
            adjVertices.get(v2).remove(Integer.valueOf(v1));
        }
    }

    // Remove um vértice e todas as suas conexões
    public void removeVertex(int vertex) {
        if (!adjVertices.containsKey(vertex)) return;

        // Remove o vértice das listas dos outros
        for (List<Integer> neighbors : adjVertices.values()) {
            neighbors.remove(Integer.valueOf(vertex));
        }

        // Remove o próprio vértice
        adjVertices.remove(vertex);
    }

    // Percorre o grafo usando BFS
    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int neighbor : adjVertices.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Percorre o grafo usando DFS
    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursive(int vertex, Set<Integer> visited) {
        visited.add(vertex);
        System.out.print(vertex + " ");
        for (int neighbor : adjVertices.getOrDefault(vertex, Collections.emptyList())) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    // Imprime o grafo
    public void printGraph() {
        for (Map.Entry<Integer, List<Integer>> entry : adjVertices.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // Retorna os vizinhos de um vértice
    public List<Integer> getNeighbors(int vertex) {
        return adjVertices.getOrDefault(vertex, Collections.emptyList());
    }

    // Verifica se existe uma aresta
    public boolean hasEdge(int v1, int v2) {
        return adjVertices.containsKey(v1) && adjVertices.get(v1).contains(v2);
    }

    // Getter para verificar se é direcionado
    public boolean isDirected() {
        return isDirected;
    }

    // Getter para número de vértices
    public int getVertexCount() {
        return adjVertices.size();
    }
}