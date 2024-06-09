package libraries.dataStructures.graphs;

/**
 * UndirectedGraph class: implementation of an Undirected graph
 * (weighted or unweighted) through Adjacency Lists:<br>
 * an Undirected graph IS A Directed Graph such that if the Edge (i, j)
 * is present in i's Adjacency List, then the Edge (j, i) should also
 * be present in j's List.
 *
 * @version December 2019
 */
public class UndirectedGraph extends DirectedGraph {

    /**
     * Creates an empty Undirected graph with nV vertices.
     * 
     * @param numV Number of vertices in the graph
     */
    public UndirectedGraph(int numV) {
        super(numV);
        isDirected = false;
    }

    /**
     * If it isn't in the graph, adds the edge (i, j) to an Undirected
     * Unweighted graph, at the end of the list of adjacent vertices of i.
     * It also adds edge (j, i).
     *
     * @param i Source vertex
     * @param j Target vertex
     */
    public void addEdge(int i, int j) {
        if (!containsEdge(i, j)) {
            theArray[i].add(new Adjacent(j, 1));
            theArray[j].add(new Adjacent(i, 1));
            numE++;
        }
    }

    /**
     * If it isn't in the graph, adds the edge (i, j) with weight w to an
     * Undirected Weighted graph, at the end of the list of adjacent vertices of i.
     * It also adds edge (j, i) with the same weight.
     *
     * @param i Source vertex
     * @param j Target vertex
     * @param w Weight of the edge (i, j)
     */
    public void addEdge(int i, int j, double w) {
        if (!containsEdge(i, j)) {
            theArray[i].add(new Adjacent(j, w));
            theArray[j].add(new Adjacent(i, w));
            numE++;
        }
    }

    /*
     * Se tiene un grafo no dirigido con información sobre las diferentes líneas
     * entre municipios de una red
     * eléctrica. Se desea saber si hay algún municipio en la red de distribución
     * que sea un nodo fuente. Una forma
     * de averiguarlo sería examinar si hay algún vértice en su árbol de
     * recubrimiento mínimo (MST), calculado
     * mediante kruskal, que esté conectado con todos los demás vértices.
     * Para ello, en la clase Grafo (del paquete
     * librerias.estructurasDeDatos.grafos), implementa un
     * método de instancia de nombre fuenteEnMST, sin parámetros y que devuelva un
     * número entero:
     *  -2, si el MST no existe.
     *  -1, si el MST existe, pero no contiene ningún vértice fuente.
     *  El número del vértice fuente, en cualquier otro caso.
     * El perfil del método debe ser: public int fuenteEnMST()
     * TEST. Para comprobar su funcionamiento, descarga el programa TestFuenteMST y
     * añádelo al paquete
     * librerias.estructurasDeDatos.grafos de tu proyecto eda. La salida que debe
     * generar es:
     * Grafo inconexo: -2
     * Sin vértice fuente: -1
     * Vértice fuente: 1
     * Vértice fuente: 5
     */
    public int fuenteEnMST() {
        Edge[] mst = kruskal();
        if (mst == null) {
            return -2;
        }
        int[] cont = new int[numVertices()];
        for (int i = 0; i < mst.length; i++) {
            cont[mst[i].source]++;
            cont[mst[i].target]++;
        }
        for (int i = 0; i < numVertices(); i++) {
            if (cont[mst[i].source] == numVertices() - 1) {
                return mst[i].source;
            }
        }
        return -1;
    }

    /*
     * En un grafo no dirigido, el grado de un vértice se define como el número de
     * aristas que inciden en él.
     * En la clase Grafo, se quiere completar la implementación del siguiente método
     * de instancia:
     * precondición: this es un grafo no dirigido conexo
     * public int verticeMayorGrado() {
     * // A COMPLETAR
     * }
     * Para que el método verticeMayorGrado devuelva el vértice de mayor grado en el
     * árbol de recubrimiento
     * mínimo (MST) calculado mediante kruskal para dicho grafo. Si hay más de un
     * vértice con el mayor grado
     * el método debe devolver cualquiera de ellos.
     */
    public int verticeMayorGrado() {
        Edge[] mst = kruskal();
        int[] cont = new int[numVertices()];
        for (int i = 0; i < mst.length; i++) {
            cont[mst[i].source]++;
            cont[mst[i].target]++;
        }
        int max = 0;
        int res = 0;
        for (int i = 0; i < numVertices(); i++) {
            if (cont[i] > max) {
                max = cont[i];
                res = i;
            }
        }
        return res;
    }
}
