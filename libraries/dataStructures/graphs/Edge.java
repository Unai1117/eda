package libraries.dataStructures.graphs;

/**
 * Edge Class: represents an edge in a graph.<br>
 *
 * @version november 2021
 */

public class Edge implements Comparable<Edge>{

    // AN Edge HAS A source vertex AND A target vertex:
    /* TO BE COMPLETED */
    // AN Edge HAS A weight:
    /* TO BE COMPLETED */
    protected int source, target;
    protected double weight;

    /**
     * Creates an edge (a, b) with weight w.
     *
     * @param a Source vertex
     * @param b Target vertex
     * @param w Weight
     */
    public Edge(int a, int b, double w) {
        /* TO BE COMPLETED */
        this.source = a;
        this.target = b;
        this.weight = w;
    }

    /**
     * Returns the source vertex of an edge.
     *
     * @return int source vertex
     */
    public int getSource() {
        // CHANGE ME
        return this.source;
    }

    /**
     * Returns the target vertex of an edge.
     *
     * @return int target vertex
     */
    public int getTarget() {
        // CHANGE ME
        return this.target;
    }

    /**
     * Reterns an edge's weight
     *
     * @return double Weight of the edge
     */
    public double getWeight() {
        // CHANGE ME
        return this.weight;
    }

    /**
     * Returns a String that represents an edge
     * in the format (source, target, weight)
     *
     * @return String that represents the edge
     */
    public String toString() {
        // CHANGE ME
        return "(" + this.source + ", " + this.target + ", " + this.weight + ")";
    }
    
    public int compareTo(Edge o){
        double w1 = this.getWeight(), w2 = o.getWeight(); 
        if(w1 == w2) return 0; 
        if(w1 > w2) return 1; 
        else {
            return -1; 
        }
    }
}
