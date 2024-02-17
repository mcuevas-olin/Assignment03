package org.example

import java.io.File
class ProjectEulerProblem83(){
    var textfilepath:String = "C:\\Users\\mcuevas\\Documents\\DSA\\Assignment03\\src\\main\\kotlin\\0083_matrix.txt"
    fun solve(startPosition:String = "0,0", endPosition:String = "79,79"):Double{
        //set up to add all the matrix indicies as vertices ex: "1,1" for rowIndex1, rowColumn1, with the current value the weight it would take to move to that position
        val matrixArray = readTextFileTo2DArray(textfilepath)
        println("Finished reading text file")
        val secondhalfweightmap = createMapFromDoubleArray(matrixArray)
        val matrixGraph = DirectedGraph<String>()

        //add all vertices to graph
        for (currentVertex in secondhalfweightmap.keys) {
            matrixGraph.addVertex(currentVertex)
        }
        println("finished adding verticies")
        println(matrixGraph.getVertices())

        //add all edges to graph
        for (currentVertex in secondhalfweightmap.keys) {
            val neighboringKeys = findNeighboringKeys(currentVertex, matrixArray.size, matrixArray[0].size)
            for (neighborKey in neighboringKeys) {
                //val (neighborRowIndex, neighborColIndex) = extractIndicesFromKey(neighborKey)
                val weight = secondhalfweightmap[neighborKey]?:0.0
                matrixGraph.addEdge(currentVertex,neighborKey,weight)
            }
            //println(matrixGraph.getEdges(currentVertex))
        }

        //println("finished adding edges")
        //preps class for finding Shortest Path
        val toFindShort = ShortestPath(matrixGraph)

        //finds shortest path
        val shortPath = toFindShort.run(startPosition,endPosition)

        var totalcost:Double = 0.0
        for (currentPosition in shortPath){
            totalcost = totalcost + secondhalfweightmap[currentPosition]!!
        }

        return totalcost


    }

        private fun readTextFileTo2DArray(filePath: String): Array<DoubleArray> {
            try {
                val lines = File(filePath).readLines()

                // Create a 2D array of doubles
                val array2D = lines.map { line ->
                    line.split(',').map { it.toDouble() }.toDoubleArray()
                }.toTypedArray()

                return array2D
            } catch (e: Exception) {
                error("An error occurred: $e")
            }
        }

        private fun createMapFromDoubleArray(doubleArray: Array<DoubleArray>): MutableMap<String, Double> {
            val map = mutableMapOf<String, Double>()

            for ((rowIndex, row) in doubleArray.withIndex()) {
                for ((colIndex, value) in row.withIndex()) {
                    val key = "$rowIndex,$colIndex"
                    map[key] = value
                }
            }

            return map
        }


        private fun extractIndicesFromKey(key: String): Pair<Int, Int> {
            val indices = key.split(',').map { it.toInt() }
            return Pair(indices[0], indices[1])
        }

        // Function to find neighboring keys (left, right, top, down)
        private fun findNeighboringKeys(baseKey: String, numRows: Int, numCols: Int): List<String> {
            val (rowIndex, colIndex) = extractIndicesFromKey(baseKey)
            val neighboringKeys = mutableListOf<String>()

            val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

            for ((rowOffset, colOffset) in directions) {
                val newRow = rowIndex + rowOffset
                val newCol = colIndex + colOffset

                if (newRow in 0 until numRows && newCol in 0 until numCols) {
                    neighboringKeys.add("$newRow,$newCol")
                }
            }

            return neighboringKeys
        }
    }





/**
 * Convert Array into Graph
 *
 * connects to edges +1, -1 with respect to its index.
 * weight of edge is the value of the
 *
 */

