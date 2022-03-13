import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.PriorityQueue;
import java.util.stream.Stream;

class PriorityQueueTest {
    static Stream<Arguments> streamProvider() {
        return Stream.of(
            Arguments.of(new int[]{3,1,2}, new int[]{1,2,3}),
            Arguments.of(new int[]{9,10,5,-5}, new int[]{-5,5,9,10}),
            Arguments.of(new int[]{55,22,44,33,11}, new int[]{11,22,33,44,55}),
            Arguments.of(new int[]{0,1,3,-1,-3}, new int[]{-3,-1,0,1,3}),
            Arguments.of(new int[]{9,8,7}, new int[]{7,8,10})
        );
    }

    @ParameterizedTest(name="#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void priorityQueueRun(int []random_array, int[] correct_array) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        int[] result = new int[random_array.length];
        int index = 0;
        Integer s;

        for (int i = 0; i < random_array.length; i++) {
            pq.offer(random_array[i]);
        }
        while((s = pq.poll()) != null) {
            result[index++] = s;
        }

        assertArrayEquals(correct_array, result);
    }


    @org.junit.jupiter.api.Test
    public void whenExceptionThrown_OfferIsNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            pq.offer(null);
        });

        assertEquals(null, exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    public void whenExceptionThrown_InitCapacityLessThanOne() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>(-1);
        });

        assertEquals(null, exception.getMessage());
    }

    @org.junit.jupiter.api.Test
    public void whenExceptionThrown_ArrayStoreWrongType() {
        Exception exception = assertThrows(ArrayStoreException.class, () -> {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            pq.add(1);
            Object[] arr = pq.toArray(new String[]{"Hello", "World"});
        });

        assertTrue(exception.getMessage().contains("arraycopy: element type mismatch: can not cast one of the elements of java.lang.Object[] to the type of the destination array, java.lang.String"));
    }
}