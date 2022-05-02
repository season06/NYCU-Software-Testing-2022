import static org.junit.jupiter.api.Assertions.*;

class BoundedQueueTest {
    private static BoundedQueue bq;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bq = new BoundedQueue(2);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        bq = null;
    }

    @org.junit.jupiter.api.Test
    void boundedQueue_BaseCase() { // C1=T, C2=F
        new BoundedQueue(1);
    }

    @org.junit.jupiter.api.Test
    void boundedQueue_C2() throws IllegalArgumentException { // C1=T, C2=T
        try {
            new BoundedQueue(-1);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), "BoundedQueue.constructor");
        }
    }

    @org.junit.jupiter.api.Test
    void enQueue_BaseCase() {  // C3=T, C4=F, C7=F
        bq.enQueue('A');
        assertEquals("[A]", bq.toString());
    }

    @org.junit.jupiter.api.Test
    void enQueue_C4() throws NullPointerException {  // C3=T, C4=T, C7=F
        try {
            bq.enQueue(null);
        } catch (NullPointerException e) {
            assertEquals(e.getMessage(), "BoundedQueue.enQueue");
        }
    }

    @org.junit.jupiter.api.Test
    void enQueue_C7() throws IllegalStateException {  // C3=T, C4=F, C7=T
        try {
            bq.enQueue(1);
            bq.enQueue(2);
            bq.enQueue(3);
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "BoundedQueue.enQueue");
        }
    }

    @org.junit.jupiter.api.Test
    void deQueue_BaseCase() {   // C5=T, C6=F
        bq.enQueue(1);
        assertEquals(1, bq.deQueue());
    }

    @org.junit.jupiter.api.Test
    void deQueue_C6() throws IllegalStateException {  // C5=T, C6=T
        try {
            bq.deQueue();
        } catch (IllegalStateException e) {
            assertEquals(e.getMessage(), "BoundedQueue.deQueue");
        }
    }

    @org.junit.jupiter.api.Test
    void isEmpty_BaseCase() {    // C6=T
        assertTrue(bq.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isEmpty_C6() {         // C6=F
        bq.enQueue(1);
        assertFalse(bq.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void isFull_BaseCase() {    // C7=T
        bq.enQueue(1);
        bq.enQueue(2);
        assertTrue(bq.isFull());
    }

    @org.junit.jupiter.api.Test
    void isFull_C7() {           // C7=F
        assertFalse(bq.isFull());
    }
}