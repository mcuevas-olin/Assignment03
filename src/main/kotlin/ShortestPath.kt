package org.example




class ShortestPath<VertexType> (private var graphtouse: Graph<VertexType>){
    private var prev : MutableMap<VertexType,VertexType> = mutableMapOf()
    private var cost : MutableMap<VertexType, Double> = mutableMapOf()
    private var queue : priorityQueue<VertexType> = priorityQueue()

    fun run(startNode: VertexType, endNode: VertexType): MutableList<VertexType>{

        setup(startNode, endNode)
        println("finished shortest path setup")
        var currentNode: VertexType = startNode
        var currentEdges : Map<VertexType, Double> = graphtouse.getEdges(currentNode)

        //var currentNode : VertexType ?= startNode
        while (currentNode!= endNode){
            queue.next()?.also { currentNode = it }
            currentEdges = graphtouse.getEdges(currentNode)
            for (v in currentEdges.keys){
                if(queue.contains(v)){
                    val alternate = cost[currentNode]!! + currentEdges[v]!!

                    if (alternate<cost[v]!!){
                        cost[v] = alternate
                        queue.adjustPriority(v, alternate)
                        prev[v] = currentNode
                    }
                }
            }
        }

        return reconstructShortPath(startNode, endNode)
    }

    private fun reconstructShortPath(startNode: VertexType, endNode: VertexType): MutableList<VertexType>{
        var currentNode: VertexType = endNode
        val pathtoFinish: MutableList<VertexType> = mutableListOf(endNode)
        while (currentNode!=startNode){
         pathtoFinish.add(0, prev[currentNode]!!)
         currentNode = prev[currentNode]!!
        }
        return pathtoFinish
    }

    /**
     * Sets up the prev, cost, and queue with the data from the graph
     */
    private fun setup(startNode: VertexType, endNode: VertexType){
        for (v in graphtouse.getVertices()){
            prev.put(v, v)
            cost.put(v, Double.POSITIVE_INFINITY)
            queue.addWithPriority(v, Double.MAX_VALUE)

        }

        cost[startNode]= 0.0
        queue.adjustPriority(startNode, 0.0)
    }
}