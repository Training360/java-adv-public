package singleton.producerconsumer;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocalStoreTest {

    @Test
    void getInstance() {
        LocalStore store = LocalStore.getInstance();
        store.reset();
        LocalStore localStore = LocalStore.getInstance();

        assertEquals(localStore, store);
    }

    @Test
    void add() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        store.add(new Product("Apple"));
        //Then
        assertEquals(1, store.getProductCount());
    }

    @Test
    void addTooMuch() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        for (int i = 0; i < LocalStore.CAPACITY + 2; i++) {
            store.add(new Product("Apple"));
        }

        assertEquals(LocalStore.CAPACITY, store.getProductCount());
    }

    @Test
    void removeFromEmptyStoreShouldThrowException() throws IllegalStateException {
        LocalStore store = LocalStore.getInstance();

        store.reset();

        Exception ex = assertThrows(IllegalStateException.class, () -> store.remove());
        assertEquals("Store is empty, no product is available!", ex.getMessage());
    }

    @Test
    void addRemove() {
        //Given
        LocalStore store = LocalStore.getInstance();
        store.reset();
        //Then
        assertEquals(0, store.getProductCount());
        //When
        store.add(new Product("Apple"));
        store.remove();
        //Then
        assertEquals(0, store.getProductCount());
    }
}