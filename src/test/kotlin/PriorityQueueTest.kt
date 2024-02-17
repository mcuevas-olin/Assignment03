import org.example.priorityQueue
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PriorityQueueTest {
    private var testQueue: priorityQueue<Char> = priorityQueue()
    @Test
    fun addwithPriorityContains(){
        assertFalse(testQueue.contains('A'))
        testQueue.addWithPriority('A', 3.0)
        assertTrue(testQueue.contains('A'))
    }

    @Test
    fun isEmpty(){
        //test emptyQueue
        assertTrue(testQueue.isEmpty())
        testQueue.addWithPriority('A', 3.0)
        assertFalse(testQueue.isEmpty())
    }

    @Test
    fun next(){
        testQueue.addWithPriority('A',3.0)
        testQueue.addWithPriority('B',2.0)
        assertEquals('B', testQueue.next())
        assertEquals('A', testQueue.next())
        assertNull(testQueue.next())
    }

    @Test
    fun adjustPriority(){
        testQueue.addWithPriority('A',3.0)
        testQueue.addWithPriority('B',2.0)
        testQueue.adjustPriority('A', 1.0)
        assertEquals('A', testQueue.next())
        assertEquals('B', testQueue.next())
        assertNull(testQueue.next())
    }
}