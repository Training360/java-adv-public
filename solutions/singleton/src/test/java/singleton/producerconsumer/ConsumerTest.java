package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsumerTest {

    @Test
    public void emptyStoreShouldThrowException() throws IllegalStateException {

        StoreTestImpl store = new StoreTestImpl();

        Consumer consumer = new Consumer(store);


        Exception ex = assertThrows(IllegalStateException.class, () -> consumer.consume());
        assertEquals("Store is empty, no product is available!", ex.getMessage());

    }

    @Test
    public void testConsume() {
        //Given
        StoreTestImpl store = new StoreTestImpl();
        store.add(new Product("Apple"));
        store.add(new Product("Orange"));
        //When
        Consumer consumer = new Consumer(store);
        //Then
        assertEquals("Apple", consumer.consume().getName());
        assertEquals("Orange", consumer.consume().getName());
    }
}