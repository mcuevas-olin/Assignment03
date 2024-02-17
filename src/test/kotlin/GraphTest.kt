import org.example.DirectedGraph
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
class GraphTest {
    private var emptyTestGraphChar = DirectedGraph<Char>()

    //make a graph with a mutable set with elements 'A','B' and 'C'
    private var testingVertices : MutableSet<Char> = mutableSetOf('A','B','C')
    private var testGraph = DirectedGraph(testingVertices)

    @Test
    fun addVertex(){
        //should be able to add vertex A, as it currently has no vertices

        assertTrue(emptyTestGraphChar.addVertex('A'))

        //should return false, as we have already added a vertex A
        assertFalse(emptyTestGraphChar.addVertex('A'))

    }

    @Test
    fun getVertices(){
        //see that the vertices given by getVertices are the same that were assigned to the graph
        assertEquals(testingVertices, testGraph.getVertices())
    }

    @Test
    //tests both adding and getting the edges as it is impossible to test one without using the other
    fun addEdgeGetEdge(){
        var weight : Double = 13.0
        var expectedEdges : MutableMap<Char, Double> = mutableMapOf()

        //tests for node with no edges
        assertEquals(expectedEdges, testGraph.getEdges('A'))

        //tests for node with one edge
        testGraph.addEdge(from  = 'A',to = 'B', cost = weight)
        expectedEdges.put('B', weight)
        assertEquals(expectedEdges, testGraph.getEdges('A'))

        //tests for node with two edges
        testGraph.addEdge('A','C', weight)
        expectedEdges.put('C', weight)
        assertEquals(expectedEdges, testGraph.getEdges('A'))

        //tests updating the weight of one edge in node with two edges
        weight = 15.0
        testGraph.addEdge('A','B', weight)
        expectedEdges['B']=weight
        assertEquals(expectedEdges,testGraph.getEdges('A'))

    }

    @Test
    fun clear(){
        testGraph.addEdge('A','B', 13.0 )
        testGraph.clear()
        assertEquals(emptyTestGraphChar.getVertices(), testGraph.getVertices())
        assertEquals(emptyTestGraphChar.getEdges('A'), testGraph.getEdges('A'))
    }
}

