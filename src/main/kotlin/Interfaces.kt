package org.example

/**
 * This ``Graph`` that represents a directed graph
 * @param VertexType the representation of a vertex in the graph
 */
interface Graph<VertexType> {
    /**
     * @return the vertices in the graph
     */
    fun getVertices(): Set<VertexType>

    /**
     * Add an
     */
    fun addEdge(from: VertexType, to: VertexType, cost: Double)

    /**
     *
     */
    fun getEdges(from: VertexType): Map<VertexType, Double>

    /**
     * Remove all edges and vertices from the graph
     */
    fun clear()
}

class DirectedGraph<VertexType>(private var vertices: MutableSet<VertexType> = mutableSetOf()): Graph<VertexType>{

    //private var vertices: MutableSet<VertexType>  = mutableSetOf()
    //private var edges: MutableMap<VertexType, MutableSet<VertexType>> = mutableMapOf()
    private var weightedEdges: MutableMap<VertexType, MutableMap<VertexType, Double>> = mutableMapOf()

    /**
     * Add the vertex [v] to the graph
     * @param v the vertex to add
     * @return true if the vertex is successfully added, false if the vertex
     *   was already in the graph
     */
    fun addVertex(v: VertexType): Boolean {
        if (vertices.contains(v)) {
            return false
        }
        vertices.add(v)
        return true
    }

    /**
     * @return the vertices in the graph
     */
    override fun getVertices(): Set<VertexType> {
        return vertices
    }


    /**
     * Adds edge from [from] to [to] with weight [cost]
     * @param from the starting vertex
     * @param to the end vertex
     * @param cost the weight
     */
    override fun addEdge(from: VertexType, to: VertexType, cost: Double) {
        if (!vertices.contains(from) || !vertices.contains(to)) {
            error("Trying to connect one or more nonexistent vertices. Edges can only connect existing Vertices")
        }
        weightedEdges[from]?.also { currentAdjacent: MutableMap<VertexType, Double> ->
            //if there is an existing edge between to and from, it updates the cost
            if (currentAdjacent.contains(to)) {
                currentAdjacent[to] = cost
            }
            //stores new edge in existing mutableMap paired to the from vertex
            currentAdjacent.put(to, cost)

        } ?: run {
            //makes an empty mutable map to store all edges connectd to the from vertex
            weightedEdges[from] = mutableMapOf<VertexType, Double>()
            //adds new edge into the new mutableMap paired to the from vertex
            weightedEdges[from]?.put(to,cost)

        }
    }

    /**
     * Gets all the current edges starting from [from]
     * @param from the starting vertex
     * @return Map with mapping the ending vertex with the associated wieght
     * or
     * @return an empty Map if no edges exist.
     */
    override fun getEdges(from: VertexType): Map<VertexType, Double> {
        //if edges from that Vertex exist, returns the connected vertices with the corresponding weight
        weightedEdges[from]?.also{  currentAdjacent: MutableMap<VertexType, Double> ->
            return currentAdjacent
        }?:run{
        ///if edges do not exist, returns empty map
            return mutableMapOf<VertexType, Double>()
        }
        error("Is the cat dead or alive")
    }

    /**
     * Remove all edges and vertices from the graph
     */
    override fun clear() {
        vertices = mutableSetOf()
        weightedEdges = mutableMapOf()
    }


    //fun DijkstraAlgorithm (startNode: VertexType, endNode: VertexType): MutableList<VertexType>{

    //}

}


/**
 * ``MinPriorityQueue`` maintains a priority queue where the lower
 *  the priority value, the sooner the element will be removed from
 *  the queue.
 *  @param T the representation of the items in the queue
 */
interface MinPriorityQueue<T> {
    /**
     * @return true if the queue is empty, false otherwise
     */
    fun isEmpty(): Boolean

    /**
     * Add [elem] with at level [priority]
     */
    fun addWithPriority(elem: T, priority: Double)

    /**
     * Get the next (lowest priority) element.
     * @return the next element in terms of priority.  If empty, return null.
     */
    fun next(): T?

    /**
     * Adjust the priority of the given element
     * @param elem whose priority should change
     * @param newPriority the priority to use for the element
     *   the lower the priority the earlier the element int
     *   the order.
     */
    fun adjustPriority(elem: T, newPriority: Double)
}

class priorityQueue<T>(private var heap: MinHeap<T> = MinHeap()) :MinPriorityQueue<T>{
    //private var queue : MutableMap< T, Double> = mutableMapOf()
    //private var heap : MinHeap<T> = MinHeap()
    override fun isEmpty(): Boolean {
        return heap.isEmpty()
    }

    override fun addWithPriority(elem: T, priority: Double) {
        heap.insert(elem, priority)
    }
    override fun adjustPriority(elem: T, newPriority: Double) {
        heap.adjustHeapNumber(elem, newPriority)
    }

    override fun next(): T? {
        return heap.getMin()
    }

    fun contains (elem: T) :Boolean{
        return heap.contains(elem)
    }
}