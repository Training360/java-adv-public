package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConsumerTest {

    @Test
    void emptyStoreShouldThrowException() throws IllegalStateException {
        Store store = LocalStore.getInstance();
        Consumer consumer = new Consumer(store);
        Exception ex = assertThrows(IllegalStateException.class, () -> consumer.consume());
        assertEquals("Store is empty, no product is available!", ex.getMessage());
    }

    @Test
    void testConsume() {
        //Given
        Store store = LocalStore.getInstance();
        store.add(new Product("Apple"));
        store.add(new Product("Orange"));
        //When
        Consumer consumer = new Consumer(store);
        //Then
        assertEquals("Apple", consumer.consume().getName());
        assertEquals("Orange", consumer.consume().getName());
    }
}